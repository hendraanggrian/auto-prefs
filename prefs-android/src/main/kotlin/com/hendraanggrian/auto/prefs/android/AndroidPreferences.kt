@file:Suppress("NOTHING_TO_INLINE", "DEPRECATION")

package com.hendraanggrian.auto.prefs.android

import android.app.Fragment
import android.content.Context
import android.content.SharedPreferences
import androidx.annotation.AnyThread
import androidx.annotation.WorkerThread
import androidx.preference.PreferenceManager
import com.hendraanggrian.auto.prefs.EditablePreferences
import com.hendraanggrian.auto.prefs.PreferencesEditor
import com.hendraanggrian.auto.prefs.PreferencesSaver
import com.hendraanggrian.auto.prefs.Prefs

/**
 * Bind fields annotated with [com.hendraanggrian.auto.prefs.BindPreference] to target [Any]
 * from [SharedPreferences].
 *
 * @receiver platform-specific preferences.
 * @param target fields' owner.
 * @return saver instance to apply changes made to the fields.
 * @throws RuntimeException when constructor of binding class cannot be found.
 */
inline fun SharedPreferences.bindTo(target: Any): PreferencesSaver =
    Prefs.bind(target) { AndroidPreferences(this) }

/**
 * Bind fields annotated with [com.hendraanggrian.auto.prefs.BindPreference] from
 * source [SharedPreferences] to target [Any].
 *
 * @receiver target fields' owner.
 * @param preferences platform-specific preferences.
 * @return saver instance to apply changes made to the fields.
 * @throws RuntimeException when constructor of binding class cannot be found.
 */
fun Any.bindPreferences(preferences: SharedPreferences): PreferencesSaver = preferences.bindTo(this)

/**
 * Bind fields annotated with [com.hendraanggrian.auto.prefs.BindPreference] from source and to
 * target [Context].
 *
 * @receiver application context and also target fields' owner.
 * @param preferences platform-specific preferences.
 * @return saver instance to apply changes made to the fields.
 * @throws RuntimeException when constructor of binding class cannot be found.
 */
fun Context.bindPreferences(
    preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
): PreferencesSaver = preferences.bindTo(this)

/**
 * Bind fields annotated with [com.hendraanggrian.auto.prefs.BindPreference] from source and to
 * target [Fragment].
 *
 * @receiver deprecated fragment and also target fields' owner.
 * @param preferences platform-specific preferences.
 * @return saver instance to apply changes made to the fields.
 * @throws RuntimeException when constructor of binding class cannot be found.
 */
fun Fragment.bindPreferences(
    preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity)
): PreferencesSaver = preferences.bindTo(this)

/**
 * Bind fields annotated with [com.hendraanggrian.auto.prefs.BindPreference] from source and to
 * target [androidx.fragment.app.Fragment].
 *
 * @receiver support fragment and also target fields' owner.
 * @param preferences platform-specific preferences.
 * @return saver instance to apply changes made to the fields.
 * @throws RuntimeException when constructor of binding class cannot be found.
 */
fun androidx.fragment.app.Fragment.bindPreferences(
    preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context!!)
): PreferencesSaver = preferences.bindTo(this)

/** A wrapper of [SharedPreferences] with [EditablePreferences] implementation. */
class AndroidPreferences(private val nativePreferences: SharedPreferences) :
    SharedPreferences by nativePreferences, EditablePreferences<AndroidPreferences.Editor> {

    override fun get(key: String): String? = getString(key, null)

    override fun getOrDefault(key: String, defaultValue: String?): String? =
        getString(key, defaultValue)

    override fun getBoolean(key: String): Boolean? =
        if (key !in nativePreferences) null else getBoolean(key, false)

    override fun getBooleanOrDefault(key: String, defaultValue: Boolean): Boolean =
        getBoolean(key, defaultValue)

    override fun getDouble(key: String): Nothing = throw UnsupportedOperationException()

    override fun getFloat(key: String): Float? =
        if (key !in nativePreferences) null else getFloat(key, 0f)

    override fun getFloatOrDefault(key: String, defaultValue: Float): Float =
        getFloat(key, defaultValue)

    override fun getLong(key: String): Long? =
        if (key !in nativePreferences) null else getLong(key, 0L)

    override fun getLongOrDefault(key: String, defaultValue: Long): Long =
        getLong(key, defaultValue)

    override fun getInt(key: String): Int? = if (key !in nativePreferences) null else getInt(key, 0)

    override fun getIntOrDefault(key: String, defaultValue: Int): Int = getInt(key, defaultValue)

    override fun getShort(key: String): Nothing = throw UnsupportedOperationException()

    override fun getByte(key: String): Nothing = throw UnsupportedOperationException()

    override val editor: Editor get() = Editor(nativePreferences.edit())

    /** A wrapper of [SharedPreferences.Editor] with [PreferencesEditor] implementation. */
    class Editor internal constructor(private val nativeEditor: SharedPreferences.Editor) :
        PreferencesEditor {

        override fun remove(key: String) {
            nativeEditor.remove(key)
        }

        override fun clear() {
            nativeEditor.clear()
        }

        override fun set(key: String, value: String?) {
            nativeEditor.putString(key, value)
        }

        override fun set(key: String, value: Boolean) {
            nativeEditor.putBoolean(key, value)
        }

        override fun set(key: String, value: Double): Unit = throw UnsupportedOperationException()

        override fun set(key: String, value: Float) {
            nativeEditor.putFloat(key, value)
        }

        override fun set(key: String, value: Long) {
            nativeEditor.putLong(key, value)
        }

        override fun set(key: String, value: Int) {
            nativeEditor.putInt(key, value)
        }

        override fun set(key: String, value: Short): Nothing = throw UnsupportedOperationException()

        override fun set(key: String, value: Byte): Nothing = throw UnsupportedOperationException()

        /**
         * Save changes blocking thread.
         *
         * @see SharedPreferences.Editor.commit
         */
        @WorkerThread
        override fun save() {
            nativeEditor.commit()
        }

        /**
         * Save preferences changes in the background.
         *
         * @see SharedPreferences.Editor.apply
         */
        @AnyThread
        fun saveAsync(): Unit = nativeEditor.apply()
    }
}
