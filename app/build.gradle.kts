plugins {
    alias(libs.plugins.korge)
}

korge {
    id = "com.rpg.aetherion"
    name = "Aetherion RPG"

    targetAndroid()

    // Auto-detection usually works for main
}

android {
    namespace = "com.rpg.aetherion"
    compileSdk = 35
    buildToolsVersion = "35.0.1"

    defaultConfig {
        applicationId = "com.rpg.aetherion"
        minSdk = 21
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }
}
