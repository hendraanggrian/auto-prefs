package com.example.prefs.test

import com.hendraanggrian.prefs.BindPreference

class Target2 : Target1() {
    @JvmField @BindPreference var test2: String = ""
}