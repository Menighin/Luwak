package com.menighin.luwak.core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
public @interface Label {

    String value() default "";

}
