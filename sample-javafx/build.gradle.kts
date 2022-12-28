plugins {
    application
    alias(libs.plugins.javafx)
    kotlin("jvm") version libs.versions.kotlin
    kotlin("kapt") version libs.versions.kotlin
}

javafx.modules("javafx.controls", "javafx.fxml")

application.mainClass.set("com.example.ExampleApp")

dependencies {
    implementation(project(":prefs-jvm"))
    kapt(project(":prefs-compiler"))
    implementation(libs.ktfx)
    implementation(libs.commons.lang)
}
