package com.hendraanggrian.auto.prefs

import com.google.common.truth.Truth.assertThat
import kotlin.test.Test

class PreferencesLoggerTest {

    @Test
    fun setLogger() {
        val infos = mutableListOf<String>()
        val warns = mutableListOf<String>()
        Prefs.setLogger(object : PreferencesLogger {
            override fun info(message: String) {
                infos += message
            }

            override fun warn(message: String) {
                warns += message
            }
        })
        Prefs.info("A")
        Prefs.info("B")
        Prefs.warn("C")
        assertThat(infos).containsExactly("A", "B")
        assertThat(warns).containsExactly("C")
    }
}
