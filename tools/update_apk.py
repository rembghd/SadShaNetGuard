#!/usr/bin/env python3
import os
import sys
import zipfile
import hashlib
import base64
import subprocess
import shutil

def run():
    base_dir = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
    os.chdir(base_dir)

    apk_src = os.path.join(base_dir, 'public', 'SadShaNetGuard.apk')
    if not os.path.exists(apk_src):
        apk_src = os.path.join(base_dir, 'SadShaNetGuard.apk')
    
    print(f"Reading base APK from: {apk_src}")
    
    # Generate RSA 2048 key and certificate for signing if not present
    key_file = '/tmp/sadsha_release.key'
    crt_file = '/tmp/sadsha_release.crt'
    if not os.path.exists(key_file) or not os.path.exists(crt_file):
        print("Generating SadSha NetGuard release certificate...")
        subprocess.check_call([
            'openssl', 'req', '-newkey', 'rsa:2048', '-nodes',
            '-keyout', key_file,
            '-x509', '-days', '10000',
            '-out', crt_file,
            '-subj', '/C=US/ST=CA/L=Mountain View/O=SadSha Security/OU=NetGuard/CN=SadSha NetGuard'
        ])

    # Icon file mappings
    icon_replacements = {
        'res/O4.png': 'app/src/main/res/mipmap-mdpi/ic_launcher_round.png',
        'res/zR.png': 'app/src/main/res/mipmap-mdpi/ic_launcher.png',
        'res/yn.png': 'app/src/main/res/mipmap-hdpi/ic_launcher_round.png',
        'res/gL.png': 'app/src/main/res/mipmap-hdpi/ic_launcher.png',
        'res/S1.png': 'app/src/main/res/mipmap-xhdpi/ic_launcher_round.png',
        'res/wb.png': 'app/src/main/res/mipmap-xhdpi/ic_launcher.png',
        'res/fO.png': 'app/src/main/res/mipmap-xxhdpi/ic_launcher_round.png',
        'res/RJ.png': 'app/src/main/res/mipmap-xxhdpi/ic_launcher.png',
        'res/o-.png': 'app/src/main/res/mipmap-xxxhdpi/ic_launcher.png',
    }

    # Verify replacement files exist
    for apk_path, disk_path in icon_replacements.items():
        if not os.path.exists(disk_path):
            raise RuntimeError(f"Missing icon asset on disk: {disk_path}")

    # Read original APK entries
    entries = {}
    with zipfile.ZipFile(apk_src, 'r') as zin:
        for item in zin.infolist():
            # Skip old signature files
            if item.filename.startswith('META-INF/'):
                continue
            data = zin.read(item.filename)
            entries[item.filename] = data

    # Update icon files
    for apk_path, disk_path in icon_replacements.items():
        with open(disk_path, 'rb') as f:
            new_data = f.read()
        entries[apk_path] = new_data
        print(f"Updated {apk_path} with {disk_path} ({len(new_data)} bytes)")

    # Add/update assets/admob_config.json
    admob_config = """{
  "application_id": "com.sadsha.netguard",
  "app_name": "SadSha NetGuard",
  "version_name": "3.1.0-release",
  "version_code": 202602,
  "admob_app_id": "ca-app-pub-3940256099942544~3347511713",
  "ad_units": {
    "banner": "ca-app-pub-3940256099942544/6300978111",
    "interstitial": "ca-app-pub-3940256099942544/1033173712",
    "rewarded": "ca-app-pub-3940256099942544/5224354917",
    "app_open": "ca-app-pub-3940256099942544/9257395921"
  },
  "smart_profiles": [
    "gaming_turbo",
    "data_saver",
    "strict_privacy",
    "night_shield"
  ],
  "monetization": {
    "app_open_enabled": true,
    "cold_start_delay_ms": 1000,
    "max_ad_frequency_hours": 4
  }
}
""".encode('utf-8')
    entries['assets/admob_config.json'] = admob_config

    # Construct MANIFEST.MF
    # Sort files deterministically (excluding META-INF)
    sorted_files = sorted(entries.keys())
    
    mf_lines = ["Manifest-Version: 1.0\r\n"]
    entry_sections = {} # filename -> exact section bytes
    
    for filename in sorted_files:
        content = entries[filename]
        file_sha256 = base64.b64encode(hashlib.sha256(content).digest()).decode('ascii')
        sec_str = f"Name: {filename}\r\nSHA-256-Digest: {file_sha256}\r\n\r\n"
        mf_lines.append(sec_str)
        entry_sections[filename] = sec_str.encode('utf-8')
        
    manifest_bytes = "".join(mf_lines).encode('utf-8')
    
    # Construct SADSHA.SF
    manifest_sha256 = base64.b64encode(hashlib.sha256(manifest_bytes).digest()).decode('ascii')
    sf_lines = [
        "Signature-Version: 1.0\r\n",
        "Created-By: 1.0 (Android)\r\n",
        f"SHA-256-Digest-Manifest: {manifest_sha256}\r\n",
        "X-Android-APK-Signed: 2, 3\r\n\r\n"
    ]
    
    for filename in sorted_files:
        sec_bytes = entry_sections[filename]
        sec_sha256 = base64.b64encode(hashlib.sha256(sec_bytes).digest()).decode('ascii')
        sf_lines.append(f"Name: {filename}\r\nSHA-256-Digest: {sec_sha256}\r\n\r\n")
        
    sf_bytes = "".join(sf_lines).encode('utf-8')
    
    # Write SF to temp file and sign with openssl CMS
    sf_temp = '/tmp/SADSHA.SF'
    rsa_temp = '/tmp/SADSHA.RSA'
    with open(sf_temp, 'wb') as f:
        f.write(sf_bytes)
        
    subprocess.check_call([
        'openssl', 'cms', '-sign',
        '-in', sf_temp,
        '-signer', crt_file,
        '-inkey', key_file,
        '-out', rsa_temp,
        '-outform', 'DER',
        '-nodetach',
        '-nosmimecap',
        '-binary'
    ])
    
    with open(rsa_temp, 'rb') as f:
        rsa_bytes = f.read()

    # Build final APK zip
    out_apk_tmp = '/tmp/SadShaNetGuard_updated.apk'
    with zipfile.ZipFile(out_apk_tmp, 'w', compression=zipfile.ZIP_DEFLATED) as zout:
        # 1. Write META-INF signature files
        zout.writestr('META-INF/MANIFEST.MF', manifest_bytes)
        zout.writestr('META-INF/SADSHA.SF', sf_bytes)
        zout.writestr('META-INF/SADSHA.RSA', rsa_bytes)
        
        # 2. Write all other entries
        for filename in sorted_files:
            # Some Android resources (like .arsc or uncompressed assets) prefer stored vs deflated
            # But standard zipfile with DEFLATED or original compression works.
            # Keep classes.dex, resources.arsc properly written
            zout.writestr(filename, entries[filename])
            
    apk_size = os.path.getsize(out_apk_tmp)
    print(f"Generated updated APK: {out_apk_tmp} ({apk_size} bytes)")
    
    # Distribute to all target locations
    destinations = [
        'public/SadShaNetGuard.apk',
        'public/NetGuard.apk',
        'SadShaNetGuard.apk',
        'NetGuard.apk',
        'dist/SadShaNetGuard.apk',
        'dist/NetGuard.apk'
    ]
    
    for dst in destinations:
        dst_path = os.path.join(base_dir, dst)
        dst_dir = os.path.dirname(dst_path)
        if os.path.exists(dst_dir):
            shutil.copy2(out_apk_tmp, dst_path)
            print(f"Copied to: {dst}")

    print("All APK files updated successfully!")

if __name__ == '__main__':
    run()
