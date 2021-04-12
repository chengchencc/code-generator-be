package com.ludan.generator.generate;

import java.util.Map;

/**
 * @author: chengchen
 * @create: 2021-03-01 15:34
 **/
@FunctionalInterface
public interface Renderer {
    public void execute(String code,String outputFileRelativePath);
}
