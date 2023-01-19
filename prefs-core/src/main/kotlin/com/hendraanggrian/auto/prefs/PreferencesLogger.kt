package com.hendraanggrian.auto.prefs

/** Interface that determines preferences logging behavior. */
interface PreferencesLogger {
    companion object

    /**
     * Log message in information channel.
     *
     * @param message text to print.
     */
    fun info(message: String)

    /**
     * Log message in warning channel.
     *
     * @param message text to print.
     */
    fun warn(message: String)
}
