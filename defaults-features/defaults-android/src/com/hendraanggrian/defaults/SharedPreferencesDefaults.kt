package com.hendraanggrian.defaults

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.hendraanggrian.tools.defaults.Defaults

infix fun Defaults.Companion.sharedPreferences(sharedPreferences: SharedPreferences): Defaults<*> =
    SharedPreferencesDefaults(sharedPreferences)

@Suppress("NOTHING_TO_INLINE")
inline infix fun Defaults.Companion.sharedPreferences(context: Context): Defaults<*> =
    sharedPreferences(PreferenceManager.getDefaultSharedPreferences(context))

private class SharedPreferencesDefaults(private val preferences: SharedPreferences) :
    Defaults<SharedPreferencesDefaults.Editor>,
    SharedPreferences by preferences {

    override fun getString(key: String): String = getString(key, null).orEmpty()

    override fun getInt(key: String): Int = getInt(key, 0)

    override fun getLong(key: String): Long = getLong(key, 0L)

    override fun getFloat(key: String): Float = getFloat(key, 0f)

    override fun getBoolean(key: String): Boolean = getBoolean(key, false)

    override fun getEditor(): Editor =
        Editor(preferences.edit())

    class Editor(private val editor: SharedPreferences.Editor) : Defaults.Editor,
        SharedPreferences.Editor by editor {

        override fun minusAssign(key: String) {
            editor.remove(key)
        }

        override fun reset() {
            editor.clear()
        }

        override fun set(key: String, value: String?) {
            editor.putString(key, value)
        }

        override fun set(key: String, value: Int) {
            editor.putInt(key, value)
        }

        override fun set(key: String, value: Long) {
            editor.putLong(key, value)
        }

        override fun set(key: String, value: Float) {
            editor.putFloat(key, value)
        }

        override fun set(key: String, value: Boolean) {
            editor.putBoolean(key, value)
        }

        override fun save() {
            editor.commit()
        }

        override fun saveAsync() {
            editor.apply()
        }
    }
}