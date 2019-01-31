const val RELEASE_USER = "hendraanggrian"
const val RELEASE_ARTIFACT = "defaults"
const val RELEASE_GROUP = "com.$RELEASE_USER.$RELEASE_ARTIFACT"
const val RELEASE_VERSION = "0.2-rc3"
const val RELEASE_DESC = "Local settings library for JVM and Android"
const val RELEASE_WEBSITE = "https://github.com/$RELEASE_USER/$RELEASE_ARTIFACT"

val BINTRAY_USER = System.getenv("BINTRAY_USER")
val BINTRAY_KEY = System.getenv("BINTRAY_KEY")