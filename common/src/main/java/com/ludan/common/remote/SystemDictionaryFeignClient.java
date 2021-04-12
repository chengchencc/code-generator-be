package com.ludan.common.remote;

import com.central.common.model.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

/**
 * @author: chengchen
 * @create: 2021-03-29 18:54
 **/
@FeignClient(value = "app-creditmid-service-chengch",fallbackFactory = SystemDictionaryFallbackFactory.class)
public interface SystemDictionaryFeignClient {
    /**
     * 查询
     */
    @ApiOperation(value = "字典编号批量查询")
    @PostMapping("/sysdatadictvalue/findByDictCodes")
    public Result<List<Map<String,Object>>> findByDictCodes(@RequestBody List<String> dto);


}
