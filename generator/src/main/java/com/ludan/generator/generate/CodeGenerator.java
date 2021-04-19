package com.ludan.generator.generate;

import com.ludan.generator.entity.DataEntity;
import com.ludan.generator.entity.GeneratorRule;

import javax.servlet.http.HttpServletResponse;
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
    void generateToResponse(Integer entityId, HttpServletResponse response);

    void generateToFile(DataEntity entity, GeneratorRule generatorRule);

    void generateToResponse(DataEntity entity, GeneratorRule generatorRule, HttpServletResponse response);

    void generateToResponse(Integer[] entityIds,HttpServletResponse response);

}
