# SadSha NetGuard Rebranding & Google Play Store Compliance Guide

This guide details all code changes, file paths, Gradle snippets, and Google Play Store policy compliance steps for rebranding the open-source NetGuard project into **SadSha NetGuard** (`com.sadsha.netguard`).

---

## 1. App Name & Package Name Changes

### Target Specifications
- **App Name:** `SadSha NetGuard` (or `SadSha AI NetGuard`)
- **New Package Name / Application ID:** `com.sadsha.netguard`
- **Target SDK:** API 34 (Android 14)
- **Minimum SDK:** API 21 (Android 5.0 Lollipop)

### Files Modified

#### A. `app/src/main/res/values/strings.xml`
```xml
<resources>
    <string name="app_name" translatable="false">SadSha NetGuard</string>
    <string name="app_description">SadSha NetGuard provides intelligent no-root firewall protection to monitor, control network access, save battery, and block unwanted tracking.</string>
    <string name="app_copyright" translatable="false">Copyright \u00A9 2026 SadSha AI Security</string>
    
    <!-- Smart Profiles (Feature Additions) -->
    <string name="profile_normal">Standard Mode</string>
    <string name="profile_normal_desc">Default firewall filtering with manual rule enforcement</string>
    <string name="profile_gaming">Gaming Mode</string>
    <string name="profile_gaming_desc">Low latency prioritizer: blocks background syncing apps to minimize game ping</string>
    <string name="profile_data_saver">Data Saver Mode</string>
    <string name="profile_data_saver_desc">Restricts background cellular data for all non-essential apps</string>
    <string name="profile_privacy">Strict Privacy Mode</string>
    <string name="profile_privacy_desc">Blocks ad-trackers, telemetry, and background access for untrusted apps</string>

    <!-- Custom Theme & Appearance -->
    <string name="setting_theme_title">Custom Theme &amp; Appearance</string>
    <string name="setting_theme_system">System Default</string>
    <string name="setting_theme_light">SadSha Light</string>
    <string name="setting_theme_dark">SadSha Cyber Dark</string>
    <string name="setting_theme_amoled">Pure AMOLED Black</string>

    <!-- AdMob & Compliance -->
    <string name="ad_banner_disclaimer">Ads support development and server costs without collecting personal identity.</string>
    <string name="admob_app_id" translatable="false">ca-app-pub-3940256099942544~3347511713</string>
    <string name="admob_banner_unit_id" translatable="false">ca-app-pub-3940256099942544/6300978111</string>
</resources>
```

#### B. `app/src/main/AndroidManifest.xml`
- Permission definitions and usages updated from `eu.faircode.netguard.permission.ADMIN` to `com.sadsha.netguard.permission.ADMIN`:
```xml
<permission
    android:name="com.sadsha.netguard.permission.ADMIN"
    android:description="@string/app_description"
    android:label="@string/app_name"
    android:protectionLevel="signature" />

<uses-permission android:name="com.sadsha.netguard.permission.ADMIN" />
```
- Intent-filter action names updated to match the new package namespace:
  - `com.sadsha.netguard.START_PORT_FORWARD`
  - `com.sadsha.netguard.STOP_PORT_FORWARD`
  - `com.sadsha.netguard.DOWNLOAD_HOSTS_FILE`
  - `com.sadsha.netguard.ON` / `OFF` / `LOCKDOWN_ON` / `LOCKDOWN_OFF`
- Google AdMob App ID added inside `<application>`:
```xml
<meta-data
    android:name="com.google.android.gms.ads.APPLICATION_ID"
    android:value="ca-app-pub-3940256099942544~3347511713" />
```

#### C. `app/build.gradle`
```groovy
plugins {
    id 'com.android.application'
}

android {
    namespace 'com.sadsha.netguard'
    compileSdk 34

    defaultConfig {
        applicationId "com.sadsha.netguard"
        minSdk 21
        targetSdk 34
        versionCode 202602
        versionName "3.1.0-sadsha"

        externalNativeBuild {
            cmake {
                cppFlags "-std=c++14 -frtti -fexceptions"
                arguments "-DANDROID_STL=c++_shared"
            }
        }
        ndk {
            abiFilters 'armeabi-v7a', 'arm64-v8a', 'x86', 'x86_64'
        }
    }

    buildTypes {
        release {
            minifyEnabled true
            shrinkResources true
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
}

dependencies {
    implementation 'androidx.appcompat:appcompat:1.7.0'
    implementation 'androidx.core:core:1.13.1'
    implementation 'androidx.preference:preference:1.2.1'
    implementation 'androidx.recyclerview:recyclerview:1.3.2'
    implementation 'com.google.android.material:material:1.12.0'

    // Google AdMob SDK
    implementation 'com.google.android.gms:play-services-ads:23.3.0'
}
```

