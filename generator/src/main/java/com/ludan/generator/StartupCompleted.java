package com.ludan.generator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author: chengchen
 * @create: 2021-04-13 10:19
 **/
@Component
@Slf4j
public class StartupCompleted implements ApplicationRunner {

    @Value("${code-generator.outputPath}")
    private String outputPath;
    @Value("${code-generator.templatePath}")
    private String templatePath;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("启动成功");
        log.info("代码输出路径::{}", outputPath);
        log.info("代码模板路径::{}", templatePath);
    }
}

