plugins {
    android("application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdk = SDK_TARGET
    defaultConfig {
        minSdk = SDK_MIN
        targetSdk = SDK_TARGET
        applicationId = "com.example.$RELEASE_ARTIFACT"
        versionName = RELEASE_VERSION
        multiDexEnabled = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    sourceSets {
        named("main") {
            manifest.srcFile("AndroidManifest.xml")
            java.srcDir("src")
            res.srcDir("res")
            resources.srcDir("src")
        }
    }
    buildTypes {
        named("debug") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
        named("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
    packagingOptions {
        exclude("META-INF/services/javax.annotation.processing.Processor")
    }
    lint {
        isAbortOnError = false
    }
}

dependencies {
    api(kotlin("stdlib", VERSION_KOTLIN))
    implementation(project(":$RELEASE_ARTIFACT-android"))
    kapt(project(":$RELEASE_ARTIFACT-compiler"))
    implementation(apache("commons", "commons-lang3", VERSION_COMMONS_LANG))
    implementation(material())
    implementation(androidx("multidex", version = VERSION_MULTIDEX))
    implementation(androidx("core", "core-ktx", VERSION_ANDROIDX))
    implementation(androidx("appcompat", version = VERSION_ANDROIDX))
    implementation(androidx("preference", "preference-ktx", "1.1.1"))
}