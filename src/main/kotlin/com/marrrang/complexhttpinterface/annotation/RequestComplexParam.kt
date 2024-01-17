package com.marrrang.complexhttpinterface.annotation

import org.springframework.web.bind.annotation.ValueConstants


annotation class RequestComplexParam(
    val value: String = "",
    val name: String = "",
    val required: Boolean = true,
    val defaultValue: String = ValueConstants.DEFAULT_NONE
)