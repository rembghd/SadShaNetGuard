# App Store Listing Metadata & Asset Guide
### Google Play Store & Amazon Appstore — SadSha NetGuard

This document provides ready-to-use listing copy, strict character-limit compliance, and an asset directory manifest for **Google Play Store** and **Amazon Appstore**.

---

## 📊 Store Requirement Comparison Table

| Field | Google Play Store | Amazon Appstore | SadSha NetGuard Chosen Values |
| :--- | :--- | :--- | :--- |
| **App Title** | Max **30** characters | Max **50** characters | Google: `SadSha NetGuard: App Firewall` (29 ch)<br>Amazon: `SadSha NetGuard - No-Root Firewall & Data Guard` (47 ch) |
| **Short Description** | Max **80** characters | Max **1,200** characters | Google: 79 characters<br>Amazon: 865 characters |
| **Full Description** | Max **4,000** characters | Max **4,000** characters | Structured Markdown / Plain Text (~2,100 characters) |
| **Feature Bullets** | In description body | **3 to 5** dedicated bullets | 5 high-impact feature bullets provided |
| **Keywords** | Algorithmic indexing | Max **250** chars (comma-separated) | 14 targeted keywords (167 chars) |
| **App Icon** | 512 x 512 px 32-bit PNG | 512 x 512 px PNG (transparency)<br>+ 114 x 114 px PNG (transparency) | Both generated with transparent alpha channel |
| **Promo Image** | 1024 x 500 px (Feature Graphic) | 1024 x 500 px (Landscape) | 1024 x 500 px PNG generated |
| **Screenshots** | Min 2, Max 8 (16:9 or 9:16) | Min 3, Max 10 | 6 screens generated across all standard resolutions |

---

## 🎨 Asset File Manifest & Resolutions

All generated graphics meet store validation checks and are saved in both `/store_assets/` and `/public/store_assets/` (accessible directly via web preview):

### 1. App Icons
* **Large Icon (Google Play & Amazon):**
  - **Resolution:** `512 x 512 px`
  - **Format:** 32-bit RGBA PNG
  - **Transparency:** True alpha channel (`hasAlpha: true`, squircle curvature)
  - **Location:** `store_assets/icons/icon-512x512.png` & `public/store_assets/icons/icon-512x512.png`
* **Small Icon (Amazon Appstore):**
  - **Resolution:** `114 x 114 px`
  - **Format:** 32-bit RGBA PNG
  - **Transparency:** True alpha channel (`hasAlpha: true`)
  - **Location:** `store_assets/icons/icon-114x114.png` & `public/store_assets/icons/icon-114x114.png`

### 2. Promotional / Feature Graphic
* **Resolution:** `1024 x 500 px` (Landscape)
* **Format:** PNG
* **Location:** `store_assets/promotional/promo-1024x500.png` & `public/store_assets/promotional/promo-1024x500.png`

### 3. Screenshots (Multi-Resolution)
The screenshots are provided in all standard store formats:
* **1080 x 1920 px (Portrait - Phone Standard):**
  - `store_assets/screenshots/1080x1920/screenshot-1-main-dashboard.png` (Main Firewall Controls)
  - `store_assets/screenshots/1080x1920/screenshot-2-access-rules.png` (Per-App Granular Access)
  - `store_assets/screenshots/1080x1920/screenshot-3-traffic-log.png` (Live Traffic Inspector)
  - `store_assets/screenshots/1080x1920/screenshot-4-settings.png` (Firewall Settings)
  - `store_assets/screenshots/1080x1920/screenshot-5-advanced-network.png` (DNS & Subnets)
  - `store_assets/screenshots/1080x1920/screenshot-6-speed-graph.png` (Real-Time Speed Graph)
* **1920 x 1080 px (Landscape Presentation):**
  - `store_assets/screenshots/1920x1080/` (Featuring marketing titles, device framing, and clean typography)
* **1280 x 720 px (HD Landscape):**
  - `store_assets/screenshots/1280x720/`
* **800 x 480 px (Compact Landscape):**
  - `store_assets/screenshots/800x480/`

---

## 📱 Google Play Copy-Paste Block

