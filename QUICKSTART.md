# Quick Build & Deploy Guide

Your Aniyomi extension repository is ready at: **https://github.com/lowkeyhawk/cubiquity**

## 🚀 Quick Steps to Get It Working

### Option 1: Build on Your Machine (Recommended)

1. **Open in Android Studio:**
   - Open Android Studio
   - Select "Open an Existing Project"
   - Navigate to: `d:\manwha_reader\manhwa-reader-extensions`
   - Wait for Gradle sync to complete

2. **Build the APK:**
   - Menu: **Build** → **Build Bundle(s) / APK(s)** → **Build APK(s)**
   - Or use terminal in Android Studio:
     ```bash
     .\gradlew.bat assembleRelease
     ```

3. **Find the APK:**
   ```
   src\en\manhwareader\build\outputs\apk\release\
   └── manhwareader-v1.0.0.apk
   ```

4. **Create Release on GitHub:**
   - Go to: https://github.com/lowkeyhawk/cubiquity/releases/new
   - Tag: `v1.0.0`
   - Title: `Manhwa Reader Extension v1.0.0`
   - Upload the APK file
   - Click "Publish release"

5. **Update Repo URLs:**
   After release is created, update `repo/index.json` and `repo/index.min.json`:
   ```json
   "apk": "https://github.com/lowkeyhawk/cubiquity/releases/download/v1.0.0/manhwareader-v1.0.0.apk"
   ```
   Then commit and push.

### Option 2: Use Pre-built APK (If Available)

If you have an Aniyomi extension APK from another source, you can:
1. Rename it to `manhwareader-v1.0.0.apk`
2. Create a release and upload it
3. Update the repo index files

## 📱 Add to Aniyomi

Once the APK is available in a GitHub release:

1. Open **Aniyomi** app
2. **Settings** → **Browse** → **Anime extension repos**
3. Tap **+** and enter:
   ```
   https://raw.githubusercontent.com/lowkeyhawk/cubiquity/main/repo/index.min.json
   ```
4. Tap **Allow** → Refresh extensions
5. Find **Manhwa Reader** → Install

## 🔧 Troubleshooting

**Build fails with SDK error:**
- Open Android Studio → SDK Manager
- Install Android SDK 33 (or update `build.gradle.kts` to your SDK version)

**Gradle sync fails:**
- File → Invalidate Caches / Restart
- Delete `gradle` folder and re-sync

**APK not installing:**
- Make sure you have Aniyomi installed (not Tachiyomi)
- Enable "Install from unknown sources" in Android settings

## 📝 Current Status

✅ Code pushed to https://github.com/lowkeyhawk/cubiquity  
✅ Repository structure is correct  
✅ Kotlin source code is ready  
❌ APK needs to be built manually (GitHub Actions billing issue)  
❌ Release not created yet  

## 🎯 Next Step

**Build the APK using Android Studio** and create a GitHub release. Then it will be ready to use online with Aniyomi!

Need help? The `MANUAL_BUILD.md` file has detailed instructions.
