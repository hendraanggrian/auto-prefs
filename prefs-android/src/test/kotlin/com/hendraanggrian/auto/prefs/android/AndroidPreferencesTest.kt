package com.hendraanggrian.auto.prefs.android

import com.hendraanggrian.auto.prefs.PreferencesLogger
import com.hendraanggrian.auto.prefs.Prefs
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

@RunWith(RobolectricTestRunner::class)
class AndroidPreferencesTest {
    private lateinit var preferences: AndroidPreferences

    @BeforeTest
    fun setup() {
        Prefs.setLogger(PreferencesLogger.Android)
        val activity = Robolectric.buildActivity(TestActivity::class.java).setup().get()
        preferences = activity.preferences
        preferences.edit { clear() }
    }

    @Test
    fun sharedPreferences() {
        assertNull(preferences["name"])
        assertNull(preferences.getInt("age"))
        preferences.edit {
            this["name"] = "Hendra"
            this["age"] = 25
        }
        assertEquals("Hendra", preferences["name"])
        assertEquals(25, preferences.getInt("age"))
    }
}
