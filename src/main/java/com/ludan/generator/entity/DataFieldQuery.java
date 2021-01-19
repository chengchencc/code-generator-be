package com.ludan.generator.entity;

import com.ludan.generator.common.entity.EntityBase;
import com.ludan.generator.common.jpa.JpaJsonConverter;
import com.ludan.generator.entity.validation.AbstractValidation;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @author: chengchen
 * @create: 2021-01-18 19:49
 **/
@Table
@Entity
@Data
public class DataFieldQuery extends EntityBase<Integer> {

    private int dataFieldId;

    @OneToOne
    @JoinColumn(name = "dataFieldId",referencedColumnName = "id",nullable = false,insertable = false,updatable = false)
    private DataField dataField;

    private boolean isEnable;

    private List<String> testList;

    private List<String> jsonTest;

    @Column(columnDefinition = "json")
    @Convert(converter = JpaJsonConverter.class)
    private AbstractValidation validation;

}
