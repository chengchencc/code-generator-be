package com.ludan.generator;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ludan.generator.entity.UITemplate;
import com.ludan.generator.entity.validation.AbstractValidation;
import com.ludan.generator.entity.validation.NumberValidation;
import org.junit.Test;
import org.springframework.expression.spel.standard.SpelExpression;

import java.util.Date;

/**
 * @author: chengchen
 * @create: 2021-01-21 16:09
 **/
public class JsonTest {
    @Test
    public void testJsonConvert() throws JsonProcessingException {

        UITemplate uiTemplate = UITemplate.Default;
        Date date = DateUtil.beginOfDay(new Date()).toJdkDate();

        AbstractValidation numberValidation = getNumberValidation();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        String s = objectMapper.writeValueAsString(numberValidation);

        AbstractValidation abstractValidation = objectMapper.readValue(s, AbstractValidation.class);

        Object o = objectMapper.readValue(s, abstractValidation.getClassType());


    }

    private AbstractValidation getNumberValidation(){
        NumberValidation numberValidation = new NumberValidation(true,0,100);
        return numberValidation;
    }

    @Test
    public void testSplit(){

//        System.out.println(String.format("本业务已经提报了申保期限%s的业务办理，业务编号为%s，不能重复办理","2021","aaa"));

//        Date now = new Date(2021,2,28);//DateUtil.offsetDay();
//        System.out.println(now);
//        System.out.println(DateUtil.offsetMonth(now,1).toString());

//        String[] split = "aaa".split(",");
//        System.out.println(split);
    }
}
