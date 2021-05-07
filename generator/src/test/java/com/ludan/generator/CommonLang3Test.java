package com.ludan.generator;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.text.StrFormatter;
import lombok.Data;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.builder.Diff;
import org.apache.commons.lang3.builder.DiffBuilder;
import org.apache.commons.lang3.builder.DiffResult;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author: chengchen
 * @create: 2021-02-19 13:59
 **/
public class CommonLang3Test {

    @Test
    public void beanUtilsTest() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Map<String, String> describe = BeanUtils.describe(DataEntityUtil.getSingleEntity());
        Assert.assertEquals(describe.get("tableName"),"t_test");
    }

    @Test
    public void DiffTest() {
        ClassA classA = new ClassA();
        classA.setName("A");
        classA.setBoolField(false);
        classA.setCode(1);
        classA.setTime(new Date());

        ClassA classB = new ClassA();
        classB.setName("B");
        classB.setBoolField(false);
        classB.setCode(0);
        classB.setTime(DateUtil.offsetMonth(new Date(),1));

        DiffBuilder<ClassA> classADiffBuilder = new DiffBuilder<>(classA, classB, ToStringStyle.DEFAULT_STYLE);
        classADiffBuilder.append("name",classA.name,classB.name);
        classADiffBuilder.append("time",classA.time,classB.time);
        classADiffBuilder.append("bool",classA.boolField,classB.boolField);
        classADiffBuilder.append("code",classA.code,classB.code);
        DiffResult<ClassA> build = classADiffBuilder.build();
        List<Diff<?>> diffs = build.getDiffs();
//        StrFormatter.format("")
        for (Diff<?> diff : diffs) {
            String fieldName = diff.getFieldName();
            Object value = diff.getValue();
            String s = diff.toString();
            Object key = diff.getKey();
        }
        Assert.assertEquals(3,diffs.size());
    }

    @Data
    public class ClassA{
        private String name;
        private Boolean boolField;
        private Integer code;
        private Date time;
    }
}
