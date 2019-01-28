package com.hendraanggrian.local

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

infix fun Local.Companion.android(preferences: SharedPreferences): Local<*> =
    AndroidLocal(preferences)

@Suppress("NOTHING_TO_INLINE")
inline infix fun Local.Companion.android(context: Context): Local<*> =
    this android PreferenceManager.getDefaultSharedPreferences(context)

private class AndroidLocal(private val preferences: SharedPreferences) :
    Local<AndroidLocal.Editor>,
    SharedPreferences by preferences {

    override fun getString(key: String): String = preferences.getString(key, null).orEmpty()

    override fun getInt(key: String): Int = preferences.getInt(key, 0)

    override fun getLong(key: String, defValue: Long): Long = preferences.getLong(key, 0L)

    override fun getFloat(key: String?, defValue: Float): Float = preferences.getFloat(key, 0f)

    override fun getBoolean(key: String?, defValue: Boolean): Boolean =
        preferences.getBoolean(key, false)

    override fun getEditor(): Editor = Editor(preferences.edit())

    class Editor(private val editor: SharedPreferences.Editor) : Local.Editor,
        SharedPreferences.Editor by editor {

        override fun setString(key: String, value: String?) {
            editor.putString(key, value)
        }

        override fun setInt(key: String, value: Int) {
            editor.putInt(key, value)
        }

        override fun setLong(key: String, value: Long) {
            editor.putLong(key, value)
        }

        override fun setFloat(key: String, value: Float) {
            editor.putFloat(key, value)
        }

        override fun setBoolean(key: String, value: Boolean) {
            editor.putBoolean(key, value)
        }

        override fun save() {
            editor.commit()
        }
    }
}