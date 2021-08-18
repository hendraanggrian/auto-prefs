include("prefs-core")
include("prefs-annotations")
include("prefs-compiler")
include("prefs-jvm")
include("prefs-android")

include("website")
includeDir("samples")

fun includeDir(path: String) = file(path)
    .listFiles()!!
    .filter { it.isDirectory }
    .forEach { include("$path:${it.name}") }
