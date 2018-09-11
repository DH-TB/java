package com.cultivation.javaBasicExtended.myIoC;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// TODO: Please add additional annotation so that it can be found at runtime. And make it field only.
// <-Start
// --end->
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyIoCInjection {
}
