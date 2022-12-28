package com.hendraanggrian.auto.prefs.jvm

import com.hendraanggrian.auto.prefs.PreferencesLogger
import com.hendraanggrian.auto.prefs.Prefs
import com.hendraanggrian.auto.prefs.ReadablePreferences
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class JvmPreferencesTest {
    private lateinit var preferences: JvmPreferences

    @BeforeTest
    fun setup() {
        Prefs.setLogger(PreferencesLogger.System)
        preferences = Prefs.userNode<ReadablePreferences>()
    }

    @Test
    fun jvm() {
        preferences["name"] = "Hendra"
        preferences.save()
        assertEquals("Hendra", preferences["name"])
    }
}
