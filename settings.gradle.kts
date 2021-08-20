include("prefs-annotations")
include("prefs-core")
include("prefs-jvm")
include("prefs-android")
include("prefs-compiler")

include("website")
includeDir("samples")

fun includeDir(path: String) = file(path)
    .listFiles()!!
    .filter { it.isDirectory }
    .forEach { include("$path:${it.name}") }
