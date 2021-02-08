package com.ludan.generator.entity.validation;

/**
 * @author: chengchen
 * @create: 2021-02-04 15:53
 **/
public class SimpleValidation extends AbstractValidation<SimpleValidation> {
    public SimpleValidation() {
    }

    public SimpleValidation(boolean required) {
        super(SimpleValidation.class, required);
    }
}
