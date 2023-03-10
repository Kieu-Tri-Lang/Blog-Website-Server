package com.kieutrilang.blogwebsite.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.PARAMETER })
public @interface PagingParam {

    int page() default 1;

    int limitedSize() default 12;

    String sortTar() default "id";

    String sortDir() default "asc";
}