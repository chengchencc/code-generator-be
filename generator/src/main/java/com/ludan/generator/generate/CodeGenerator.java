package com.ludan.generator.generate;

import com.ludan.generator.entity.DataEntity;
import com.ludan.generator.entity.GeneratorRule;

import java.io.OutputStream;

/**
 * @author: chengchen
 * @create: 2021-02-04 17:11
 **/
public interface CodeGenerator {
    /**
     * 生成代码
     * @param entityId
     * @return
     */
    void generate(Integer entityId, OutputStream outputStream);

    void generate(DataEntity entity, GeneratorRule generatorRule);

    String render();

}
