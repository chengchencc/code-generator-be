package com.ludan.generator.common.jpa;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;

/**
 * @author: chengchen
 * @create: 2021-01-19 09:46
 **/
@Converter(autoApply = true)
public class JpaJsonConverter implements AttributeConverter<Object, String> {
    private final static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Object meta) {
        try {
            return objectMapper.writeValueAsString(meta);
        } catch (JsonProcessingException ex) {
            throw new JpaConvertException(ex);
        }
    }

    @Override
    public Object convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, Object.class);
        } catch (IOException ex) {
             throw new JpaConvertException("Unexpected IOEx decoding json from database: " + dbData,ex);
        }
    }
}
