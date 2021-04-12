package com.ludan.reguarantee.dto;

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
* @Description: 备案信息
* @Author: ludan
* @Date:   2021-04-09
* @Version: V1.0
*/
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class RegistrationDto extends EntityDto<String> {
    private static final long serialVersionUID=1L;

    /**
    * 雪花算法生成的递增主键
    */
    @ApiModelProperty(value = "雪花算法生成的递增主键")
    private String id;
    /**
    * 工作流nodeId
    */
    @ApiModelProperty(value = "工作流nodeId")
    private String wfNodeId;
    /**
    * 工作流实例id
    */
    @ApiModelProperty(value = "工作流实例id")
    private String wfInstanceId;
    /**
    * 项目状态
    */
    @ApiModelProperty(value = "项目状态")
    private String status;
    /**
    * 业务编号
    */
    @ApiModelProperty(value = "业务编号")
    private String code;
    /**
    * 客户名称
    */
    @ApiModelProperty(value = "客户名称")
    private String custName;
    /**
    * 客户性质 取字典值
    */
    @ApiModelProperty(value = "客户性质 取字典值")
    private String custNature;
    /**
    * 客户证件号类别 取字典值
    */
    @ApiModelProperty(value = "客户证件号类别 取字典值")
    private String custCertType;
    /**
    * 证件号码
    */
    @ApiModelProperty(value = "证件号码")
    private String custCertNo;
    /**
    * 联系电话
    */
    @ApiModelProperty(value = "联系电话")
    private String custLinkTel;
    /**
    * 客户邮编
    */
    @ApiModelProperty(value = "客户邮编")
    private String custPostCode;
    /**
    * 客户邮件
    */
    @ApiModelProperty(value = "客户邮件")
    private String custEmail;
    /**
    * 法人姓名
    */
    @ApiModelProperty(value = "法人姓名")
    private String legalName;
    /**
    * 法人联系方式
    */
    @ApiModelProperty(value = "法人联系方式")
    private String legalLinkTel;
    /**
    * 法人身份证号
    */
    @ApiModelProperty(value = "法人身份证号")
    private String legalCertNo;
    /**
    * 所属行业(国家)
    */
    @ApiModelProperty(value = "所属行业(国家)")
    private String industCategNation;
    /**
    * 所属行业(工业)
    */
    @ApiModelProperty(value = "所属行业(工业)")
    private String industCateg;
    /**
    * 从业人数(上一年末)
    */
    @ApiModelProperty(value = "从业人数(上一年末)")
    private Integer workNumLastYear;
    /**
    * 上一年末资产总额
    */
    @ApiModelProperty(value = "上一年末资产总额")
    private BigDecimal assetAmtLastYear;
    /**
    * 营业额
    */
    @ApiModelProperty(value = "营业额")
    private BigDecimal incomeAmt;
    /**
    * 纳税额
    */
    @ApiModelProperty(value = "纳税额")
    private BigDecimal taxAmt;
    /**
    * 企业划型，大中小微等
    */
    @ApiModelProperty(value = "企业划型，大中小微等")
    private String compType;
    /**
    * 政策扶持领域类别(服务对象类型)
    */
    @ApiModelProperty(value = "政策扶持领域类别(服务对象类型)")
    private String policySupportFieldType;
    /**
    * 存续状态
    */
    @ApiModelProperty(value = "存续状态")
    private String standingStatus;
    /**
    * 登记地址
    */
    @ApiModelProperty(value = "登记地址")
    private String regAddr;
    /**
    * 省
    */
    @ApiModelProperty(value = "省")
    private String provinceName;
    /**
    * 市
    */
    @ApiModelProperty(value = "市")
    private String cityName;
    /**
    * 县/区
    */
    @ApiModelProperty(value = "县/区")
    private String countyName;
    /**
    * 上报批次
    */
    @ApiModelProperty(value = "上报批次")
    private String reportBatch;
    /**
    * 债务人名称
    */
    @ApiModelProperty(value = "债务人名称")
    private String debtorName;
    /**
    * 业务品种 取字典
    */
    @ApiModelProperty(value = "业务品种 取字典")
    private String serviceCateg;
    /**
    * 融资用途 取字典
    */
    @ApiModelProperty(value = "融资用途 取字典")
    private String loanUse;
    /**
    * 是否首次贷款
    */
    @ApiModelProperty(value = "是否首次贷款")
    private Integer isFirstLoan;
    /**
    * 分支行
    */
    @ApiModelProperty(value = "分支行")
    private String branchBank;
    /**
    * 主债权金额
    */
    @ApiModelProperty(value = "主债权金额")
    private BigDecimal mainDebtAmt;
    /**
    * 借款合同号
    */
    @ApiModelProperty(value = "借款合同号")
    private String loanContNo;
    /**
    * 主债权起始日期
    */
    @ApiModelProperty(value = "主债权起始日期")
    private Date mainDebtStartDate;
    /**
    * 主债权到期日
    */
    @ApiModelProperty(value = "主债权到期日")
    private Date mainDeptEndDate;
    /**
    * 贷款利率
    */
    @ApiModelProperty(value = "贷款利率")
    private BigDecimal loanRate;
    /**
    * 保证合同号
    */
    @ApiModelProperty(value = "保证合同号")
    private String guarContNo;
    /**
    * 委保合同号
    */
    @ApiModelProperty(value = "委保合同号")
    private String securContNo;
    /**
    * 担保费率
    */
    @ApiModelProperty(value = "担保费率")
    private BigDecimal guarRate;
    /**
    * 反担保措施
    */
    @ApiModelProperty(value = "反担保措施")
    private String protectGuarMethod;
    /**
    * 原担保机构名称
    */
    @ApiModelProperty(value = "原担保机构名称")
    private String originGuarOrgName;
    /**
    * 原担保机构证件号码
    */
    @ApiModelProperty(value = "原担保机构证件号码")
    private String originGuarOrgCertNo;
    /**
    * 市再担机构名称
    */
    @ApiModelProperty(value = "市再担机构名称")
    private String cityReguarOrgName;
    /**
    * 市再担机构证件号码
    */
    @ApiModelProperty(value = "市再担机构证件号码")
    private String cityReguarOrgCertNo;
    /**
    * 省再担机构名称
    */
    @ApiModelProperty(value = "省再担机构名称")
    private String provReguarOrgName;
    /**
    * 省再担机构证件号码
    */
    @ApiModelProperty(value = "省再担机构证件号码")
    private String provReguarOrgCertNo;
    /**
    * [再]担保期限(月)
    */
    @ApiModelProperty(value = "[再]担保期限(月)")
    private Integer reguarPeriod;
    /**
    * [再]担保费率(省级)(年)
    */
    @ApiModelProperty(value = "[再]担保费率(省级)(年)")
    private BigDecimal reguarRateProv;
    /**
    * [再]合计担保费(省级)(元)
    */
    @ApiModelProperty(value = "[再]合计担保费(省级)(元)")
    private BigDecimal reguarAmtProvSum;
    /**
    * [再]本次担保费(省级)(元)
    */
    @ApiModelProperty(value = "[再]本次担保费(省级)(元)")
    private BigDecimal reguarAmtProvCur;
    /**
    * [再]担保费率(国担)(年)
    */
    @ApiModelProperty(value = "[再]担保费率(国担)(年)")
    private BigDecimal reguarAmtRateNation;
    /**
    * [再]合计担保费(国担)(元)
    */
    @ApiModelProperty(value = "[再]合计担保费(国担)(元)")
    private BigDecimal reguarAmtNationSum;
    /**
    * [再]本次担保费(国担)(元)
    */
    @ApiModelProperty(value = "[再]本次担保费(国担)(元)")
    private BigDecimal reguarAmtNationCur;
    /**
    * 分险比例（债权人）
    */
    @ApiModelProperty(value = "分险比例（债权人）")
    private BigDecimal riskRateDebtee;
    /**
    * 分险比例（原担保）
    */
    @ApiModelProperty(value = "分险比例（原担保）")
    private BigDecimal riskRateOriginGuar;
    /**
    * 分险比例(市级)(再担保)
    */
    @ApiModelProperty(value = "分险比例(市级)(再担保)")
    private BigDecimal riskRateCityReguar;
    /**
    * 分险比例(省级)(再担保）
    */
    @ApiModelProperty(value = "分险比例(省级)(再担保）")
    private BigDecimal riskRateProvinceReguar;
    /**
    * 分险比例（其他）
    */
    @ApiModelProperty(value = "分险比例（其他）")
    private BigDecimal riskRateOther;
    /**
    * 分险比例（国担）
    */
    @ApiModelProperty(value = "分险比例（国担）")
    private BigDecimal riskRateNation;
    /**
    * 分险比例（工信部）
    */
    @ApiModelProperty(value = "分险比例（工信部）")
    private BigDecimal riskRateTechInfo;
    /**
    * 第N次备案
    */
    @ApiModelProperty(value = "第N次备案")
    private Integer regCnt;
    /**
    * 创建时间
    */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    /**
    * 修改时间
    */
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;
    /**
    * 创建人
    */
    @ApiModelProperty(value = "创建人")
    private String creator;
    /**
    * 修改人
    */
    @ApiModelProperty(value = "修改人")
    private String updator;
    /**
    * 删除标记
    */
    @ApiModelProperty(value = "删除标记")
    private String delFlag;
    /**
    * 备注
    */
    @ApiModelProperty(value = "备注")
    private String remark;

}
