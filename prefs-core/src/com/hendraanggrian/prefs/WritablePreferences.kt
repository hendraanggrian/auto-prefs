package com.hendraanggrian.prefs

/**
 * Subinterface of [ReadablePreferences] that, in contrast to [EditablePreferences],
 * can directly modify preferences since it also inherits [PreferencesEditor].
 */
interface WritablePreferences : ReadablePreferences, PreferencesEditor
