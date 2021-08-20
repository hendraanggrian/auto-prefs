[![version](https://img.shields.io/maven-central/v/com.hendraanggrian.auto/prefs)](https://search.maven.org/artifact/com.hendraanggrian.auto/prefs)
[![analysis](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081)](https://ktlint.github.io)

Prefs
=====

Local settings library that runs in plain Java and Android.
* Retrieve and assign values with Kotlin operator functions.
* Annotation processor to bind preferences' values to JVM fields, similar to [ButterKnife](https://github.com/JakeWharton/butterknife).

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

Download
--------

```gradle
repositories {
    mavenCentral()
}

dependencies {
    compile "com.hendraanggrian.auto:prefs-jvm:$version" // for non-Android project
    api "com.hendraanggrian.auto:prefs-android:$version" // for Android project
    annotationProcessor "com.hendraanggrian.auto:prefs-compiler:$version" // or kapt
}
```

Snapshots of the development version are available in [Sonatype's snapshots repository](https://s01.oss.sonatype.org/content/repositories/snapshots).

Usage
-----
Coming soon.
