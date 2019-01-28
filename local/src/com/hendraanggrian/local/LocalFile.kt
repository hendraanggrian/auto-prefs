package com.hendraanggrian.local

import java.io.File
import java.util.Properties

infix fun Local.Companion.file(file: File): Local<*> = LocalFile(file)

private class LocalFile(private val file: File) : Local<LocalFile.Editor> {

    private val properties = Properties()

    init {
        if (!file.exists()) {
            file.createNewFile()
        }
        file.inputStream().use { properties.load(it) }
    }

    override fun contains(key: String): Boolean = properties.containsKey(key)

    override fun getString(key: String): String = properties.getProperty(key)

    override fun getEditor(): Editor = Editor()

    inner class Editor : Local.Editor {

        override fun setString(key: String, value: String?) {
            properties.setProperty(key, value)
        }

        override fun save() {
            file.outputStream().use {
                properties.save(it, null)
            }
        }
    }
}