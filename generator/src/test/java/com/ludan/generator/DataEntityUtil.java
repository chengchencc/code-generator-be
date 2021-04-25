package com.ludan.generator;

import com.google.common.collect.Lists;
import com.ludan.generator.entity.*;
import com.ludan.generator.entity.validation.*;

import java.util.Arrays;
import java.util.Date;

/**
 * @author: chengchen
 * @create: 2021-02-09 16:28
 **/
public class DataEntityUtil {
    public static DataEntity getSingleEntity(){
        return getDataEntity("t_test_Single","Single","Single信息表");
    }



    public static DataEntity getOneToManyEntity(){
        DataEntity mainEntity = getDataEntity("t_main", "Main", "Main信息表");
        DataEntity child1Entity = getDataEntity("t_childA", "ChildA", "ChildA信息表");
        child1Entity.setTableType(TableType.OneToOneChildTable);
        DataEntity child2Entity = getDataEntity("t_childB", "ChildB", "ChildB信息表");
        child2Entity.setTableType(TableType.OneToManyChildTable);
        mainEntity.setTableType(TableType.MainTable);

        mainEntity.setChildren(Arrays.asList(child1Entity,child2Entity));

        return mainEntity;
    }

    private static DataEntity getDataEntity(String tablename,String entityCode,String entityName) {
        DataEntity dataEntity = new DataEntity();
        dataEntity.setTableName(tablename);
        dataEntity.setCode(entityCode);
        dataEntity.setName(entityCode);
        dataEntity.setDescription("开发测试");
        dataEntity.setTableSchema(TableSchema.Nomal);
        dataEntity.setParentId("");
        dataEntity.setTableType(TableType.SingleTable);
        dataEntity.setMainEntityId(0);
        dataEntity.setTableIdType(IdType.ASSIGN_ID);
        dataEntity.setUiTemplate(UITemplate.Default);
        dataEntity.setVersion(0);
        dataEntity.setPublished(false);
        dataEntity.setFields(Lists.newArrayList());
        dataEntity.setStorageType("");
        dataEntity.setCreationTime(new Date());
        dataEntity.setLastModifyTime(new Date());
        dataEntity.setCreationUserId("");
        dataEntity.setLastModifyUserId("");
        dataEntity.setDeletionTime(new Date());
        dataEntity.setDeletionUserId("");
        dataEntity.setId(1);
        dataEntity.setTenantId("10000");

        DataField fieldId = getDataField(1, "id", DataFieldType.INTETER, 0);
        fieldId.setDataFieldUI(getDataFieldUI(1, ControlType.Number, getNumberValidation()));
        fieldId.setIsPrimaryKey(true);

        DataField field1 = getDataField(1, "fieldInt", DataFieldType.INTETER, 0);
        field1.setDataFieldUI(getDataFieldUI(1, ControlType.Number, getNumberValidation()));
        DataField field2 = getDataField(2, "fieldString", DataFieldType.STRING, 0);
        field2.setDataFieldUI(getDataFieldUI(2, ControlType.InputText, getStringValidation()));
        DataField field3 = getDataField(3, "fieldBool", DataFieldType.BOOLEAN, 0);
        field3.setDataFieldUI(getDataFieldUI(3, ControlType.Checkbox, new SimpleValidation(false)));

        dataEntity.getFields().add(fieldId);
        dataEntity.getFields().add(field1);
        dataEntity.getFields().add(field2);
        dataEntity.getFields().add(field3);
        return dataEntity;
    }

    public static DataField getDataField(int fieldId,String name,DataFieldType fieldType , Integer sortNo){
        DataField dataField = new DataField(name, fieldType, 255L, sortNo);
        return dataField;
    }

    public static DataFieldUI getDataFieldUI(int fieldId, ControlType controlType, AbstractValidation validation){
        DataFieldUI dataFieldUI = new DataFieldUI(fieldId, controlType, true, true, true, true,true, 200, validation);
        return dataFieldUI;
    }


    public static AbstractValidation getNumberValidation(){
        NumberValidation numberValidation = new NumberValidation(true,0,100);
        return numberValidation;
    }

    public static AbstractValidation getStringValidation(){
        return new StringValidation(false,0L,10L,null);
    }

}
