// Top-level build file for Aniyomi extensions repository  

buildscript {  
    repositories {  
        google()  
        mavenCentral()  
        maven { url = uri("https://maven.kanade.eu/repo") }  
    }  

    dependencies {  
        classpath("com.android.tools.build:gradle:7.4.2")  
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.22")  
    }  
}  

allprojects {  
    repositories {  
        google()  
        mavenCentral()  
        maven { url = uri("https://maven.kanade.eu/repo") }  
    }  
}  

subprojects {  
    apply(plugin = "com.android.library")  
    apply(plugin = "kotlin-android")  

    configure<com.android.build.gradle.LibraryExtension> {  
        compileSdk = 33  

        defaultConfig {  
            minSdk = 21  
            targetSdk = 33  
        }  

        sourceSets {  
            getByName("main") {  
                java.srcDirs("src")  
            }  
        }  
    }  

    dependencies {  
        // Aniyomi extensions library  
        "compileOnly"("eu.kanade.tachiyomi:extensions:1.4.5")  
          
        // OkHttp for network requests  
        "implementation"("com.squareup.okhttp3:okhttp:4.11.0")  
          
        // JSON handling
        "implementation"("org.json:json:20230227")  
    }  
}  
        implementation("org.jsoup:jsoup:1.16.1")  
    }  
}  
