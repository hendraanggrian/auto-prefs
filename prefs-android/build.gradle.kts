import com.vanniktech.maven.publish.AndroidSingleVariantLibrary

plugins {
    alias(libs.plugins.android.library)
    kotlin("android") version libs.versions.kotlin
    kotlin("kapt") version libs.versions.kotlin
    alias(libs.plugins.dokka)
    alias(libs.plugins.kotlinx.kover)
    alias(libs.plugins.maven.publish)
}

android {
    defaultConfig.consumerProguardFiles("proguard-rules.pro")
    buildFeatures.buildConfig = false
    testOptions.unitTests.isIncludeAndroidResources = true
}

mavenPublishing.configure(AndroidSingleVariantLibrary())

dependencies {
    ktlint(libs.ktlint, ::configureKtlint)
    ktlint(libs.rulebook.ktlint)
    api(project(":prefs-core"))
    implementation(libs.androidx.preference)
    implementation(libs.androidx.fragment)
    kaptTest(project(":prefs-compiler"))
    testImplementation(kotlin("test-junit", libs.versions.kotlin.get()))
    testImplementation(libs.androidx.appcompat)
    testImplementation(libs.bundles.androidx.test)
}
