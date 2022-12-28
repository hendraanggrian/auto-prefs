[![Travis CI](https://img.shields.io/travis/com/hendraanggrian/auto-prefs)](https://travis-ci.com/github/hendraanggrian/auto-prefs/)
[![Codecov](https://img.shields.io/codecov/c/github/hendraanggrian/auto-prefs)](https://codecov.io/gh/hendraanggrian/auto-prefs/)
[![Maven Central](https://img.shields.io/maven-central/v/com.hendraanggrian.auto/prefs)](https://search.maven.org/artifact/com.hendraanggrian.auto/prefs/)
[![Nexus Snapshot](https://img.shields.io/nexus/s/com.hendraanggrian.auto/prefs?server=https%3A%2F%2Fs01.oss.sonatype.org)](https://s01.oss.sonatype.org/content/repositories/snapshots/com/hendraanggrian/auto/prefs/)
[![OpenJDK](https://img.shields.io/badge/jdk-1.8%2B-informational)](https://openjdk.java.net/projects/jdk8/)
[![Android SDK](https://img.shields.io/badge/sdk-14%2B-informational)](https://developer.android.com/studio/releases/platforms/#4.0)

# Prefs

Local settings library that runs in plain Java and Android.

- Retrieve and assign values with Kotlin operator functions.
- Annotation processor to bind preferences' values to JVM fields, similar
  to [ButterKnife](https://github.com/JakeWharton/butterknife/).

```kotlin
@JvmField @BindPreference var name: String = ""
@JvmField @BindPreference var age: Int = 0

lateinit var saver: PreferencesSaver = bindPreferences()

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

Coming soon.
