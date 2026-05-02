# Manhwa Reader Extension Repository

Aniyomi extension for reading manhwa from your local Manhwa Reader server.

## 📱 Installation

### Method 1: Add Repository URL (Recommended)
1. Open **Aniyomi** app
2. Go to **Settings** → **Browse** → **Anime extension repos**
3. Tap **+** and enter:
   ```
   https://raw.githubusercontent.com/lowkeyhawk/cubiquity/main/repo/index.min.json
   ```
4. Tap **Allow** and refresh extensions
5. Find **Manhwa Reader** in the extensions list and install

### Method 2: Direct APK Install
1. Download the latest APK from [Releases](https://github.com/lowkeyhawk/cubiquity/releases)
2. Transfer to your Android device
3. Install the APK (enable unknown sources)
4. Open Aniyomi and the extension should appear

## 🏗️ Repository Structure

```
manhwa-reader-extensions/
├── README.md
├── build.gradle
├── settings.gradle.kts
├── common.gradle
├── repo/
│   ├── index.json          # Full repo index
│   └── index.min.json      # Minified repo index
└── src/
    └── en/
        └── manhwareader/
            ├── build.gradle
            ├── AndroidManifest.xml
            └── src/
                └── eu/kanade/tachiyomi/animeextension/en/manhwareader/
                    └── ManhwaReader.kt
```

## 🔧 Extension Details

| Field | Value |
|-------|-------|
| **Name** | Manhwa Reader |
| **Language** | English (en) |
| **Package** | `eu.kanade.tachiyomi.animeextension.en.manhwareader` |
| **Source URL** | `http://192.168.1.209/manhwa-api/api` |
| **Version** | 1.0.0 |

## 📡 API Endpoints

The extension connects to your Manhwa Reader PHP backend:

| Endpoint | Purpose |
|----------|---------|
| `/manhwa.php` | List/search manhwa |
| `/manhwa.php?id={id}` | Get manhwa details |
| `/chapters.php?manhwa_id={id}` | Get chapters list |
| `/reader.php?chapter_id={id}` | Get chapter images |

## 🛠️ Building from Source

### Prerequisites
- Android Studio or Android SDK
- JDK 11 or higher
- Git

### Build Commands
```bash
# Clone the repository
git clone https://github.com/lowkeyhawk/cubiquity.git
cd cubiquity

# Build the extension
./gradlew assembleRelease

# APK location:
# src/en/manhwareader/build/outputs/apk/release/manhwareader-v1.0.0.apk
```

## 🚀 Automated Builds

This repository uses GitHub Actions for automated building:

1. Push a new tag to trigger build:
   ```bash
   git tag v1.0.0
   git push origin v1.0.0
   ```

2. GitHub Action will:
   - Build the extension APK
   - Create a GitHub Release
   - Upload the APK to the release
   - Update the repo index files automatically

## 📝 Notes

- Make sure your Manhwa Reader server is accessible from your Android device
- The server URL (`http://192.168.1.209/manhwa-api/api`) should be reachable
- If using localhost, ensure your phone and server are on the same network
- You may need to adjust the server URL in `ManhwaReader.kt` if your IP changes

## 🐛 Troubleshooting

**Extension not showing up:**
- Check that the repo URL is correct
- Pull to refresh the extensions list
- Ensure your Aniyomi app is up to date

**Can't connect to server:**
- Verify the server is running
- Check that your phone can reach the server IP
- Try accessing the API from your phone's browser

## 📄 License

Licensed under the Apache License, Version 2.0

---

**Repository: https://github.com/lowkeyhawk/cubiquity**

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
