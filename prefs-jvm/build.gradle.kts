group = RELEASE_GROUP
version = RELEASE_VERSION

plugins {
    `java-library`
    kotlin("jvm")
    dokka
    `maven-publish`
    signing
}

sourceSets {
    main {
        java.srcDir("src")
        resources.srcDir("res")
    }
    test {
        java.srcDir("tests/src")
        resources.srcDir("tests/res")
    }
}

ktlint()

dependencies {
    api(project(":$RELEASE_ARTIFACT-core"))
    api(kotlin("stdlib", VERSION_KOTLIN))
    testImplementation(kotlin("test-junit", VERSION_KOTLIN))
    testImplementation(google("truth", "truth", VERSION_TRUTH))
}

tasks {
    dokkaJavadoc {
        dokkaSourceSets {
            "main" {
                sourceLink {
                    localDirectory.set(projectDir.resolve("src"))
                    remoteUrl.set(getGithubRemoteUrl())
                    remoteLineSuffix.set("#L")
                }
            }
        }
    }
}

mavenPublishJvm("$RELEASE_ARTIFACT-jvm")