package com.example.prefs.test

import com.hendraanggrian.prefs.BindPreference

class Target2 : Target1() {
    @BindPreference lateinit var test2: String
}