package com.ludan.viz.dto;

import com.central.common.model.SuperEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.central.common.dto.EntityDto;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.math.BigDecimal;
import java.util.Date;


/**
* @Description: 大屏-业务代偿率信息
* @Author: Smark
* @Date:   2021-05-08
* @Version: V1.0
*/
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class AdsShowGuarComptInfoDto extends EntityDto<Long> {
    private static final long serialVersionUID=1L;

    /**
    * 
    */
    @ApiModelProperty(value = "")
    private Long id;
    /**
    * 数据日期
    */
    @ApiModelProperty(value = "数据日期")
    private String dayId;
    /**
    * 山东累保数
    */
    @ApiModelProperty(value = "山东累保数")
    private BigDecimal sdGuarQty;
    /**
    * 山东代偿率
    */
    @ApiModelProperty(value = "山东代偿率")
    private BigDecimal sdComptRate;
    /**
    * 山东在保数
    */
    @ApiModelProperty(value = "山东在保数")
    private BigDecimal sdOnguarQty;
    /**
    * 全国累保数
    */
    @ApiModelProperty(value = "全国累保数")
    private BigDecimal allGuarQty;
    /**
    * 全国代偿率
    */
    @ApiModelProperty(value = "全国代偿率")
    private BigDecimal allComptRate;
    /**
    * 全国在保数
    */
    @ApiModelProperty(value = "全国在保数")
    private BigDecimal allOnguarQty;

}
