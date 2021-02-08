package com.ludan.generator.common.controller;

import com.ludan.generator.common.domain.service.ITreeAppService;
import com.ludan.generator.common.domain.dto.TreeEntityDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class TreeAppCrudControllerBase<Tservice extends ITreeAppService<TEntityDto,Tkey>,TEntityDto extends TreeEntityDto<TEntityDto,Tkey>,Tkey>
        extends CrudControllerBase<Tservice,TEntityDto,Tkey> {

    public TreeAppCrudControllerBase(Tservice service) {
        super(service);
    }

    /**
     * 新增子级
     * @param dto
     * @param id
     * @return
     */
    @PostMapping("/child/{id}")
    public TEntityDto  addChild(@RequestBody TEntityDto dto, @PathVariable(name = "id")Tkey id){
        return service.addChild(dto,id);
    }


    /**
     * 更新
     * @param dto
     * @return
     */
    @PutMapping
    public TEntityDto update(@RequestBody TEntityDto dto) {
        return service.update(dto);
    }
}
