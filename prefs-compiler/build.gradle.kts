import com.vanniktech.maven.publish.JavadocJar
import com.vanniktech.maven.publish.KotlinJvm

plugins {
    kotlin("jvm") version libs.versions.kotlin
    alias(libs.plugins.dokka)
    alias(libs.plugins.kotlinx.kover)
    alias(libs.plugins.maven.publish)
}

mavenPublishing.configure(KotlinJvm(JavadocJar.Dokka("dokkaJavadoc")))

dependencies {
    ktlint(libs.ktlint, ::configureKtlint)
    ktlint(libs.rulebook.ktlint)
    implementation(project(":prefs-annotations"))
    implementation(libs.auto.common)
    implementation(libs.guava.jre)
    implementation(libs.javapoet.dsl)
    compileOnly(files(org.gradle.internal.jvm.Jvm.current().toolsJar))
    testImplementation(kotlin("test-junit", libs.versions.kotlin.get()))
    testImplementation(libs.truth)
}
