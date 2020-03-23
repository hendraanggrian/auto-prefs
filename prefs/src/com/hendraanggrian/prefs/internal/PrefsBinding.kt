package com.hendraanggrian.prefs.internal

import com.hendraanggrian.prefs.EditablePrefs
import com.hendraanggrian.prefs.Prefs
import com.hendraanggrian.prefs.ReadablePrefs
import com.hendraanggrian.prefs.WritablePrefs

/**
 * Parent class of any class generated by `local-compiler` (should they are being used in the project).
 * It behaves similarly as [ReadablePrefs] with simpler API for generated sources to use.
 */
abstract class PrefsBinding(private val source: ReadablePrefs) : Prefs.Saver {

    protected fun get(key: String, defaultValue: String?): String? = source[key] ?: defaultValue
    protected fun get(key: String, defaultValue: Boolean): Boolean = source.getBoolean(key) ?: defaultValue
    protected fun get(key: String, defaultValue: Double): Double = source.getDouble(key) ?: defaultValue
    protected fun get(key: String, defaultValue: Float): Float = source.getFloat(key) ?: defaultValue
    protected fun get(key: String, defaultValue: Long): Long = source.getLong(key) ?: defaultValue
    protected fun get(key: String, defaultValue: Int): Int = source.getInt(key) ?: defaultValue
    protected fun get(key: String, defaultValue: Short): Short = source.getShort(key) ?: defaultValue
    protected fun get(key: String, defaultValue: Byte): Byte = source.getByte(key) ?: defaultValue

    protected val editor: Prefs.Editor
        get() = when (source) {
            is WritablePrefs -> source
            is EditablePrefs<*> -> source.editor
            else -> error("Unrecognizable prefs instance")
        }
}