package com.ludan.generator.common.controller;

import com.ludan.generator.common.domain.dto.PagedRequestDto;
import com.ludan.generator.common.domain.dto.PagedResultDto;
import com.ludan.generator.common.domain.service.ICrudAppService;
import com.ludan.generator.common.domain.dto.EntityDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Description com.inspur.edp.epp.framework.web.controllers
 * Created by chengch
 * date on 2019/11/14
 */
public class CrudControllerBase<TService extends ICrudAppService<TDto,TId>,TDto extends EntityDto<TId>,TId>
        extends ControllerBase
        implements CrudController<TDto,TId> {

    protected TService service;

    public CrudControllerBase(TService service) {
        this.service = service;
    }

    /**
     * 查找所有
     *
     * @return
     */
    @Override
    @GetMapping("/details")
    @ApiOperation("获取全部")
    public List<TDto> findAll() {
        return service.findAll();
    }

    @Override
    @GetMapping("/page")
    @ApiOperation("获取分页数据")
    public PagedResultDto<TDto> findAll(PagedRequestDto dto) {
        return service.findAll(PageRequest.of(dto.getPageNo()-1,dto.getPageSize()));
    }


    /**
     * 通过Id进行查询
     *
     * @param tId
     * @return
     */
    @Override
    @GetMapping("/detail/{id}")
    @ApiOperation("根据ID查询")
    public TDto findById(@PathVariable("id") TId tId) {
        return service.findById(tId);
    }

    /**
     * 创建
     *
     * @param dto
     * @return
     */
    @Override
    @PostMapping
    @ApiOperation("新建")
    public TDto create(@RequestBody TDto dto) {
        return service.create(dto);
    }

    /**
     * 更新
     *
     * @param dto
     * @return
     */
    @Override
    @PutMapping
    @ApiOperation("更新")
    public TDto update(@RequestBody TDto dto) {
        return service.update(dto);
    }

    /**
     * 删除，dto中只需要带着id即可
     *
     * @param dto
     */
    @Override
    @DeleteMapping
    @ApiOperation("删除")
    public void delete(TDto dto) {
        service.delete(dto);
    }

    @Override
    @DeleteMapping("/{id}")
    @ApiOperation("删除")
    public void deleteById(@PathVariable("id") TId id){
        service.deleteById(id);
    }

    /**
     * 删除，dto中只需要带着id即可
     *
     * @param id
     */
//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable("id") TId id) throws IllegalAccessException, InstantiationException {
//
//        ParameterizedType ptype = (ParameterizedType) this.getClass().getGenericSuperclass();
//        Class clazz = (Class<TDto>) ptype.getActualTypeArguments()[0];
//        TDto dto = (TDto) clazz.newInstance();
//
//        service.delete(dto);
//    }
}