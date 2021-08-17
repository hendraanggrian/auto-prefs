[![version](https://img.shields.io/maven-central/v/com.hendraanggrian.auto/prefs)](https://search.maven.org/artifact/com.hendraanggrian.auto/prefs)
[![analysis](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081)](https://ktlint.github.io)

Prefs
=====
Local settings library that runs in plain Java and Android.
* Retrieve and assign values with Kotlin operator functions.
* Annotation processor to bind preferences' values to JVM fields, similar to [ButterKnife].

```kotlin
@BindPreference lateinit var name: String
@BindPreference @JvmField var age: Int = 0

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
    jcenter()
}

dependencies {
    compile "com.hendraanggrian.prefs:prefs-jvm:$version" // for non-Android project
    api "com.hendraanggrian.prefs:prefs-android:$version" // for Android project

    // optional annotation processor
    annotationProcessor "com.hendraanggrian.prefs:prefs-compiler:$version" // or kapt
}
```

Usage
-----
Coming soon.

[ButterKnife]: https://github.com/JakeWharton/butterknife
