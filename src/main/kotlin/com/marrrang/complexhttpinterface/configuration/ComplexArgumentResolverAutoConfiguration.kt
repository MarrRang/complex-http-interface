package com.marrrang.complexhttpinterface.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ComplexArgumentResolverAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    fun complexArgumentResolver(@Value("\${http-interface.complex.packageName}") packageName: String): ComplexArgumentResolver {
        return ComplexArgumentResolver(packageName)
    }

}