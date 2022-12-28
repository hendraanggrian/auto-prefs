plugins {
    alias(libs.plugins.android.application)
    kotlin("android") version libs.versions.kotlin
    kotlin("android.extensions") version libs.versions.kotlin
    kotlin("kapt") version libs.versions.kotlin
}

android {
    defaultConfig {
        applicationId = "com.example"
        multiDexEnabled = true
    }
    lint.abortOnError = false
}

// hotfix: duplicate class androidx.lifecycle.viewmodel
// https://stackoverflow.com/questions/69817925/problem-duplicate-class-androidx-lifecycle-viewmodel-found-in-modules
configurations.all {
    exclude("androidx.lifecycle", "lifecycle-viewmodel-ktx")
}

dependencies {
    implementation(project(":prefs-android"))
    kapt(project(":prefs-compiler"))
    implementation(libs.material)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.preference.ktx)
    implementation(libs.androidx.multidex)
    implementation(libs.commons.lang)
}
