internal typealias Plugins = org.gradle.plugin.use.PluginDependenciesSpec
internal typealias Dependencies = org.gradle.api.artifacts.dsl.DependencyHandler

const val VERSION_MULTIDEX = "2.0.1"
const val VERSION_ANDROIDX = "1.3.0"
const val VERSION_ANDROIDX_TEST = "1.4.0"
const val VERSION_ANDROIDX_JUNIT = "1.1.3"
const val VERSION_ANDROIDX_TRUTH = "1.4.0"
const val VERSION_ESPRESSO = "3.4.0"
val Dependencies.android get() = "com.android.tools.build:gradle:7.0.0"
fun Plugins.android(submodule: String) = id("com.android.$submodule")
fun Dependencies.material(version: String = VERSION_ANDROIDX) = "com.google.android.material:material:$version"
fun Dependencies.androidx(repository: String, module: String = repository, version: String = VERSION_ANDROIDX) =
    "androidx.$repository:$module:$version"

const val VERSION_JAVAFX_PLUGIN = "0.0.10"
val Dependencies.javafx get() = "org.openjfx:javafx-plugin:$VERSION_JAVAFX_PLUGIN"
val Plugins.javafx get() = id("org.openjfx.javafxplugin")

const val VERSION_KOTLIN = "1.5.21"
const val VERSION_COROUTINES = "1.5.1"
val Dependencies.dokka get() = "org.jetbrains.dokka:dokka-gradle-plugin:1.5.0"
val Plugins.dokka get() = id("org.jetbrains.dokka")
fun Dependencies.kotlinx(module: String, version: String? = null) =
    "org.jetbrains.kotlinx:kotlinx-$module${version?.let { ":$it" }.orEmpty()}"

val Dependencies.`git-publish` get() = "org.ajoberstar:gradle-git-publish:2.1.3"
val Plugins.`git-publish` get() = id("org.ajoberstar.git-publish")

const val VERSION_COMMONS_LANG = "3.11"
fun Dependencies.apache(repo: String, module: String, version: String) = "org.apache.$repo:$module:$version"

const val VERSION_AUTOCOMMON = "0.10"
const val VERSION_GUAVA = "28.2-jre"
const val VERSION_TRUTH = "1.1.3"
fun Dependencies.google(repo: String? = null, module: String, version: String) =
    "com.google${repo?.let { ".$it" }.orEmpty()}:$module:$version"

const val VERSION_KTFX = "8.0.0-SNAPSHOT"
const val VERSION_JAVAPOET_KTX = "0.1-SNAPSHOT"
fun Dependencies.hendraanggrian(module: String, version: String) = "com.hendraanggrian:$module:$version"
fun Dependencies.hendraanggrian(repo: String, module: String, version: String) = "com.hendraanggrian.$repo:$module:$version"
fun Plugins.hendraanggrian(module: String) = id("com.hendraanggrian.$module")
