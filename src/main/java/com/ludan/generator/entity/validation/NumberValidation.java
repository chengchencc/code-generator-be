package com.ludan.generator.entity.validation;

import lombok.Data;

/**
 * @author: chengchen
 * @create: 2021-01-19 09:43
 **/
@Data
public class NumberValidation extends AbstractValidation {
    private Integer min;
    private Integer max;

}
