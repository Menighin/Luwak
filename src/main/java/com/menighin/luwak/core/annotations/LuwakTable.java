package com.menighin.luwak.core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface LuwakTable {
	String title() default "";
	boolean canCreate() default false;
	boolean canDelete() default false;
	boolean canEdit()   default false;
}


