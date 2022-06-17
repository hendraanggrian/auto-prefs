plugins {
    javafx
    kotlin("jvm")
    kotlin("kapt")
    application
}

javafx {
    modules("javafx.controls", "javafx.fxml")
}

application {
    mainClass.set("com.example.$RELEASE_ARTIFACT.ExampleApp")
}

sourceSets {
    main {
        java.srcDir("src")
    }
    test {
        java.srcDir("tests/src")
    }
}

dependencies {
    implementation(kotlin("stdlib", VERSION_KOTLIN))
    implementation(project(":$RELEASE_ARTIFACT-jvm"))
    kapt(project(":$RELEASE_ARTIFACT-compiler"))
    implementation(hendraanggrian("ktfx", "ktfx", VERSION_KTFX))
    implementation(apache("commons", "commons-lang3", VERSION_COMMONS_LANG))
}
