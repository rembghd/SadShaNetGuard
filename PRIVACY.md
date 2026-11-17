# Privacy Policy — SadSha NetGuard

**Effective date:** 17 November 2026
**App:** SadSha NetGuard (`com.sadsha.netguard`)
**Developer:** SadSha (https://github.com/rembghd/SadShaNetGuard)

## What this app does

SadSha NetGuard is a no-root firewall. It uses Android's local
`VpnService` API to route your device's network traffic to itself,
on your own phone, so it can allow or block internet access per app.
Your traffic is filtered locally. It is never sent to our servers,
because we have no servers.

## Data we collect

**None.** SadSha NetGuard collects, stores, shares and sells no personal
data. There is no account, no sign-in, no analytics, no advertising
SDK and no tracking in the app.

Specifically:

- **No personal information** (name, email, phone number, location,
  contacts, device identifiers) is collected or transmitted.
- **No usage analytics.** The app contains no analytics SDK.
- **No advertising.** The app contains no ad SDK and shows no ads.
- **No in-app purchases.** All features are included free.
- **Network data stays on your device.** The firewall log, your
  per-app rules and your settings are stored only in the app's private
  storage on your phone. Nothing leaves the device.
- **Optional hosts file download.** If you enable ad-blocking, the app
  downloads a public hosts list (default:
  https://www.netguard.me/hosts) to your phone. That download is a
  plain file fetch from the list provider; no personal data is sent
  with it.

## Permissions the app uses

- **VPN service (`BIND_VPN_SERVICE`)** — the core of the firewall;
  required to filter traffic locally on the device.
- **Network state / Wi-Fi state** — to apply per-app Wi-Fi and mobile
  rules.
- **Run at startup** — to restart the firewall after a reboot, only
  if you enabled it.
- **Notifications** — to show the firewall status and, if enabled,
  the network speed graph.
- **Query installed apps** — to list your apps so you can set rules
  for them. The list never leaves your device.

## Children

The app is a general-audience utility. It collects no data from
anyone, including children.

## Changes to this policy

If this policy ever changes, the updated version will be published
here, in this repository.

## Contact

Questions about this policy: open an issue at
https://github.com/rembghd/SadShaNetGuard/issues
(or use the support email listed on the Google Play store listing).
