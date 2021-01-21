package com.ludan.generator;

import com.baomidou.mybatisplus.annotation.IdType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ludan.generator.entity.*;
import com.ludan.generator.entity.validation.AbstractValidation;
import com.ludan.generator.entity.validation.NumberValidation;
import com.ludan.generator.entity.validation.StringValidation;
import com.ludan.generator.repository.DataEntityRepository;
import com.ludan.generator.repository.DataFieldQueryReposiroty;
import com.ludan.generator.repository.DataFieldReposiroty;
import com.ludan.generator.repository.DataFieldUIReposiroty;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Selection;
import java.util.List;

/**
 * @author: chengchen
 * @create: 2021-01-21 09:12
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class JpaTest {

    @Autowired
    DataEntityRepository dataEntityRepository;

    @Autowired
    private DataFieldReposiroty dataFieldReposiroty;

    @Autowired
    private DataFieldQueryReposiroty dataFieldQueryReposiroty;

    @Autowired
    private DataFieldUIReposiroty dataFieldUIReposiroty;

    @PersistenceContext
    EntityManager entityManager;

    @Test
    public void testOneToManySave(){
        DataEntity dataEntity = new DataEntity();
        dataEntity.setName("test_table");
        dataEntity.setTableName("test_table");
        dataEntity.setCode("test_table");
        dataEntity.setDescription("测试表");
        dataEntity.setTableType(TableType.Nomal);
        dataEntity.setUiTemplate(UITemplate.Default);
        dataEntity.setTableIdType(IdType.AUTO);
        dataEntityRepository.save(dataEntity);

        DataField idField = new DataField();
        idField.setCode("id");
        idField.setName("id");
        idField.setDescription("id");
        idField.setDataFieldType(DataFieldType.INTETER);
        idField.setIsRequired(false);
        idField.setDefaultValue("1");
        idField.setForeignKey(false);
        idField.setLength(12);
        idField.setSortNo(0);

        idField.setEntityId(dataEntity.getId());


        DataField codeField = getDataField("code", 1);
        codeField.setEntityId(dataEntity.getId());
        DataField nameField = getDataField("name",2);
        nameField.setEntityId(dataEntity.getId());

        dataFieldReposiroty.save(idField);
        dataFieldReposiroty.save(codeField);
        dataFieldReposiroty.save(nameField);

        DataFieldUI idFieldUI = getDataFieldUI(idField.getId(),ControlType.InputText,getNumberValidation());
        DataFieldUI codeFieldUI = getDataFieldUI(codeField.getId(),ControlType.InputText,getStringValidation());
        DataFieldUI nameFieldUI = getDataFieldUI(nameField.getId(),ControlType.InputText,getStringValidation());

        dataFieldUIReposiroty.save(idFieldUI);
        dataFieldUIReposiroty.save(codeFieldUI);
        dataFieldUIReposiroty.save(nameFieldUI);

        System.out.println("*****************************************");

        List<DataEntity> all = dataEntityRepository.findAll();
        List<DataField> all1 = dataFieldReposiroty.findAll();
        System.out.println(all);
    }
    @Test
    public void testJsonConvert() throws JsonProcessingException {
        AbstractValidation numberValidation = getNumberValidation();
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(numberValidation);

        AbstractValidation abstractValidation = objectMapper.readValue(s, AbstractValidation.class);
    }


    @Test
    public void testJpaSpecificationExecutor(){
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);

        // 报错，待解决！！！
        List<DataEntity> fetch1 = jpaQueryFactory.selectFrom(QDataEntity.dataEntity).fetch();

        List<Tuple> fetch = jpaQueryFactory.select(
                QDataEntity.dataEntity.code,
                QDataEntity.dataEntity.fields)
                .from(QDataEntity.dataEntity)
                .fetch();
        System.out.println(fetch);
    }

    private DataField getDataField(String name,Integer sortNo){
        DataField dataField = new DataField(name, DataFieldType.STRING, 255, sortNo);
        return dataField;
    }

    private DataFieldUI getDataFieldUI(int fieldId,ControlType controlType,AbstractValidation validation){
        DataFieldUI dataFieldUI = new DataFieldUI(fieldId, controlType, true, true, true, true, 200, validation);
        return dataFieldUI;
    }


    private AbstractValidation getNumberValidation(){
        NumberValidation numberValidation = new NumberValidation(true,0,100);
        return numberValidation;
    }

    private AbstractValidation getStringValidation(){
       return new StringValidation(false,0,10,null);
    }

}
