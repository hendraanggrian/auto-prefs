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
    @JvmField @BindPreference var int = 0

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
        assertFalse("int" in preferences)

        preferences.edit {
            this["string"] = "Hello World"
            this["int"] = 10
        }

        assertEquals("Hello World", preferences["string"])
        assertEquals(10, preferences.getInt("int"))
    }

    // @Test
    fun test() {
        assertEquals("", string)
        assertEquals(0, int)

        val saver = bindPreferences(preferences)
        string = "Hello World"
        int = 10
        saver.save()

        assertEquals("Hello World", preferences["string"])
        assertEquals(10, preferences.getInt("int"))
    }
}
