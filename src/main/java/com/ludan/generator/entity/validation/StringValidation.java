package com.ludan.generator.entity.validation;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @author: chengchen
 * @create: 2021-01-19 09:41
 **/
@Data
public class StringValidation extends AbstractValidation {
    private Integer minLength;
    private Integer maxLength;
    private String pattern;
}
