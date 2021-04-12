package com.ludan.generator.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author: chengchen
 * @create: 2021-02-03 17:07
 **/
@Data
@Component
@ConfigurationProperties(prefix = "code-generator")
public class CodeGeneratorProperties {
    private Map<String,String> dataTypeMap;
    private String outputPath;
}