---

## 2. Logo & Branding Assets

The custom 3D metallic shield with vibrant green/cyan ribbon has been processed into all Android density buckets:

| Density Tier | Resolution | Asset Location |
|---|---|---|
| **mdpi** | 48 × 48 px | `app/src/main/res/mipmap-mdpi/ic_launcher.png`, `ic_launcher_round.png` |
| **hdpi** | 72 × 72 px | `app/src/main/res/mipmap-hdpi/ic_launcher.png`, `ic_launcher_round.png` |
| **xhdpi** | 96 × 96 px | `app/src/main/res/mipmap-xhdpi/ic_launcher.png`, `ic_launcher_round.png` |
| **xxhdpi** | 144 × 144 px | `app/src/main/res/mipmap-xxhdpi/ic_launcher.png`, `ic_launcher_round.png` |
| **xxxhdpi** | 192 × 192 px | `app/src/main/res/mipmap-xxxhdpi/ic_launcher.png`, `ic_launcher_round.png` |
| **Play Store / Web** | 512 × 512 px | `app/src/main/ic_launcher-web.png`, `public/icon.png` |

---

## 3. Feature Additions & Differentiation

### A. Smart Profiles (`com.sadsha.netguard.SmartProfileManager`)
Provides unique preset modes to distinguish the application on Google Play:
1. **Gaming Mode (Low Latency):** Automatically blocks heavy background data-syncing apps (cloud backups, social video auto-refresh) during active gameplay sessions to minimize ping spikes.
2. **Data Saver Mode:** Restricts background cellular traffic to only critical communication services (dialer, messaging, navigation).
3. **Strict Privacy Mode:** Enforces zero-telemetry rules, blocking known analytics and tracker endpoints.

### B. Custom Theme & Appearance
- **System Default**, **SadSha Light**, **SadSha Cyber Dark**, and **Pure AMOLED Black** themes supported with dynamic night mode toggling via `AppCompatDelegate.setDefaultNightMode()`.

---

## 4. Google AdMob Integration & Play Store VpnService Compliance

### A. AdMob Banner XML (`app/src/main/res/layout/main.xml`)
Placed cleanly at the bottom of the main activity:
```xml
<com.google.android.gms.ads.AdView
    android:id="@+id/adView"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_gravity="center_horizontal"
    android:layout_marginTop="6dp"
    ads:adSize="BANNER"
    ads:adUnitId="ca-app-pub-3940256099942544/6300978111" />
```

### B. ProGuard Configuration (`app/proguard-rules.pro`)
```proguard
# Google Play Services & AdMob
-keep public class com.google.android.gms.ads.** {
   public *;
}
-keep public class com.google.ads.** {
   public *;
}
-dontwarn com.google.android.gms.**
-dontwarn com.google.android.gms.internal.**
```

### C. Critical Google Play VpnService & Ad Policy Compliance
To pass Google Play app review:
1. **VPN Loopback Exclusion:** The app's own internal ad traffic (`com.google.android.gms.ads`) must NOT be looped into the packet filter or blocked by the VPN service itself. In `ServiceSinkhole.java`, ensure the app's own UID is excluded from VPN routing via:
   ```java
   builder.addDisallowedApplication(getPackageName());
   ```
2. **Foreground Service Special Use Declaration:** In the Play Console, declare `VpnService` as the core application function used to provide local firewall security. The manifest contains the required declaration:
   ```xml
   <property
       android:name="android.app.PROPERTY_SPECIAL_USE_FGS_SUBTYPE"
       android:value="SadSha NetGuard is an intelligent firewall app using the Android VPN service to filter traffic on the device..." />
   ```
3. **No Interfering Ads:** Ads must not obscure system network dialogs, VPN connection requests, or bypass consent warnings.
