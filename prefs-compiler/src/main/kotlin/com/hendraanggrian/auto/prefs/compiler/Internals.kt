@file:Suppress("UnstableApiUsage")

package com.hendraanggrian.auto.prefs.compiler

import com.google.auto.common.MoreElements
import com.hendraanggrian.javapoet.classNameOf
import javax.lang.model.element.TypeElement

private const val PACKAGE_NAME = "com.hendraanggrian.auto.prefs"
internal const val FILE_COMMENT = "Prefs generated class, do not modify! " +
    "https://github.com/hendraanggrian/auto-prefs/"

internal const val REF_TARGET = "target"
internal const val REF_SOURCE = "source"
internal const val REF_EDITOR = "editor"

internal val CLS_PREFERENCES_BINDING = classNameOf("$PACKAGE_NAME.internal", "PreferencesBinding")
internal val CLS_READABLE_PREFERENCES = classNameOf(PACKAGE_NAME, "ReadablePreferences")
internal val CLS_PREFERENCES_EDITOR = classNameOf(PACKAGE_NAME, "PreferencesEditor")

internal val TypeElement.measuredPackageName: String
    get() = MoreElements.getPackage(this).qualifiedName.toString()

internal val TypeElement.measuredName: String
    get() {
        val enclosings = mutableListOf(simpleName.toString())
        var typeElement = this
        while (typeElement.nestingKind.isNested) {
            typeElement = MoreElements.asType(typeElement.enclosingElement)
            enclosings.add(typeElement.simpleName.toString())
        }
        enclosings.reverse()
        var typeName = enclosings.first()
        for (i in 1 until enclosings.size) {
            typeName += "$${enclosings[i]}"
        }
        return "${typeName}_PreferencesBinding"
    }
