package com.menighin.luwak.core.annotations

import com.menighin.luwak.core.enums.FieldTypeEnum

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class FieldType(val value: FieldTypeEnum = FieldTypeEnum.TEXT)