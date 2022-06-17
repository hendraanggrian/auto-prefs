plugins {
    `git-publish`
}

gitPublish {
    repoUri.set("git@github.com:hendraanggrian/$RELEASE_ARTIFACT.git")
    branch.set("gh-pages")
    contents.from(
        "src",
        "../$RELEASE_ARTIFACT-core/build/dokka",
        "../$RELEASE_ARTIFACT-android/build/dokka",
        "../$RELEASE_ARTIFACT-jvm/build/dokka"
    )
}

tasks {
    register("clean") {
        delete(buildDir)
    }
    gitPublishCopy {
        dependsOn(
            ":$RELEASE_ARTIFACT-core:dokkaHtml",
            ":$RELEASE_ARTIFACT-android:dokkaHtml",
            ":$RELEASE_ARTIFACT-jvm:dokkaHtml"
        )
    }
}
