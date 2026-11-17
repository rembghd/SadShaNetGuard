import fs from 'fs';
import path from 'path';
import sharp from 'sharp';

const OUT_DIR = 'public/store_assets';
const STORE_DIR = 'store_assets';

[OUT_DIR, STORE_DIR].forEach(base => {
  ['icons', 'promotional', 'screenshots', 'screenshots/1080x1920', 'screenshots/1920x1080', 'screenshots/1280x720', 'screenshots/800x480'].forEach(sub => {
    fs.mkdirSync(path.join(base, sub), { recursive: true });
  });
});

async function generateIcons() {
  console.log('Generating icons with transparency...');
  
  // Create 512x512
  const size512 = 512;
  const radius512 = 100;
  const mask512 = Buffer.from(
    `<svg width="${size512}" height="${size512}"><rect x="0" y="0" width="${size512}" height="${size512}" rx="${radius512}" ry="${radius512}" fill="#ffffff"/></svg>`
  );

  const base512 = await sharp('public/icon.png')
    .resize(size512, size512, { fit: 'cover' })
    .toBuffer();

  const icon512 = await sharp(base512)
    .composite([{ input: mask512, blend: 'dest-in' }])
    .png()
    .toBuffer();

  fs.writeFileSync('public/store_assets/icons/icon-512x512.png', icon512);
  fs.writeFileSync('store_assets/icons/icon-512x512.png', icon512);
  fs.writeFileSync('fastlane/metadata/android/en-US/images/icon.png', icon512);

  // Create 114x114 for Amazon Appstore (with transparency)
  const size114 = 114;
  const radius114 = 23;
  const mask114 = Buffer.from(
    `<svg width="${size114}" height="${size114}"><rect x="0" y="0" width="${size114}" height="${size114}" rx="${radius114}" ry="${radius114}" fill="#ffffff"/></svg>`
  );

  const base114 = await sharp('public/icon.png')
    .resize(size114, size114, { fit: 'cover' })
    .toBuffer();

  const icon114 = await sharp(base114)
    .composite([{ input: mask114, blend: 'dest-in' }])
    .png()
    .toBuffer();

  fs.writeFileSync('public/store_assets/icons/icon-114x114.png', icon114);
  fs.writeFileSync('store_assets/icons/icon-114x114.png', icon114);
  console.log('Icons generated successfully.');
}

async function generatePromotionalImage() {
  console.log('Generating 1024x500 promotional image...');
  let promoBuffer;
  if (fs.existsSync('playstore/play.png')) {
    promoBuffer = await sharp('playstore/play.png')
      .resize(1024, 500, { fit: 'contain', background: { r: 10, g: 19, b: 36, alpha: 1 } })
      .png()
      .toBuffer();
  } else {
    promoBuffer = await sharp({
      create: {
        width: 1024,
        height: 500,
        channels: 4,
        background: { r: 10, g: 19, b: 36, alpha: 1 }
      }
    }).png().toBuffer();
  }

  fs.writeFileSync('public/store_assets/promotional/promo-1024x500.png', promoBuffer);
  fs.writeFileSync('store_assets/promotional/promo-1024x500.png', promoBuffer);
  fs.writeFileSync('fastlane/metadata/android/en-US/images/featureGraphic.png', promoBuffer);
  console.log('Promotional image generated successfully.');
}

