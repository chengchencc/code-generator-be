package com.ludan.generator.entity.validation;

import lombok.*;
import org.hibernate.validator.constraints.Length;

/**
 * @author: chengchen
 * @create: 2021-01-19 09:41
 **/
@Getter
@Setter
@ToString(callSuper = true)
public class StringValidation extends AbstractValidation {
    private Integer minLength;
    private Integer maxLength;
    private String pattern;

    public StringValidation() {
    }

    public StringValidation(boolean required, Integer minLength, Integer maxLength, String pattern) {
        super(StringValidation.class, required);
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.pattern = pattern;
    }
}
