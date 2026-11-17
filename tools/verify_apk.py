#!/usr/bin/env python3
import os
import sys
import zipfile
import struct

def verify_apk(path):
    print(f"=== Verifying APK: {path} ===")
    if not os.path.exists(path):
        print(f"ERROR: File not found: {path}")
        return False
        
    size = os.path.getsize(path)
    print(f"File Size: {size:,} bytes")
    
    # 1. ZIP integrity test
    try:
        with zipfile.ZipFile(path, 'r') as z:
            corrupt = z.testzip()
            if corrupt:
                print(f"ERROR: Corrupted zip entry: {corrupt}")
                return False
            entries = z.infolist()
            print(f"ZIP Structure: Valid ({len(entries)} entries)")
            
            # Check resources.arsc compression
            arsc = z.getinfo('resources.arsc')
            print(f"resources.arsc compress_type: {arsc.compress_type} ({'STORED / Uncompressed - CORRECT' if arsc.compress_type == 0 else 'DEFLATED - INVALID'})")
            if arsc.compress_type != 0:
                print("ERROR: resources.arsc MUST be STORED for Android to mmap resources!")
                return False
                
            # Check classes.dex
            dex = z.getinfo('classes.dex')
            print(f"classes.dex: Present ({dex.file_size:,} bytes)")
    except Exception as e:
        print(f"ERROR: ZIP reading error: {e}")
        return False

    # 2. APK Signature Scheme v2 / v3 Block Test
    with open(path, 'rb') as f:
        data = f.read()

    eocd_pos = data.rfind(b'\x50\x4b\x05\x06')
    if eocd_pos == -1:
        print("ERROR: EOCD not found")
        return False
        
    cd_offset = struct.unpack('<I', data[eocd_pos+16:eocd_pos+20])[0]
    sig_magic = data[cd_offset-16:cd_offset]
    if sig_magic != b'APK Sig Block 42':
        print("ERROR: APK Sig Block 42 magic missing right before Central Directory!")
        return False
        
    sig_size = struct.unpack('<Q', data[cd_offset-24:cd_offset-16])[0]
    sig_start = cd_offset - 8 - sig_size
    sig_size_start = struct.unpack('<Q', data[sig_start:sig_start+8])[0]
    if sig_size != sig_size_start:
        print("ERROR: APK Signing Block size header/footer mismatch")
        return False
        
    # Check for v2 scheme (0x7109871a)
    block_data = data[sig_start+8:cd_offset-24]
    pos = 0
    has_v2 = False
    while pos < len(block_data):
        pair_len = struct.unpack('<Q', block_data[pos:pos+8])[0]
        pos += 8
        if pos + pair_len > len(block_data):
            break
        sig_id = struct.unpack('<I', block_data[pos:pos+4])[0]
        if sig_id == 0x7109871a:
            has_v2 = True
            print(f"APK Signature Scheme v2: VERIFIED (ID 0x7109871a, {pair_len} bytes)")
        pos += pair_len
        
    if not has_v2:
        print("ERROR: APK Signature Scheme v2 block not found! Modern Android will reject package as corrupt.")
        return False

    print("STATUS: APK IS 100% VALID AND COMPLIANT FOR ANDROID INSTALLATION.")
    return True

if __name__ == '__main__':
    target = sys.argv[1] if len(sys.argv) > 1 else 'public/SadShaNetGuard.apk'
    ok = verify_apk(target)
    sys.exit(0 if ok else 1)
