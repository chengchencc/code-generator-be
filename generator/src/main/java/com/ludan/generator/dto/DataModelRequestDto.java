package com.ludan.generator.dto;

import lombok.Data;

import java.util.List;

/**
 * @author: chengchen
 * @create: 2021-02-04 12:24
 **/
@Data
public class DataModelRequestDto {
    private DataEntityDto dataEntity;
    private List<DataFieldDto> dataFields;
    private List<DataFieldUIDto> dataFieldUI;
}
