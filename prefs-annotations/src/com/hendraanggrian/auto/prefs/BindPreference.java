package com.hendraanggrian.auto.prefs;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Bind a preference value to JVM field with this annotation.
 * Keep in mind that in order to use this feature, `prefs-compiler` must be imported as annotation processor.
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.FIELD)
public @interface BindPreference {

    /** Generated class name suffix which fields are annotated with this annotation. */
    String SUFFIX = "_PreferencesBinding";

    /**
     * Key of the preference which value will be injected into the annotated field.
     * @return preference key, or field name if left empty.
     */
    String value() default "";
}