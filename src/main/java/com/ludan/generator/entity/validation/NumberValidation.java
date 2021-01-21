package com.ludan.generator.entity.validation;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @author: chengchen
 * @create: 2021-01-19 09:43
 **/
@Getter
@Setter
@ToString(callSuper = true)
public class NumberValidation extends AbstractValidation<NumberValidation> {
    private Integer min;
    private Integer max;

    public NumberValidation() {
    }

    public NumberValidation(boolean required, Integer min, Integer max) {
        super(NumberValidation.class, required);
        this.min = min;
        this.max = max;
    }

}
