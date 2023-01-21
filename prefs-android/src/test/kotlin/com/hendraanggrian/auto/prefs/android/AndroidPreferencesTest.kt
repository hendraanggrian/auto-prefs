package com.hendraanggrian.auto.prefs.android

import androidx.preference.PreferenceManager
import com.hendraanggrian.auto.prefs.BindPreference
import com.hendraanggrian.auto.prefs.PreferencesLogger
import com.hendraanggrian.auto.prefs.Prefs
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse

@RunWith(RobolectricTestRunner::class)
class AndroidPreferencesTest {
    private lateinit var preferences: AndroidPreferences
    @JvmField @BindPreference var string = ""
    @JvmField @BindPreference var integer = 0

    @BeforeTest
    fun setup() {
        Prefs.setLogger(PreferencesLogger.Android)
        val activity = Robolectric.buildActivity(TestActivity::class.java).setup().get()
        preferences = AndroidPreferences(PreferenceManager.getDefaultSharedPreferences(activity))
        preferences.edit { clear() }
    }

    @Test
    fun `Non-binding test`() {
        assertFalse("string" in preferences)
        assertFalse("integer" in preferences)

        preferences.edit {
            this["string"] = "Hello World"
            this["integer"] = 10
        }

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
