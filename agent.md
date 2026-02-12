agent.md

**Environment:** AndroidIDE (On-Device Build).
***
## 🛠️ TECHNICAL STACK (STRICT)
You must adhere to this environment. Do not suggest libraries incompatible with these versions.

‎​1. Environment & Path Configuration (FIXED - DO NOT CHANGE)
‎​This project is developed On-Device (Android Environment). Standard desktop paths (Mac/Windows/Linux) do not apply.
‎​IDE: Android Code Studio / AndroidIDE (AndroidCSOfficial v1.0.0+gh.r3)
‎​Device Arch: arm64-v8a
‎​Java Home: OpenJDK 17.0.16
‎​my local SDK Location: /data/user/0/com.tom.rv2ide/files/home/android-sdk/
‎​NDK Location: Inside SDK folder (Version 27.1.12297006 or 28.2.13676358)
‎​2. Build Toolchain Versions (STRICT)
‎​You MUST respect these versions strictly. Do not downgrade or suggest incompatible versions.
‎​Kotlin Version: 2.1.0
‎​Compile SDK: 35 (Android 15)
‎​Build Tools: 35.0.1 (Mandatory)
‎​NDK Version: "27.1.12297006" or "28.2.13676358"
‎​CMake Version: 4.1.1 (Installed & Verified)
‎​Android Gradle Plugin (AGP): ... (Must be newer to support SDK 35)
JVM 17
‎​Gradle Wrapper: 9.0-bin
‎​4. Native Development (C++/NDK)
‎​CMakeLists.txt Location: app/src/main/cpp/CMakeLists.txt
‎​C++ Standard: ... (e.g., C++17 or C++20)

Note : for Jules VM Cloud, if project not use ndk, do not apply ndk in project. cmake to or else. 
***