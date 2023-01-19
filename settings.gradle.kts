pluginManagement.repositories {
    gradlePluginPortal()
    mavenCentral()
    google()
}
dependencyResolutionManagement.repositories {
    mavenCentral()
    google()
    maven("https://s01.oss.sonatype.org/content/repositories/snapshots/") // KtFX for sample
}

rootProject.name = "auto-prefs"

include("prefs-annotations", "prefs-core", "prefs-jvm", "prefs-android", "prefs-compiler" )
include("sample-android", "sample-javafx")
include("website")
