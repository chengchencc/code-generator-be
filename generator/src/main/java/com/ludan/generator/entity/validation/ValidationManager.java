package com.ludan.generator.entity.validation;

import com.ludan.generator.entity.DataFieldType;

/**
 * @author: chengchen
 * @create: 2021-02-04 15:57
 **/
public class ValidationManager {
    public static AbstractValidation getDefaultValidation(DataFieldType fieldType){
        switch (fieldType){
            case FLOAT:
            case LONG:
            case INTETER:
            case DOUBLE:
            case DECIMAL:
                return new NumberValidation(false,0,null);
            case TEXT:
            case STRING:
                return new StringValidation(false,0L,255L,null);
            case BYTES:
                return new SimpleValidation(false);
            case DATETIME:
                return new SimpleValidation(false);
            case BOOLEAN:
                return new SimpleValidation(false);
            case UNKNOWN:
            default:
                return new SimpleValidation(false);
        }
    }
}
