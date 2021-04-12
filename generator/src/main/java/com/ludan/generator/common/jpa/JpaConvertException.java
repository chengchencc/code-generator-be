package com.ludan.generator.common.jpa;

import com.ludan.generator.common.exception.GeneratorException;

/**
 * @author: chengchen
 * @create: 2021-01-19 09:48
 **/
public class JpaConvertException extends GeneratorException {

    public JpaConvertException(Throwable cause) {
        super(cause);
    }

    public JpaConvertException(String message, Throwable cause) {
        super(message, cause);
    }
}
