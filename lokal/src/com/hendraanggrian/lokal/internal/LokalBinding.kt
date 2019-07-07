package com.hendraanggrian.lokal.internal

import com.hendraanggrian.lokal.Lokal

/** Parent class of any class generated by `lokal-compiler`. */
abstract class LokalBinding(private val source: Lokal) : Lokal by source, Lokal.Saver {

    protected fun get(key: String, defaultValue: String?): String? =
        source[key] ?: defaultValue

    protected fun get(key: String, defaultValue: Boolean): Boolean =
        source.getBoolean(key) ?: defaultValue

    protected fun get(key: String, defaultValue: Double): Double =
        source.getDouble(key) ?: defaultValue

    protected fun get(key: String, defaultValue: Float): Float =
        source.getFloat(key) ?: defaultValue

    protected fun get(key: String, defaultValue: Long): Long =
        source.getLong(key) ?: defaultValue

    protected fun get(key: String, defaultValue: Int): Int =
        source.getInt(key) ?: defaultValue

    protected fun get(key: String, defaultValue: Short): Short =
        source.getShort(key) ?: defaultValue

    protected fun get(key: String, defaultValue: Byte): Byte =
        source.getByte(key) ?: defaultValue

    override val editor: Lokal.Editor
        get() = when (source) {
            is Lokal.Editor -> source
            else -> source.editor
        }
}