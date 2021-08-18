package com.example.prefs

import android.app.Application
import com.hendraanggrian.prefs.PreferencesLogger
import com.hendraanggrian.prefs.Prefs
import com.hendraanggrian.prefs.android.Android

class ExampleApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Prefs.setLogger(PreferencesLogger.Android)
    }
}