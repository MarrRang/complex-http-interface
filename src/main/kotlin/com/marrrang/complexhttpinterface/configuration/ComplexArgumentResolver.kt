package com.marrrang.complexhttpinterface.configuration

import com.marrrang.complexhttpinterface.annotation.RequestComplexParam
import org.springframework.core.MethodParameter
import org.springframework.web.service.invoker.AbstractNamedValueArgumentResolver
import org.springframework.web.service.invoker.HttpRequestValues
import kotlin.reflect.KClass
import kotlin.reflect.KProperty1
import kotlin.reflect.full.memberProperties

class ComplexArgumentResolver(private val packageName: String) : AbstractNamedValueArgumentResolver() {

    override fun createNamedValueInfo(parameter: MethodParameter): NamedValueInfo? {
        val annot = parameter.getParameterAnnotation(RequestComplexParam::class.java)
        return annot?.let {
            NamedValueInfo(
                annot.name,
                annot.required,
                annot.defaultValue,
                "request complex parameter",
                true
            )
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun addRequestValue(
        name: String,
        value: Any,
        parameter: MethodParameter,
        requestValues: HttpRequestValues.Builder
    ) {
        val properties = value::class.memberProperties

        for (property in properties) {
            val eachProperty = property as KProperty1<Any, *>
            val propertyClassifier = eachProperty.returnType.classifier

            val propertyValue = if (propertyClassifier is KClass<*> && propertyClassifier.java.isEnum) {
                (eachProperty.get(value) as Enum<*>).name
            } else {
                if (eachProperty.get(value)?.javaClass?.`package`?.name?.contains(packageName) == true) {
                    addRequestValue(name, eachProperty.get(value)!!, parameter, requestValues)
                    continue
                } else {
                    convertToString(eachProperty.get(value))
                }
            }

            requestValues.addRequestParameter(eachProperty.name, propertyValue)
        }
    }

    private fun convertToString(value: Any?): String {
        return if (value is String) value else value.toString()
    }

}