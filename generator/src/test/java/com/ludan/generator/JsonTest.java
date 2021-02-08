package com.ludan.generator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ludan.generator.entity.UITemplate;
import com.ludan.generator.entity.validation.AbstractValidation;
import com.ludan.generator.entity.validation.NumberValidation;
import org.junit.Test;

/**
 * @author: chengchen
 * @create: 2021-01-21 16:09
 **/
public class JsonTest {
    @Test
    public void testJsonConvert() throws JsonProcessingException {

        UITemplate uiTemplate = UITemplate.Default;



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
}
