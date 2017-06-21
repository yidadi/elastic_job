package com.even.job.annation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Retention(RUNTIME)
@Target(FIELD)
/**
 *  自定义的log注解，通过注解的方式动态的注入到spring容器中  避免每次手动的书写
 *
 *  @Log
 *  private Logger log;
 */
public @interface Log {

}
