package com.ludan.common.annotation;

import java.lang.annotation.*;

/**
 * @author: chengchen
 * @create: 2021-02-27 22:49
 **/

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelDefine{
    /**
     * 业务表的别名
     */
    public String name() default "";


}
