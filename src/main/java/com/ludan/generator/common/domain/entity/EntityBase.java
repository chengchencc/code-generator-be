package com.ludan.generator.common.domain.entity;

import com.ludan.generator.common.domain.Entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Description epp.cloud.widget.core.entities.common
 * Created by chengch
 * date on 2019/8/26
 * 实体类基类，包含了实体ID、审计、租户 等公共信息
 */

@MappedSuperclass
public class EntityBase<T extends Serializable> implements Serializable, Entity<T> {
    protected static final int Default_Max_VChar_Length = 4000;
    protected static final int Default_Name_Length = 255;
    protected static final int Default_Description_Length = 255;
    protected static final int Default_Id_Length = 36;
    protected static final int Default_Middle_Length = 500;

    @Id
//    @GenericGenerator(name = "idGenerator", strategy = "com.inspur.edp.epp.infrastructure.domain.entities.EppUUIDGeneratorIfNullThenGenerator")
//    //这个是hibernate的注解/生成32位UUID
//    @GeneratedValue(generator = "idGenerator")
    @GeneratedValue(strategy= GenerationType.IDENTITY) // MYSQL时可以这样使用自增
    protected T id;

    @Column()
    protected String tenantId;

    @Override
    public T getId() {
        return id;
    }

    @Override
    public void setId(T id) {
        this.id = id;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
}
