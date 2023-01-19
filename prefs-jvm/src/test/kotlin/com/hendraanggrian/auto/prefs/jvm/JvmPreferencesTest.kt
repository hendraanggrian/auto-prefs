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
    @JvmField @BindPreference var int = 0

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
        assertFalse("int" in preferences)

        preferences["string"] = "Hello World"
        preferences["int"] = 10
        preferences.save()

        assertEquals("Hello World", preferences["string"])
        assertEquals(10, preferences.getInt("int"))
    }

    // @Test
    fun `Binding test`() {
        assertFalse("string" in preferences)
        assertFalse("int" in preferences)

        val saver = bindPreferences(preferences)
        string = "Hello World"
        int = 10
        saver.save()

        assertEquals("Hello World", preferences["string"])
        assertEquals(10, preferences.getInt("int"))
    }
}
