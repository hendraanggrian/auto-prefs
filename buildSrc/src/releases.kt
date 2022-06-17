const val SDK_MIN = 14
const val SDK_TARGET = 30

const val RELEASE_GROUP = "com.hendraanggrian.auto"
const val RELEASE_ARTIFACT = "prefs"
const val RELEASE_VERSION = "0.1-SNAPSHOT"
const val RELEASE_DESCRIPTION = "Preferences field binder for JVM and Android"
const val RELEASE_URL = "https://github.com/hendraanggrian/$RELEASE_ARTIFACT"

fun getGithubRemoteUrl(artifact: String = RELEASE_ARTIFACT) =
    `java.net`.URL("$RELEASE_URL/tree/main/$artifact/src")
