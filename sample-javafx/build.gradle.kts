plugins {
    application
    kotlin("jvm") version libs.versions.kotlin
    kotlin("kapt") version libs.versions.kotlin
}

application.mainClass.set("com.example.autoprefs.ExampleApp")

dependencies {
    implementation(project(":prefs-jvm"))
    kapt(project(":prefs-compiler"))
    implementation(libs.ktfx)
}
