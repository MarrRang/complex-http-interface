package com.marrrang.complexhttpinterface.annotation

import org.springframework.context.annotation.ComponentScan


@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@ComponentScan(basePackages = ["com.marrrang.complexhttpinterface.**"])
annotation class EnableComplexResolver()