async function generateScreenshots() {
  console.log('Generating screenshots in required formats...');

  const sources = [
    { src: 'screenshots/01-main.png', id: '1-main-dashboard', title: 'Per-App Firewall Controls' },
    { src: 'screenshots/03-main-access.png', id: '2-access-rules', title: 'Granular Network Access Rules' },
    { src: 'screenshots/20-traffic-log.png', id: '3-traffic-log', title: 'Live Network Traffic Inspector' },
    { src: 'screenshots/30-settings-default.png', id: '4-settings', title: 'Comprehensive Firewall Settings' },
    { src: 'screenshots/32-settings-network.png', id: '5-advanced-network', title: 'Custom DNS & Subnet Routing' },
    { src: 'screenshots/34-settings-speed.png', id: '6-speed-graph', title: 'Real-Time Speed & Usage Graph' }
  ];

  for (const item of sources) {
    if (!fs.existsSync(item.src)) {
      console.log(`Skipping missing source ${item.src}`);
      continue;
    }

    // 1080 x 1920 Portrait (standard phone store listing)
    const p1080 = await sharp(item.src)
      .resize(1080, 1920, { fit: 'contain', background: { r: 15, g: 23, b: 42, alpha: 1 } })
      .png()
      .toBuffer();

    fs.writeFileSync(`public/store_assets/screenshots/1080x1920/screenshot-${item.id}.png`, p1080);
    fs.writeFileSync(`store_assets/screenshots/1080x1920/screenshot-${item.id}.png`, p1080);
    fs.writeFileSync(`public/store_assets/screenshots/screenshot-${item.id}.png`, p1080);
    fs.writeFileSync(`store_assets/screenshots/screenshot-${item.id}.png`, p1080);

    // 1920 x 1080 Landscape presentation format
    const phoneIn1920x1080 = await sharp(item.src)
      .resize(580, 1030, { fit: 'contain' })
      .toBuffer();

    const titleClean = item.title.replace('&', 'and');
    const svgOverlay = Buffer.from(
      '<svg width="1150" height="1080" viewBox="0 0 1150 1080" xmlns="http://www.w3.org/2000/svg">' +
      '<text x="80" y="380" font-family="sans-serif" font-size="32" font-weight="bold" fill="#38bdf8">SADSHA NETGUARD</text>' +
      '<text x="80" y="470" font-family="sans-serif" font-size="60" font-weight="bold" fill="#ffffff">' + titleClean + '</text>' +
      '<text x="80" y="550" font-family="sans-serif" font-size="28" fill="#94a3b8">No Root Required | 100% On-Device VPN | Zero Tracking</text>' +
      '<rect x="80" y="600" width="360" height="60" rx="12" fill="#0284c7" />' +
      '<text x="110" y="640" font-family="sans-serif" font-size="24" font-weight="bold" fill="#ffffff">Google Play and Amazon</text>' +
      '</svg>'
    );

    const landscape1920 = await sharp({
      create: {
        width: 1920,
        height: 1080,
        channels: 4,
        background: { r: 15, g: 23, b: 42, alpha: 1 }
      }
    })
    .composite([
      { input: phoneIn1920x1080, top: 25, left: 1240 },
      { input: svgOverlay, top: 0, left: 0 }
    ])
    .png()
    .toBuffer();

    fs.writeFileSync(`public/store_assets/screenshots/1920x1080/screenshot-${item.id}-landscape.png`, landscape1920);
    fs.writeFileSync(`store_assets/screenshots/1920x1080/screenshot-${item.id}-landscape.png`, landscape1920);

    // 1280 x 720 Landscape
    const l1280 = await sharp(landscape1920)
      .resize(1280, 720, { fit: 'cover' })
      .png()
      .toBuffer();
    fs.writeFileSync(`public/store_assets/screenshots/1280x720/screenshot-${item.id}-1280x720.png`, l1280);
    fs.writeFileSync(`store_assets/screenshots/1280x720/screenshot-${item.id}-1280x720.png`, l1280);

    // 800 x 480 Landscape
    const l800 = await sharp(landscape1920)
      .resize(800, 480, { fit: 'cover' })
      .png()
      .toBuffer();
    fs.writeFileSync(`public/store_assets/screenshots/800x480/screenshot-${item.id}-800x480.png`, l800);
    fs.writeFileSync(`store_assets/screenshots/800x480/screenshot-${item.id}-800x480.png`, l800);
  }

  console.log('Screenshots generated successfully.');
}

async function run() {
  await generateIcons();
  await generatePromotionalImage();
  await generateScreenshots();
  console.log('All store assets generated successfully!');
}

run().catch(err => {
  console.error(err);
  process.exit(1);
});
