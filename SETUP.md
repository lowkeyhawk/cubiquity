# 🚀 Quick Setup Guide

Follow these steps to get your Aniyomi extension repository up and running:

## Step 1: Create GitHub Repository

1. Go to https://github.com/new
2. Repository name: `cubiquity`
3. Set to **Public**
4. **Don't** initialize with README (we already have one)
5. Click **Create repository**

## Step2: Push to GitHub

The repository is already configured for `lowkeyhawk/cubiquity`. Just push the code:

## Step 3: Push to GitHub

```bash
cd d:\manwha_reader\manhwa-reader-extensions
git init
git add .
git commit -m "Initial commit: Manhwa Reader Aniyomi Extension"
git remote add origin https://github.com/lowkeyhawk/cubiquity.git
git branch -M main
git push -u origin main
```

## Step 4: Build and Release

### Option A: Automatic Build (Recommended)
1. Tag a release:
   ```bash
   git tag v1.0.0
   git push origin v1.0.0
   ```
2. GitHub Actions will automatically:
   - Build the APK
   - Create a release
   - Upload the APK
   - Update repo index files

### Option B: Manual Build
```bash
# Build locally (requires Android Studio/Gradle)
./gradlew assembleRelease

# APK location:
# src/en/manhwareader/build/outputs/apk/release/manhwareader-v1.0.0.apk

# Create release manually on GitHub and upload APK
```

## Step 5: Add to Aniyomi

1. Open **Aniyomi** app on your Android device
2. Go to **Settings** → **Browse** → **Anime extension repos**
3. Tap the **+** button
4. Enter your repo URL:
   ```
   https://raw.githubusercontent.com/lowkeyhawk/cubiquity/main/repo/index.min.json
   ```
5. Tap **Allow** or **OK**
6. Pull down to refresh the extensions list
7. Look for **Manhwa Reader** in the extensions list
8. Tap **Install** or **Enable**

## Step 6: Test the Extension

1. In Aniyomi, go to **Browse** → **Manhwa Reader**
2. Try searching for manhwa
3. Open a manhwa and check if chapters load
4. Try reading a chapter

## 🔧 Troubleshooting

**Extension not appearing:**
- Double-check the repo URL is correct
- Make sure you've pushed to GitHub
- Try refreshing the extensions list
- Check that the APK is built and uploaded

**Can't connect to server:**
- Ensure your Manhwa Reader server is running
- Check that your phone can reach `http://192.168.1.209/manhwa-api/api`
- Both devices must be on the same network
- Try accessing the API from your phone's browser

**Build fails:**
- Make sure you have JDK 11+ installed
- Check that Android SDK is installed
- Try: `./gradlew clean assembleRelease`

## 📝 Notes

- The server URL (`192.168.1.209`) is hardcoded in `ManhwaReader.kt`
- If your server IP changes, update line 13 in `ManhwaReader.kt`
- You can create multiple extensions by adding more folders under `src/`
- For icons, see `ICONS.md`

---

**Need help?** Check the main `README.md` for more details!