```text
=== TITLE (29 / 30 chars) ===
SadSha NetGuard: App Firewall

=== SHORT DESCRIPTION (79 / 80 chars) ===
Block internet access per app without root. Save data, battery & guard privacy.

=== FULL DESCRIPTION (Google Play) ===
SadSha NetGuard provides simple and advanced ways to block access to the internet — without requiring root access. Applications and addresses can individually be allowed or denied access to your Wi-Fi and/or mobile network connections.

Blocking internet access helps you:
• Reduce unwanted mobile data usage and avoid unexpected roaming charges
• Save your battery by preventing silent background sync and tracking
• Protect your personal privacy against unauthorized telemetry and trackers

KEY FEATURES:
• Simple and clean to use: Toggle Wi-Fi or mobile data per app with a single tap
• 100% No Root Required: Safe, secure, and hassle-free
• Zero Telemetry: No calling home, no remote tracking, and no external analytics
• 100% On-Device Operation: All network routing happens strictly on your local phone
• IPv4 / IPv6 and TCP / UDP support
• Tethering and Wi-Fi hotspot support
• Optional lockdown mode: Restrict background traffic when screen is off
• Optional roaming protection: Automatically block bandwidth-heavy apps abroad
• Block system applications and bloatware with full control
• Custom DNS & Hosts Adblocking: Block intrusive advertising networks at DNS level
• Lightweight & Battery Friendly: Minimal memory footprint and dark mode themes

INCLUDED FREE — EVERY FEATURE:
• Live Outgoing Traffic Log: Search, filter, and inspect connections and IP addresses in real time
• Packet Capture (PCAP): Export network capture dumps for in-depth traffic analysis
• Per-Address Rule Management: Allow or block specific domain names and IP addresses per app
• Status Bar Speed Monitor: Real-time network speed graph notification
• Custom Themes: Five beautiful Material Design color themes in light and dark modes

WHY DOES SADSHA NETGUARD USE A VPN?
SadSha NetGuard uses Android's local VpnService API to route your device's network traffic to itself on-device. This allows it to filter network packets locally without routing your data through an external remote VPN server or third-party proxy. Your data never leaves your device.
```

---

## 🛒 Amazon Appstore Copy-Paste Block

```text
=== TITLE (47 / 50 chars) ===
SadSha NetGuard - No-Root Firewall & Data Guard

=== PRODUCT FEATURE BULLETS ===
• 100% No Root Required: Block Wi-Fi and mobile cellular data access for any application with a single tap
• Complete Privacy & Zero Tracking: Operates entirely on-device with no remote servers, no telemetry, and no data collection
• Save Battery & Mobile Data: Eliminate unwanted background synchronization, background telemetry, and expensive roaming fees
• Live Network Traffic Inspector: View active outbound requests, inspect domain/IP destinations, and export PCAP packet logs

=== KEYWORDS (Amazon Search) ===
firewall,no root,data saver,block internet,ad blocker,network monitor,privacy,traffic log,pcap,battery saver,vpn service,app blocker,wifi blocker,fire tablet utility

=== SHORT DESCRIPTION ===
SadSha NetGuard gives you absolute control over your device's network connections without requiring dangerous root permissions. Individually allow or deny internet access over Wi-Fi and mobile data per application to stop sneaky background updates, reduce high bandwidth bills, and dramatically prolong battery life.

Built with a privacy-first mindset, SadSha NetGuard routes network traffic strictly on your local device using Android's native loopback VPN engine. No traffic is ever redirected to third-party proxy servers, and zero telemetry is collected. Whether you want to silence background trackers, cut background traffic while gaming, or monitor real-time network traffic with exportable PCAP logs, SadSha NetGuard is your personal shield.
```

---

## 🔒 Policy Review & VpnService Declaration
When submitting to the Google Play Console, select:
1. **App Access:** All functionality is available without special access.
2. **Ads:** No — this build contains no ads (AdMob removed).
3. **Target Audience:** Age 13 and older (or General Audience).
4. **VpnService Disclosure:** Provide the declaration statement provided in `GOOGLE_PLAY_METADATA.md` explaining that Android VpnService is used purely for local on-device packet filtering with zero remote server forwarding.
