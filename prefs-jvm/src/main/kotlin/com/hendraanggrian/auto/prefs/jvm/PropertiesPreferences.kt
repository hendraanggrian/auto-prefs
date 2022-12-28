@file:JvmMultifileClass
@file:JvmName("PrefsJvmKt")
@file:Suppress("NOTHING_TO_INLINE")

package com.hendraanggrian.auto.prefs.jvm

import com.hendraanggrian.auto.prefs.BindPreference
import com.hendraanggrian.auto.prefs.PreferencesSaver
import com.hendraanggrian.auto.prefs.Prefs
import com.hendraanggrian.auto.prefs.WritablePreferences
import java.io.File
import java.util.*

/**
 * Create a [PropertiesPreferences] from source [File].
 *
 * @param source file containing [Properties] elements.
 * @return preferences that reads/writes to [Properties].
 */
operator fun Prefs.get(source: File): PropertiesPreferences = PropertiesPreferences(source)

/**
 * Bind fields annotated with [BindPreference] from source [PropertiesPreferences].
 *
 * @receiver target fields' owner.
 * @param source preferences to extract
 * @return saver instance to apply changes made to the fields.
 * @throws RuntimeException when constructor of binding class cannot be found.
 */
inline fun <reified T : Any> T.bindPreferences(source: PropertiesPreferences): PreferencesSaver =
    Prefs.bind(source, this)

/** A wrapper of [Properties] with [WritablePreferences] implementation. */
class PropertiesPreferences internal constructor(private val targetFile: File) :
    Properties(), WritablePreferences {

    init {
        if (!targetFile.exists()) targetFile.createNewFile()
        targetFile.inputStream().use { load(it) }
    }

    override fun contains(key: String): Boolean = containsKey(key)

    override fun get(key: String): String? = getProperty(key)

    override fun getOrDefault(key: String, defaultValue: String?): String? =
        getProperty(key, defaultValue)

    override fun getBoolean(key: String): Nothing = throw UnsupportedOperationException()

    override fun getDouble(key: String): Nothing = throw UnsupportedOperationException()

    override fun getFloat(key: String): Nothing = throw UnsupportedOperationException()

    override fun getLong(key: String): Nothing = throw UnsupportedOperationException()

    override fun getInt(key: String): Nothing = throw UnsupportedOperationException()

    override fun getShort(key: String): Nothing = throw UnsupportedOperationException()

    override fun getByte(key: String): Nothing = throw UnsupportedOperationException()

    override fun remove(key: String) {
        super.remove(key as Any)
    }

    override fun set(key: String, value: String?) {
        setProperty(key, value)
    }

    override fun set(key: String, value: Boolean): Nothing = throw UnsupportedOperationException()

    override fun set(key: String, value: Double): Nothing = throw UnsupportedOperationException()

    override fun set(key: String, value: Float): Nothing = throw UnsupportedOperationException()

    override fun set(key: String, value: Long): Nothing = throw UnsupportedOperationException()

    override fun set(key: String, value: Int): Nothing = throw UnsupportedOperationException()

    override fun set(key: String, value: Short): Nothing = throw UnsupportedOperationException()

    override fun set(key: String, value: Byte): Nothing = throw UnsupportedOperationException()

    override fun save() = targetFile.outputStream().use { store(it, null) }
}
