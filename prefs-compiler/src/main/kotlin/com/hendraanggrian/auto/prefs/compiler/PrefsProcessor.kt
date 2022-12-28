package com.hendraanggrian.auto.prefs.compiler

import com.google.auto.common.MoreElements
import com.google.auto.common.MoreTypes
import com.google.common.collect.LinkedHashMultimap
import com.hendraanggrian.auto.prefs.BindPreference
import com.hendraanggrian.javapoet.FINAL
import com.hendraanggrian.javapoet.PRIVATE
import com.hendraanggrian.javapoet.PUBLIC
import com.hendraanggrian.javapoet.asClassName
import com.hendraanggrian.javapoet.buildJavaFile
import com.hendraanggrian.javapoet.classNameOf
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.Filer
import javax.annotation.processing.ProcessingEnvironment
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element
import javax.lang.model.element.TypeElement
import javax.lang.model.type.TypeKind

/**
 * Annotation processor responsible for generating sources necessary for binding preferences. The
 * binding API is available at core repository.
 */
class PrefsProcessor : AbstractProcessor() {
    private lateinit var filer: Filer

    override fun getSupportedSourceVersion(): SourceVersion = SourceVersion.latestSupported()

    override fun getSupportedAnnotationTypes(): Set<String> =
        setOf(BindPreference::class.java.canonicalName)

    @Synchronized
    override fun init(processingEnv: ProcessingEnvironment) {
        super.init(processingEnv)
        filer = processingEnv.filer
    }

    @Suppress("UnstableApiUsage")
    override fun process(annotations: Set<TypeElement>, roundEnv: RoundEnvironment): Boolean {
        // preparing elements
        val multimap = LinkedHashMultimap.create<TypeElement, Element>()
        val measuredClassNames = mutableSetOf<String>()
        roundEnv.getElementsAnnotatedWith(BindPreference::class.java).forEach { element ->
            val typeElement = MoreElements.asType(element.enclosingElement)
            multimap.put(typeElement, element)
            measuredClassNames += typeElement.measuredName
        }

        // generate classes
        multimap.keySet().map { it to multimap[it] }.forEach { (typeElement, elements) ->
            val className = typeElement.asClassName()
            val packageName = typeElement.measuredPackageName
            buildJavaFile(packageName) {
                comment = FILE_COMMENT
                addClass(typeElement.measuredName) {
                    var hasSupercls = false
                    val supercls = typeElement.superclass
                    if (supercls.kind != TypeKind.NONE && supercls.kind != TypeKind.VOID) {
                        val measuredClassName = MoreTypes.asTypeElement(supercls).measuredName
                        if (measuredClassName in measuredClassNames) {
                            superclass = classNameOf(packageName, measuredClassName)
                            hasSupercls = true
                        }
                    }
                    if (!hasSupercls) {
                        superclass = CLS_PREFERENCES_BINDING
                    }
                    addModifiers(PUBLIC)
                    fields.add(className, REF_TARGET, PRIVATE, FINAL)
                    methods {
                        addConstructor {
                            addModifiers(PUBLIC)
                            parameters {
                                add(CLS_READABLE_PREFERENCES, REF_SOURCE, FINAL)
                                add(className, REF_TARGET, FINAL)
                            }
                            when {
                                !hasSupercls -> appendLine("super(%L)", REF_SOURCE)
                                else -> appendLine("super(%L, %L)", REF_SOURCE, REF_TARGET)
                            }
                            appendLine("this.%L = %L", REF_TARGET, REF_TARGET)
                            elements.forEachValue { field, key ->
                                appendLine(
                                    "this.$REF_TARGET.%L = get(%L, $REF_TARGET.%L)",
                                    field,
                                    key,
                                    field
                                )
                            }
                        }
                        "save" {
                            addModifiers(PUBLIC)
                            this.annotations.add<Override>()
                            if (hasSupercls) {
                                appendLine("super.save()")
                            }
                            appendLine("final %T $REF_EDITOR = getEditor()", CLS_PREFERENCES_EDITOR)
                            elements.forEachValue { field, key ->
                                appendLine("$REF_EDITOR.set(%L, $REF_TARGET.%L)", key, field)
                            }
                            appendLine("$REF_EDITOR.save()")
                        }
                    }
                }
            }.writeTo(filer)
        }
        return false
    }

    private inline fun Iterable<Element>.forEachValue(
        action: (field: String, key: String) -> Unit
    ) = forEach { element ->
        val field = element.simpleName.toString()
        val preference = element.getAnnotation(BindPreference::class.java)
        val key = "\"${if (preference!!.value.isNotEmpty()) preference.value else field}\""
        action(field, key)
    }
}
