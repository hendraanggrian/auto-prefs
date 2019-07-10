plugins {
    `java-library`
    bintray
    `bintray-release`
}

group = RELEASE_GROUP
version = RELEASE_VERSION

sourceSets["main"].java.srcDir("src")

java {
    sourceCompatibility = JavaVersion.VERSION_1_7
    targetCompatibility = JavaVersion.VERSION_1_7
}

bintrayReleaseFix()
publish {
    bintrayUser = BINTRAY_USER
    bintrayKey = BINTRAY_KEY
    dryRun = false
    repoName = RELEASE_ARTIFACT

    userOrg = RELEASE_USER
    groupId = RELEASE_GROUP
    artifactId = "$RELEASE_ARTIFACT-annotations"
    publishVersion = RELEASE_VERSION
    desc = RELEASE_DESC
    website = RELEASE_WEBSITE
}