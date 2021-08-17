package com.hendraanggrian.prefs.jvm

import com.hendraanggrian.prefs.PreferencesLogger
import com.hendraanggrian.prefs.Prefs
import com.hendraanggrian.prefs.ReadablePreferences
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class JvmPreferencesTest {
    private lateinit var preferences: JvmPreferences

    @BeforeTest fun createTest() {
        Prefs.setLogger(PreferencesLogger.System)
        preferences = Prefs.userNode<ReadablePreferences>()
    }

    @Test fun jvm() {
        preferences["name"] = "Hendra"
        preferences.save()
        assertEquals("Hendra", preferences["name"])
    }
}