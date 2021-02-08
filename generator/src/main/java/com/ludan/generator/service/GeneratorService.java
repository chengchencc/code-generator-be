package com.ludan.generator.service;

import java.io.OutputStream;
import java.util.stream.Stream;
import java.util.zip.ZipOutputStream;

/**
 * @author: chengchen
 * @create: 2021-02-04 17:11
 **/
public interface GeneratorService {
    /**
     * 生成代码
     * @param entityId
     * @return
     */
    OutputStream GenerateCode(Integer entityId);

    String render();

}
