# AppTheme

![Build](https://github.com/jonapoul/apptheme/actions/workflows/build.yml/badge.svg)
[![Jitpack](https://jitpack.io/v/jonapoul/apptheme.svg)](https://jitpack.io/#jonapoul/apptheme)

## Summary
A simple library to hold logic for changing Android app theme between light, dark and system default. Includes a `Preference` class to allow changing inside your app.
 
## Gradle Import
Root-level `build.gradle`:
```gradle
allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}
```

Module-level `build.gradle`:
```gradle
dependencies {
    implementation "com.github.jonapoul:apptheme:1.0.0"
}
```
