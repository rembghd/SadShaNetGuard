# Play Store Listing — SadSha NetGuard

Canonical listing copy for the Google Play submission.
Package: `com.sadsha.netguard` · Version: 3.1.0-sadsha (versionCode 202602)
Source: branch `ai-studio` in https://github.com/rembghd/SadShaNetGuard

## Title (29 / 30 chars)

```
SadSha NetGuard: App Firewall
```

## Short description (79 / 80 chars)

```
Block internet access per app without root. Save data, battery & guard privacy.
```

## Full description

```
SadSha NetGuard provides simple and advanced ways to block access to the internet — without requiring root access. Applications and addresses can individually be allowed or denied access to your Wi-Fi and/or mobile network connections.

Free — no ads, no in-app purchases. Every feature is included.

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

Based on the open-source NetGuard project (GPL-3.0) by Marcel Bokhorst. See the source and full attribution at https://github.com/rembghd/SadShaNetGuard
```

## Category

Tools

## Ads & purchases (declare exactly this)

- **Contains ads: NO** — no ad SDK in the app.
- **In-app purchases: NONE** — all features are free.

## Data safety (declare exactly this)

- **No data collected. No data shared.** No account, no analytics,
  no advertising SDK, no tracking. Firewall rules, logs and settings
  stay in the app's private storage on the device.

## App access

All functionality is available without special access (no account,
no sign-in).

## Content rating (questionnaire notes)

- No violence, no sexual content, no gambling, no user-generated
  content, no chat, no ads, no purchases.
- Expected rating: **Everyone**.
- The questionnaire itself must still be filled in Play Console by
  the account owner (see "Still needed" below).

## VpnService declaration (for the Play Console prompt)

> SadSha NetGuard uses Android's VpnService API only to filter
> network traffic locally on the user's own device. Traffic is
> routed to the app itself on-device; it is never forwarded to a
> remote server or third-party proxy. No user data leaves the device.

## Privacy policy

Proposed URL (lives in this repo — confirm or replace):

```
https://github.com/rembghd/SadShaNetGuard/blob/ai-studio/PRIVACY.md
```

## Support email

**NEEDED — not set yet.** Add a support email address in Play
Console before submitting (this cannot be invented here).

## Store assets (verified in this repo)

- App icon: `store_assets/icons/icon-512x512.png` — 512×512 ✓
- Feature graphic: `store_assets/promotional/promo-1024x500.png` — 1024×500 ✓
- Screenshots: `store_assets/screenshots/1080x1920/` — 6 shots, 1080×1920 ✓
  (also 1920×1080, 1280×720, 800×480 variants in the same folder)
- The 6 screenshots are REAL NetGuard UI (upstream app screens),
  not AI mockups — so there is no fabrication risk. Two caveats:
  they show the upstream teal theme and upstream shield icon, not
  the SadSha blue branding, and they are old upstream shots. Best
  practice: replace them with fresh screenshots taken from the
  SadSha build on a phone before submitting. Usable as-is if needed.

## Still needed from the user (his steps only)

1. **Release keystore + 4 GitHub secrets** — create his own release
   keystore, then add repository secrets `KEYSTORE_BASE64`,
   `KEYSTORE_PASSWORD`, `KEY_ALIAS`, `KEY_PASSWORD`. The
   `build-release-aab.yml` workflow is prepared but cannot run
   without them.
2. **Support email** — for the listing (see above).
3. **Content-rating questionnaire** — fill in Play Console
   (account sadsha4334@gmail.com).
4. **Submit the release AAB in Play Console** — upload the AAB the
   release workflow produces, complete the listing, and submit.
   Only he can do this from his Play Console account.
