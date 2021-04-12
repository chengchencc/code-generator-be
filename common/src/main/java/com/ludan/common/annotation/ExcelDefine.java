package com.ludan.common.annotation;

import javax.validation.constraints.Null;
import java.lang.annotation.*;

/**
 * @author: chengchen
 * @create: 2021-02-27 22:49
 **/

@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelDefine{
    /**
     * 列名
     */
    String columnName() default "";
    /**
     * 列宽（单位为一个字符的宽度，例如传入width为10，表示10个字符的宽度）
     * @return
     */
    int columnWidth() default 20;
//    /**
//     * 是否字典转换
//     * @return
//     */
//    boolean isDictMapping() default false;
    /**
     * 字典编码
     * @return
     */
    String dictCode() default "";


}
