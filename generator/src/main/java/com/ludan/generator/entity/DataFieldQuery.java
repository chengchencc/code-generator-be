package com.ludan.generator.entity;

import com.ludan.generator.common.domain.entity.EntityBase;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author: chengchen
 * @create: 2021-01-18 19:49
 **/
@Getter
@Setter
@Table
@Entity
public class DataFieldQuery extends EntityBase<Integer> {

    private int dataFieldId;

//    @OneToOne
//    @JoinColumn(name = "dataFieldId",referencedColumnName = "id",nullable = false,insertable = false,updatable = false)
//    private DataField dataField;

    private boolean isEnable;

//    private List<String> testList;
//
//    private List<String> jsonTest;

//    @Column(columnDefinition = "json")
//    @Convert(converter = JpaJsonConverter.class)
//    private AbstractValidation validation;

}
