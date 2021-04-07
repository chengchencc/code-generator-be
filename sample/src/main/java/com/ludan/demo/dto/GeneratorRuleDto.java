package com.ludan.demo.dto;

import com.central.common.model.SuperEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ludan.common.dto.EntityDto;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.math.BigDecimal;
import java.util.Date;


/**
* @Description: 生成规则
* @Author: chengch
* @Date:   2021-04-06
* @Version: V1.0
*/
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class GeneratorRuleDto extends EntityDto<Integer> {
    private static final long serialVersionUID=1L;

    /**
    * 主键
    */
    @ApiModelProperty(value = "主键")
    private Integer id;
    /**
    * 租户Id
    */
    @ApiModelProperty(value = "租户Id")
    private String tenantId;
    /**
    * 创建时间
    */
    @ApiModelProperty(value = "创建时间")
    private Date creationTime;
    /**
    * 创建者
    */
    @ApiModelProperty(value = "创建者")
    private String creationUserId;
    /**
    * 删除时间
    */
    @ApiModelProperty(value = "删除时间")
    private Date deletionTime;
    /**
    * 删除人
    */
    @ApiModelProperty(value = "删除人")
    private String deletionUserId;
    /**
    * 最后修改时间
    */
    @ApiModelProperty(value = "最后修改时间")
    private Date lastModifyTime;
    /**
    * 最后修改人
    */
    @ApiModelProperty(value = "最后修改人")
    private String lastModifyUserId;
    /**
    * 作者
    */
    @ApiModelProperty(value = "作者")
    private String authorName;
    /**
    * 作者邮箱
    */
    @ApiModelProperty(value = "作者邮箱")
    private String email;
    /**
    * 模块名
    */
    @ApiModelProperty(value = "模块名")
    private String moduleName;
    /**
    * 包名
    */
    @ApiModelProperty(value = "包名")
    private String packageName;
    /**
    * 模板
    */
    @ApiModelProperty(value = "模板")
    private Integer uiTemplate;

}
