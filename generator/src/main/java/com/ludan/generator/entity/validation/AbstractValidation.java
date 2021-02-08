package com.ludan.generator.entity.validation;

import com.ludan.generator.common.jpa.TypedObject;
import lombok.Data;

/**
 * @author: chengchen
 * @create: 2021-01-19 09:42
 **/
@Data
public class AbstractValidation<T extends AbstractValidation> implements TypedObject {
    protected Class<T> classType;
    protected boolean required;

    public AbstractValidation() {
    }

    public AbstractValidation(Class<T> classType, boolean required) {
        this.classType = classType;
        this.required = required;
    }
}
