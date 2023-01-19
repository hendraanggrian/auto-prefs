package com.hendraanggrian.auto.prefs

import java.lang.reflect.Constructor
import java.lang.reflect.InvocationTargetException
import java.util.*

/**
 * Create an instance of [WritablePreferences] or [EditablePreferences] and bind its values to JVM
 * fields with this object.
 */
object Prefs {
    private var BINDINGS: MutableMap<Class<*>, Constructor<PreferencesSaver>>? = null
    private var LOGGER: PreferencesLogger? = null

    /**
     * Set logging behavior using [PreferencesLogger] instance.
     *
     * @param logger custom or pre-determined logger (e.g: [PreferencesLogger.System]).
     */
    fun setLogger(logger: PreferencesLogger) {
        LOGGER = logger
    }

    internal fun info(message: String) {
        LOGGER?.info(message)
    }

    internal fun warn(message: String) {
        LOGGER?.warn(message)
    }

    /**
     * Bind fields annotated with [BindPreference] with values from this preferences.
     *
     * @param target fields' owner.
     * @param getSource callable to return platform-specific preferences.
     * @return saver instance to apply changes made to the fields.
     * @throws RuntimeException when constructor of binding class cannot be found.
     */
    fun bind(target: Any, getSource: () -> ReadablePreferences): PreferencesSaver {
        val targetClass = target.javaClass
        info("Looking up binding for ${targetClass.simpleName}")
        val constructor = findBindingConstructor(targetClass)
        if (constructor == null) {
            info("${targetClass.simpleName} binding not found, returning empty saver.")
            return PreferencesSaver.EMPTY
        }
        try {
            return constructor.newInstance(getSource(), target)
        } catch (e: IllegalAccessException) {
            throw RuntimeException("Unable to invoke $constructor", e)
        } catch (e: InstantiationException) {
            throw RuntimeException("Unable to invoke $constructor", e)
        } catch (e: InvocationTargetException) {
            when (val cause = e.cause) {
                is RuntimeException -> throw cause
                is Error -> throw cause
                else -> throw RuntimeException("Unable to create binding instance.", cause)
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun findBindingConstructor(`class`: Class<*>): Constructor<PreferencesSaver>? {
        if (BINDINGS == null) {
            BINDINGS = WeakHashMap()
        }
        var binding = BINDINGS!![`class`]
        if (binding != null) {
            info("HIT: Cache found in binding weak map.")
            return binding
        }
        val className = `class`.name
        if (className.startsWith("android.") || className.startsWith("androidx.") ||
            className.startsWith("java.") || className.startsWith("kotlin.")
        ) {
            info("MISS: Reached framework class. Abandoning search.")
            return null
        }
        try {
            binding = `class`.classLoader!!
                .loadClass(className + BindPreference.SUFFIX)
                .getConstructor(ReadablePreferences::class.java, `class`)
                as Constructor<PreferencesSaver>
            info("HIT: Loaded binding class, caching in weak map.")
        } catch (e: ClassNotFoundException) {
            val superclass = `class`.superclass!!
            warn("Not found. Trying superclass ${superclass.name} ...")
            binding = findBindingConstructor(superclass)
        } catch (e: NoSuchMethodException) {
            throw RuntimeException("Unable to find binding constructor for $className", e)
        }
        BINDINGS!![`class`] = checkNotNull(binding) {
            "Unable to find preferences binding, is `prefs-compiler` correctly installed?"
        }
        return binding
    }
}
