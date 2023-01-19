[![Travis CI](https://img.shields.io/travis/com/hendraanggrian/auto-prefs)](https://travis-ci.com/github/hendraanggrian/auto-prefs/)
[![Codecov](https://img.shields.io/codecov/c/github/hendraanggrian/auto-prefs)](https://codecov.io/gh/hendraanggrian/auto-prefs/)
[![Maven Central](https://img.shields.io/maven-central/v/com.hendraanggrian.auto/prefs-core)](https://search.maven.org/artifact/com.hendraanggrian.auto/prefs-core/)
[![Nexus Snapshot](https://img.shields.io/nexus/s/com.hendraanggrian.auto/prefs-core?server=https%3A%2F%2Fs01.oss.sonatype.org)](https://s01.oss.sonatype.org/content/repositories/snapshots/com/hendraanggrian/auto/prefs-core/)
[![OpenJDK](https://img.shields.io/badge/jdk-1.8%2B-informational)](https://openjdk.java.net/projects/jdk8/)
[![Android SDK](https://img.shields.io/badge/sdk-14%2B-informational)](https://developer.android.com/studio/releases/platforms/#4.0)

# Auto Prefs

Local settings library that runs in plain Java and Android.

- Retrieve and assign values with Kotlin operator functions.
- Annotation processor to bind preferences' values to JVM fields, similar
  to [ButterKnife](https://github.com/JakeWharton/butterknife/).

```kotlin
@JvmField @BindPreference var name = ""
@JvmField @BindPreference var age = 0
lateinit var saver = bindPreferences()

fun applyChanges(person: Person) {
    name = person.name
    age = person.age
    saver.save()
}
```

## Download

```gradle
repositories {
    mavenCentral()
}
dependencies {
    implementation "com.hendraanggrian.auto:prefs-jvm:$version" // for plain Java project
    implementation "com.hendraanggrian.auto:prefs-android:$version" // for Android project
    annotationProcessor "com.hendraanggrian.auto:prefs-compiler:$version" // or kapt for Kotlin
}
```

## Usage

### JVM

The native preference manager of Java applet is [Preferences](https://docs.oracle.com/javase/7/docs/api/java/util/prefs/Preferences.html)
.

```kotlin
class MyClass {
    @JvmField @BindPreference var name = ""
    @JvmField @BindPreference var age = 0
    lateinit var saver: PreferencesSaver

    init {
      val preferences = Preferences.userNodeForPackage(MyClass::class.java)
      saver = bindPreferences(preferences)
    }

    fun applyChanges(person: Person) {
        name = person.name
        age = person.age
        saver.save()
    }
}
```

### Android

In Android, [SharedPreferences](https://developer.android.com/reference/android/content/SharedPreferences)
can be created from `Context` or `Fragment`.

```kotlin
class MyActivity : Activity() {
    @JvmField @BindPreference var name = ""
    @JvmField @BindPreference var age = 0
    lateinit var saver: PreferencesSaver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        saver = bindPreferences()
    }

    fun applyChanges(person: Person) {
        name = person.name
        age = person.age
        saver.save()
    }
}
```
