package com.hendraanggrian.auto.prefs.jvm

import com.hendraanggrian.auto.prefs.BindPreference
import com.hendraanggrian.auto.prefs.PreferencesLogger
import com.hendraanggrian.auto.prefs.Prefs
import java.util.prefs.Preferences
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse

class JvmPreferencesTest {
    private lateinit var preferences: JvmPreferences
    @JvmField @BindPreference var string = ""
    @JvmField @BindPreference var integer = 0

    @BeforeTest
    fun setup() {
        Prefs.setLogger(PreferencesLogger.System)
        preferences =
            JvmPreferences(Preferences.userNodeForPackage(JvmPreferencesTest::class.java))
        preferences.clear()
        preferences.save()
    }

    @Test
    fun `Non-binding test`() {
        assertFalse("string" in preferences)
        assertFalse("integer" in preferences)

        preferences["string"] = "Hello World"
        preferences["integer"] = 10
        preferences.save()

        assertEquals("Hello World", preferences["string"])
        assertEquals(10, preferences.getInt("integer"))
    }

    @Test
    fun `Binding test`() {
        assertFalse("string" in preferences)
        assertFalse("integer" in preferences)

        val saver = bindPreferences(preferences)
        string = "Hello World"
        integer = 10
        saver.save()

        assertEquals("Hello World", preferences["string"])
        assertEquals(10, preferences.getInt("integer"))
    }
}
