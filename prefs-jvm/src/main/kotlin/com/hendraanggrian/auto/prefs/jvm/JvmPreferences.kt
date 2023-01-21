@file:Suppress("NOTHING_TO_INLINE")

package com.hendraanggrian.auto.prefs.jvm

import com.hendraanggrian.auto.prefs.PreferencesSaver
import com.hendraanggrian.auto.prefs.Prefs
import com.hendraanggrian.auto.prefs.WritablePreferences
import java.io.OutputStream
import java.util.prefs.NodeChangeListener
import java.util.prefs.PreferenceChangeListener
import java.util.prefs.Preferences

/**
 * Bind fields annotated with `BindPreference` to target [Any] from [Preferences].
 *
 * @receiver platform-specific preferences.
 * @param target fields' owner.
 * @return saver instance to apply changes made to the fields.
 * @throws RuntimeException when constructor of binding class cannot be found.
 */
inline fun Preferences.bindTo(target: Any): PreferencesSaver =
    Prefs.bind(target) { JvmPreferences(this) }

/**
 * Bind fields annotated with `BindPreference` from source [Preferences] to target [Any].
 *
 * @receiver target fields' owner.
 * @param preferences platform-specific preferences.
 * @return saver instance to apply changes made to the fields.
 * @throws RuntimeException when constructor of binding class cannot be found.
 */
fun Any.bindPreferences(preferences: Preferences): PreferencesSaver = preferences.bindTo(this)

/** A wrapper of [Preferences] with [WritablePreferences] implementation. */
class JvmPreferences(private val nativePreferences: Preferences) :
    Preferences(), WritablePreferences {

    override fun put(key: String, value: String?): Unit = nativePreferences.put(key, value)

    override fun get(key: String, def: String?): String? = nativePreferences.get(key, def)

    override fun remove(key: String): Unit = nativePreferences.remove(key)

    override fun clear(): Unit = nativePreferences.clear()

    override fun putInt(key: String, value: Int): Unit = nativePreferences.putInt(key, value)

    override fun getInt(key: String, def: Int): Int = nativePreferences.getInt(key, def)

    override fun putLong(key: String, value: Long): Unit = nativePreferences.putLong(key, value)

    override fun getLong(key: String, def: Long): Long = nativePreferences.getLong(key, def)

    override fun putBoolean(key: String, value: Boolean): Unit =
        nativePreferences.putBoolean(key, value)

    override fun getBoolean(key: String, def: Boolean): Boolean =
        nativePreferences.getBoolean(key, def)

    override fun putFloat(key: String, value: Float): Unit = nativePreferences.putFloat(key, value)

    override fun getFloat(key: String, def: Float): Float = nativePreferences.getFloat(key, def)

    override fun putDouble(key: String, value: Double): Unit =
        nativePreferences.putDouble(key, value)

    override fun getDouble(key: String, def: Double): Double = nativePreferences.getDouble(key, def)

    override fun putByteArray(key: String, value: ByteArray?): Unit =
        nativePreferences.putByteArray(key, value)

    override fun getByteArray(key: String, def: ByteArray?): ByteArray? =
        nativePreferences.getByteArray(key, def)

    override fun keys(): Array<String> = nativePreferences.keys()

    override fun childrenNames(): Array<String> = nativePreferences.childrenNames()

    override fun parent(): JvmPreferences = JvmPreferences(nativePreferences.parent())

    override fun node(pathName: String?): JvmPreferences =
        JvmPreferences(nativePreferences.node(pathName))

    override fun nodeExists(pathName: String?): Boolean = nativePreferences.nodeExists(pathName)

    override fun removeNode(): Unit = nativePreferences.removeNode()

    override fun name(): String = nativePreferences.name()

    override fun absolutePath(): String = nativePreferences.absolutePath()

    override fun isUserNode(): Boolean = nativePreferences.isUserNode

    override fun toString(): String = nativePreferences.toString()

    override fun flush(): Unit = nativePreferences.flush()

    override fun sync(): Unit = nativePreferences.sync()

    override fun addPreferenceChangeListener(pcl: PreferenceChangeListener?): Unit =
        nativePreferences.addPreferenceChangeListener(pcl)

    override fun removePreferenceChangeListener(pcl: PreferenceChangeListener?): Unit =
        nativePreferences.removePreferenceChangeListener(pcl)

    override fun addNodeChangeListener(ncl: NodeChangeListener?): Unit =
        nativePreferences.removeNodeChangeListener(ncl)

    override fun removeNodeChangeListener(ncl: NodeChangeListener?): Unit =
        nativePreferences.removeNodeChangeListener(ncl)

    override fun exportNode(os: OutputStream?): Unit = nativePreferences.exportNode(os)

    override fun exportSubtree(os: OutputStream?): Unit = nativePreferences.exportSubtree(os)

    override fun contains(key: String): Boolean = nodeExists(key)

    override fun get(key: String): String? = get(key, null)

    override fun getOrDefault(key: String, defaultValue: String?): String? = get(key, defaultValue)

    override fun getBoolean(key: String): Boolean = getBoolean(key, false)

    override fun getBooleanOrDefault(key: String, defaultValue: Boolean): Boolean =
        getBoolean(key, defaultValue)

    override fun getDouble(key: String): Double = getDouble(key, 0.0)

    override fun getDoubleOrDefault(key: String, defaultValue: Double): Double =
        getDouble(key, defaultValue)

    override fun getFloat(key: String): Float = getFloat(key, 0f)

    override fun getFloatOrDefault(key: String, defaultValue: Float): Float =
        getFloat(key, defaultValue)

    override fun getLong(key: String): Long = getLong(key, 0L)

    override fun getLongOrDefault(key: String, defaultValue: Long): Long =
        getLong(key, defaultValue)

    override fun getInt(key: String): Int = getInt(key, 0)

    override fun getIntOrDefault(key: String, defaultValue: Int): Int = getInt(key, defaultValue)

    override fun getShort(key: String): Nothing = throw UnsupportedOperationException()

    override fun getByte(key: String): Nothing = throw UnsupportedOperationException()

    override fun set(key: String, value: String?): Unit = put(key, value)

    override fun set(key: String, value: Boolean): Unit = putBoolean(key, value)

    override fun set(key: String, value: Double): Unit = putDouble(key, value)

    override fun set(key: String, value: Float): Unit = putFloat(key, value)

    override fun set(key: String, value: Long): Unit = putLong(key, value)

    override fun set(key: String, value: Int): Unit = putInt(key, value)

    override fun set(key: String, value: Short): Nothing = throw UnsupportedOperationException()

    override fun set(key: String, value: Byte): Nothing = throw UnsupportedOperationException()

    override fun save() {
        sync()
        flush()
    }
}
