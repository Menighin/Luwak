package com.menighin.luwak.core.annotations

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class MapModel(val value: String = "")