buildscript {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        google()
    }
    dependencies {
        classpath(kotlin("gradle-plugin", VERSION_KOTLIN))
        classpath(android)
        classpath(javafx)
        classpath(dokka)
        classpath(`git-publish`)
    }
}

allprojects {
    repositories {
        mavenCentral()
        google()
        maven(REPOSITORIES_OSSRH_SNAPSHOTS)
    }
}
