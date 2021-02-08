package com.ludan.generator.entity.validation;

import lombok.*;

/**
 * @author: chengchen
 * @create: 2021-01-19 09:41
 **/
@Getter
@Setter
@ToString(callSuper = true)
public class StringValidation extends AbstractValidation {
    private Long minLength;
    private Long maxLength;
    private String pattern;

    public StringValidation() {
    }

    public StringValidation(boolean required, Long minLength, Long maxLength, String pattern) {
        super(StringValidation.class, required);
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.pattern = pattern;
    }
}
