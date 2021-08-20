@file:JvmMultifileClass
@file:JvmName("PrefsAndroidKt")
@file:Suppress("NOTHING_TO_INLINE", "DEPRECATION", "DeprecatedCallableAddReplaceWith")

package com.hendraanggrian.auto.prefs.android

import android.app.Fragment
import android.content.Context
import android.content.SharedPreferences
import androidx.annotation.AnyThread
import androidx.annotation.WorkerThread
import androidx.preference.PreferenceManager
import com.hendraanggrian.auto.prefs.BindPreference
import com.hendraanggrian.auto.prefs.EditablePreferences
import com.hendraanggrian.auto.prefs.PreferencesEditor
import com.hendraanggrian.auto.prefs.PreferencesSaver
import com.hendraanggrian.auto.prefs.Prefs

/**
 * Create a [AndroidPreferences] from source [SharedPreferences].
 * @param source native Android preferences.
 * @return preferences that reads/writes to [SharedPreferences].
 */
operator fun Prefs.get(source: SharedPreferences): AndroidPreferences = AndroidPreferences(source)

/**
 * Create a [AndroidPreferences] from source [Context].
 * @receiver application context.
 * @return preferences that reads/writes to [SharedPreferences].
 */
inline val Context.preferences: AndroidPreferences
    get() = Prefs[PreferenceManager.getDefaultSharedPreferences(this)]

/**
 * Create a [AndroidPreferences] from source [Fragment].
 * @receiver deprecated fragment.
 * @return preferences that reads/writes to [SharedPreferences].
 */
@Deprecated("Use support androidx.fragment.app.Fragment.")
inline val Fragment.preferences: AndroidPreferences
    get() = Prefs[PreferenceManager.getDefaultSharedPreferences(activity)]

/**
 * Create a [AndroidPreferences] from source [androidx.fragment.app.Fragment].
 * @receiver support fragment.
 * @return preferences that reads/writes to [SharedPreferences].
 */
inline val androidx.fragment.app.Fragment.preferences: AndroidPreferences
    get() = Prefs[PreferenceManager.getDefaultSharedPreferences(context)]

/**
 * Bind fields annotated with [BindPreference] from source [Context].
 * @receiver application context and also target fields' owner.
 * @return saver instance to apply changes made to the fields.
 * @throws RuntimeException when constructor of binding class cannot be found.
 */
inline fun Context.bindPreferences(): PreferencesSaver =
    Prefs.bind(preferences, this)

/**
 * Bind fields annotated with [BindPreference] from source [Fragment].
 * @receiver deprecated fragment and also target fields' owner.
 * @return saver instance to apply changes made to the fields.
 * @throws RuntimeException when constructor of binding class cannot be found.
 */
@Deprecated("Use support androidx.fragment.app.Fragment.")
inline fun Fragment.bindPreferences(): PreferencesSaver =
    Prefs.bind(preferences, this)

/**
 * Bind fields annotated with [BindPreference] from source [androidx.fragment.app.Fragment].
 * @receiver support fragment and also target fields' owner.
 * @return saver instance to apply changes made to the fields.
 * @throws RuntimeException when constructor of binding class cannot be found.
 */
inline fun androidx.fragment.app.Fragment.bindPreferences(): PreferencesSaver =
    Prefs.bind(preferences, this)

/** A wrapper of [SharedPreferences] with [EditablePreferences] implementation. */
class AndroidPreferences internal constructor(private val nativePreferences: SharedPreferences) :
    SharedPreferences by nativePreferences, EditablePreferences<AndroidPreferences.Editor> {

    override fun get(key: String): String? = getString(key, null)
    override fun getOrDefault(key: String, defaultValue: String): String = getString(key, defaultValue)!!

    override fun getBoolean(key: String): Boolean? = if (key !in nativePreferences) null else getBoolean(key, false)
    override fun getBooleanOrDefault(key: String, defaultValue: Boolean): Boolean = getBoolean(key, defaultValue)

    override fun getDouble(key: String): Nothing = throw UnsupportedOperationException()

    override fun getFloat(key: String): Float? = if (key !in nativePreferences) null else getFloat(key, 0f)
    override fun getFloatOrDefault(key: String, defaultValue: Float): Float = getFloat(key, defaultValue)

    override fun getLong(key: String): Long? = if (key !in nativePreferences) null else getLong(key, 0L)
    override fun getLongOrDefault(key: String, defaultValue: Long): Long = getLong(key, defaultValue)

    override fun getInt(key: String): Int? = if (key !in nativePreferences) null else getInt(key, 0)
    override fun getIntOrDefault(key: String, defaultValue: Int): Int = getInt(key, defaultValue)

    override fun getShort(key: String): Nothing = throw UnsupportedOperationException()
    override fun getByte(key: String): Nothing = throw UnsupportedOperationException()

    override val editor: Editor get() = Editor(nativePreferences.edit())

    /** A wrapper of [SharedPreferences.Editor] with [PreferencesEditor] implementation. */
    class Editor internal constructor(private val nativeEditor: SharedPreferences.Editor) : PreferencesEditor {
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

        override fun set(key: String, value: Short): Unit = throw UnsupportedOperationException()
        override fun set(key: String, value: Byte): Unit = throw UnsupportedOperationException()

        /**
         * Save changes blocking thread.
         * @see SharedPreferences.Editor.commit
         */
        @WorkerThread
        override fun save() {
            nativeEditor.commit()
        }

        /**
         * Save preferences changes in the background.
         * @see SharedPreferences.Editor.apply
         */
        @AnyThread
        fun saveAsync() = nativeEditor.apply()
    }
}
