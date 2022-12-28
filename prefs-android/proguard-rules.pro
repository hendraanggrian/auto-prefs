# library
-keep class com.hendraanggrian.auto.prefs.**

# generated classes
-keep public class * extends com.hendraanggrian.auto.prefs.internal.PreferencesBinding {
    public <init>(com.hendraanggrian.auto.prefs.ReadablePreferences);
}

# annotated fields
-keepclasseswithmembernames class * { @com.hendraanggrian.auto.prefs.BindPreference <fields>; }
