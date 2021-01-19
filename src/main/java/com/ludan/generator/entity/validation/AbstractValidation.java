package com.ludan.generator.entity.validation;

import lombok.Data;

/**
 * @author: chengchen
 * @create: 2021-01-19 09:42
 **/
@Data
public class AbstractValidation<T extends AbstractValidation> {
    protected Class<T> classType;
    private boolean required;
}
