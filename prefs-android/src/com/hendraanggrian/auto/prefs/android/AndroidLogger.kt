@file:JvmMultifileClass
@file:JvmName("PrefsAndroidKt")

package com.hendraanggrian.auto.prefs.android

import android.util.Log
import com.hendraanggrian.auto.prefs.PreferencesLogger

private const val TAG = "Prefs"

/** Logger that prints to [Log], matching its supported channels. */
val PreferencesLogger.Companion.Android: PreferencesLogger
    get() = object : PreferencesLogger {
        override fun info(message: String) {
            Log.i(TAG, message)
        }

        override fun warn(message: String) {
            Log.w(TAG, message)
        }
    }
