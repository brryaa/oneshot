# OneShot

**OneShot** is a lightweight Android accessibility tool that automates a single tap on the notification bar — triggered by a single icon tap or shortcut. Perfect for quickly toggling VPN, hotspot, or any Quick Settings tile.

**OneShot** 是一个轻量级 Android 辅助工具，通过单次点击图标或快捷方式，自动点击通知栏中指定位置的快捷开关（如 VPN、热点等）。

---

## Features / 功能特色

- **One-tap automation** — tap the icon, the action fires instantly  
  **一键自动化** — 点击图标即触发操作
- **Configurable target coordinates** — works with any Quick Settings tile  
  **可配置点击坐标** — 适配任意快捷设置开关
- **Shortcut support** — pin a launcher shortcut for even faster access  
  **快捷方式支持** — 可固定到桌面快捷方式
- **Minimal UI** — no background services beyond Accessibility  
  **极简界面** — 除 Accessibility 服务外无常驻后台
- **Small footprint** — APK under 2 MB  
  **极小体积** — APK 不超过 2 MB

---

## Requirements / 系统要求

- Android 13 (API 33) or higher / Android 13 及以上
- Accessibility Service permission / 需开启辅助功能服务权限

---

## Installation / 安装方法

### Option A: Download APK (recommended for most users)
### 方法 A：直接下载 APK（推荐）

1. Go to the [**Releases**](../../releases) page  
   前往 [**Releases**](../../releases) 页面
2. Download the latest `oneshot.apk`  
   下载最新的 `oneshot.apk`
3. On your Android device, enable **"Install from unknown sources"** in Settings  
   在手机设置中开启**"允许安装未知来源应用"**
4. Open the downloaded APK and install  
   打开下载的 APK 并安装

### Option B: Build from source
### 方法 B：从源码构建

```bash
git clone https://github.com/YOUR_USERNAME/oneshot.git
cd oneshot
./gradlew assembleRelease
# APK output: app/build/outputs/apk/release/app-release.apk
```

---

## Setup / 使用前配置

### Step 1 — Enable Accessibility Service / 开启辅助功能服务

After installing, open the app. If the Accessibility Service is not enabled, the app will guide you to:  
安装后打开 App，如果辅助功能未开启，App 会引导你前往：

> **Settings → Accessibility → OneShot → Status Bar Click Service → Enable**  
> **设置 → 辅助功能 → OneShot → 状态栏点击服务 → 开启**

### Step 2 — Set Target Coordinates / 设置目标坐标

Tap **"Set Coordinates / 设置坐标"** in the main screen. Enter the X and Y pixel coordinates of the Quick Settings tile you want to tap.

点击主界面的**设置坐标**，输入你想点击的快捷设置开关的屏幕像素坐标（X/Y）。

**How to find coordinates / 如何找到坐标：**

1. Pull down your notification bar and note the approximate position of your target tile  
   下拉通知栏，观察目标开关的大致位置
2. Most devices: a tile in the top row of Quick Settings is roughly at `X=660, Y=940` (varies by device and resolution)  
   以 Pixel 9 为例，第一排快捷开关约在 `X=660, Y=940`（因设备分辨率不同而异）
3. Enter and save the values  
   输入并保存

### Step 3 — Use It / 开始使用

Simply **tap the OneShot icon** — the app will:  
直接**点击 OneShot 图标**，App 将自动：

1. Pull down the Quick Settings panel / 下拉快捷设置面板
2. Tap your target tile / 点击目标开关
3. Collapse the panel / 收起面板

You can also **long-press the icon** and use the **"Settings Center"** shortcut to jump directly to the coordinate settings.  
也可**长按图标**，通过**设置中心**快捷方式直接进入坐标设置。

---

## Privacy / 隐私说明

OneShot does **not** collect, transmit, or store any personal data. The only data stored is the tap coordinates you configure, saved locally on-device using Android SharedPreferences.

OneShot **不收集、不上传** 任何个人数据。唯一存储的数据是你设置的点击坐标，通过 Android SharedPreferences 保存在本地设备上。

---

## Build Info / 构建信息

| Item | Value |
|---|---|
| Min SDK | Android 13 (API 33) |
| Target SDK | 33 |
| Compile SDK | 35 |
| Language | Kotlin |
| Build tool | Gradle |

---

## License / 开源协议

MIT License. See [LICENSE](LICENSE) for details.

---

## Disclaimer / 免责声明

This tool uses Android's Accessibility API to simulate taps. Use responsibly and only for legitimate automation of your own device.  
本工具使用 Android 辅助功能 API 模拟点击操作，请合法合规使用，仅用于控制自己的设备。
