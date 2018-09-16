package com.menighin.luwak.core.annotations

import com.menighin.luwak.core.enums.ColumnTypeEnum

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class ColumnType(val value: ColumnTypeEnum = ColumnTypeEnum.Text)