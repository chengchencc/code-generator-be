package com.ludan.generator;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
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

}
