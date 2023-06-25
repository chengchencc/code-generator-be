package com.ludan.viz.entity;

import com.central.common.model.SuperEntity;
import com.central.common.model.BaseEntityFill;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.central.common.annotation.ExcelDefine;
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
@EqualsAndHashCode(callSuper = false)
@TableName("ads_show_guar_compt_info")
public class AdsShowGuarComptInfo extends BaseEntityFill {
    private static final long serialVersionUID=1L;
    /**
    * 
    */
    @ApiModelProperty(value = "")
    @TableId(type = IdType.AUTO)
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
