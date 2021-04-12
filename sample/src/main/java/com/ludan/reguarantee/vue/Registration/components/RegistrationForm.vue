
<template>
    <a-spin :spinning="loading">
        <a-form :form="form" v-bind="formLayout">
            <!-- 检查是否有 id 并且大于0，大于0是修改。其他是新增，新增不显示主键ID -->
                <a-form-item label="雪花算法生成的递增主键" >
                        <a-input v-decorator="['id',validatorRules.id ]"/>
                </a-form-item>
                <a-form-item label="工作流nodeId" >
                        <a-input v-decorator="['wfNodeId',validatorRules.wfNodeId ]"/>
                </a-form-item>
                <a-form-item label="工作流实例id" >
                        <a-input v-decorator="['wfInstanceId',validatorRules.wfInstanceId ]"/>
                </a-form-item>
                <a-form-item label="项目状态" >
                        <a-input v-decorator="['status',validatorRules.status ]"/>
                </a-form-item>
                <a-form-item label="业务编号" >
                        <a-input v-decorator="['code',validatorRules.code ]"/>
                </a-form-item>
                <a-form-item label="客户名称" >
                        <a-input v-decorator="['custName',validatorRules.custName ]"/>
                </a-form-item>
                <a-form-item label="客户性质 取字典值" >
                        <a-select v-decorator="['custNature',validatorRules.custNature ]">
                            <a-select-option value="">请选择</a-select-option>
                            <a-select-option v-for="(item, name) in pageDict.custNature" :key="name" :value="item.code">
                                {{ item.value }}
                            </a-select-option>
                        </a-select>
                </a-form-item>
                <a-form-item label="客户证件号类别 取字典值" >
                        <a-select v-decorator="['custCertType',validatorRules.custCertType ]">
                            <a-select-option value="">请选择</a-select-option>
                            <a-select-option v-for="(item, name) in pageDict.custCertType" :key="name" :value="item.code">
                                {{ item.value }}
                            </a-select-option>
                        </a-select>
                </a-form-item>
                <a-form-item label="证件号码" >
                        <a-input v-decorator="['custCertNo',validatorRules.custCertNo ]"/>
                </a-form-item>
                <a-form-item label="联系电话" >
                        <a-input v-decorator="['custLinkTel',validatorRules.custLinkTel ]"/>
                </a-form-item>
                <a-form-item label="客户邮编" >
                        <a-input v-decorator="['custPostCode',validatorRules.custPostCode ]"/>
                </a-form-item>
                <a-form-item label="客户邮件" >
                        <a-input v-decorator="['custEmail',validatorRules.custEmail ]"/>
                </a-form-item>
                <a-form-item label="法人姓名" >
                        <a-input v-decorator="['legalName',validatorRules.legalName ]"/>
                </a-form-item>
                <a-form-item label="法人联系方式" >
                        <a-input v-decorator="['legalLinkTel',validatorRules.legalLinkTel ]"/>
                </a-form-item>
                <a-form-item label="法人身份证号" >
                        <a-input v-decorator="['legalCertNo',validatorRules.legalCertNo ]"/>
                </a-form-item>
                <a-form-item label="所属行业(国家)" >
                        <a-input v-decorator="['industCategNation',validatorRules.industCategNation ]"/>
                </a-form-item>
                <a-form-item label="所属行业(工业)" >
                        <a-input v-decorator="['industCateg',validatorRules.industCateg ]"/>
                </a-form-item>
                <a-form-item label="从业人数(上一年末)" >
                        <a-input-number v-decorator="['workNumLastYear',validatorRules.workNumLastYear ]" :min="1" :max="10" style="width:100%"/>
                </a-form-item>
                <a-form-item label="上一年末资产总额" >
                        <a-input-number v-decorator="['assetAmtLastYear',validatorRules.assetAmtLastYear ]" :min="1" :max="10" style="width:100%"/>
                </a-form-item>
                <a-form-item label="营业额" >
                        <a-input-number v-decorator="['incomeAmt',validatorRules.incomeAmt ]" :min="1" :max="10" style="width:100%"/>
                </a-form-item>
                <a-form-item label="纳税额" >
                        <a-input-number v-decorator="['taxAmt',validatorRules.taxAmt ]" :min="1" :max="10" style="width:100%"/>
                </a-form-item>
                <a-form-item label="企业划型，大中小微等" >
                        <a-input v-decorator="['compType',validatorRules.compType ]"/>
                </a-form-item>
                <a-form-item label="政策扶持领域类别(服务对象类型)" >
                        <a-input v-decorator="['policySupportFieldType',validatorRules.policySupportFieldType ]"/>
                </a-form-item>
                <a-form-item label="存续状态" >
                        <a-input v-decorator="['standingStatus',validatorRules.standingStatus ]"/>
                </a-form-item>
                <a-form-item label="登记地址" >
                        <a-input v-decorator="['regAddr',validatorRules.regAddr ]"/>
                </a-form-item>
                <a-form-item label="省" >
                        <a-input v-decorator="['provinceName',validatorRules.provinceName ]"/>
                </a-form-item>
                <a-form-item label="市" >
                        <a-input v-decorator="['cityName',validatorRules.cityName ]"/>
                </a-form-item>
                <a-form-item label="县/区" >
                        <a-input v-decorator="['countyName',validatorRules.countyName ]"/>
                </a-form-item>
                <a-form-item label="上报批次" >
                        <a-input v-decorator="['reportBatch',validatorRules.reportBatch ]"/>
                </a-form-item>
                <a-form-item label="债务人名称" >
                        <a-input v-decorator="['debtorName',validatorRules.debtorName ]"/>
                </a-form-item>
                <a-form-item label="业务品种 取字典" >
                        <a-select v-decorator="['serviceCateg',validatorRules.serviceCateg ]">
                            <a-select-option value="">请选择</a-select-option>
                            <a-select-option v-for="(item, name) in pageDict.serviceCateg" :key="name" :value="item.code">
                                {{ item.value }}
                            </a-select-option>
                        </a-select>
                </a-form-item>
                <a-form-item label="融资用途 取字典" >
                        <a-select v-decorator="['loanUse',validatorRules.loanUse ]">
                            <a-select-option value="">请选择</a-select-option>
                            <a-select-option v-for="(item, name) in pageDict.loanUse" :key="name" :value="item.code">
                                {{ item.value }}
                            </a-select-option>
                        </a-select>
                </a-form-item>
                <a-form-item label="是否首次贷款" >
                        <a-input-number v-decorator="['isFirstLoan',validatorRules.isFirstLoan ]" :min="1" :max="10" style="width:100%"/>
                </a-form-item>
                <a-form-item label="分支行" >
                        <a-input v-decorator="['branchBank',validatorRules.branchBank ]"/>
                </a-form-item>
                <a-form-item label="主债权金额" >
                        <a-input-number v-decorator="['mainDebtAmt',validatorRules.mainDebtAmt ]" :min="1" :max="10" style="width:100%"/>
                </a-form-item>
                <a-form-item label="借款合同号" >
                        <a-input v-decorator="['loanContNo',validatorRules.loanContNo ]"/>
                </a-form-item>
                <a-form-item label="主债权起始日期" >
                        <a-date-picker v-decorator="['mainDebtStartDate',validatorRules.mainDebtStartDate ]" format="YYYY-MM-DD HH:mm:ss" show-time style="width: 100%"/>
                </a-form-item>
                <a-form-item label="主债权到期日" >
                        <a-date-picker v-decorator="['mainDeptEndDate',validatorRules.mainDeptEndDate ]" format="YYYY-MM-DD HH:mm:ss" show-time style="width: 100%"/>
                </a-form-item>
                <a-form-item label="贷款利率" >
                        <a-input-number v-decorator="['loanRate',validatorRules.loanRate ]" :min="1" :max="10" style="width:100%"/>
                </a-form-item>
                <a-form-item label="保证合同号" >
                        <a-input v-decorator="['guarContNo',validatorRules.guarContNo ]"/>
                </a-form-item>
                <a-form-item label="委保合同号" >
                        <a-input v-decorator="['securContNo',validatorRules.securContNo ]"/>
                </a-form-item>
                <a-form-item label="担保费率" >
                        <a-input-number v-decorator="['guarRate',validatorRules.guarRate ]" :min="1" :max="10" style="width:100%"/>
                </a-form-item>
                <a-form-item label="反担保措施" >
                        <a-input v-decorator="['protectGuarMethod',validatorRules.protectGuarMethod ]"/>
                </a-form-item>
                <a-form-item label="原担保机构名称" >
                        <a-input v-decorator="['originGuarOrgName',validatorRules.originGuarOrgName ]"/>
                </a-form-item>
                <a-form-item label="原担保机构证件号码" >
                        <a-input v-decorator="['originGuarOrgCertNo',validatorRules.originGuarOrgCertNo ]"/>
                </a-form-item>
                <a-form-item label="市再担机构名称" >
                        <a-input v-decorator="['cityReguarOrgName',validatorRules.cityReguarOrgName ]"/>
                </a-form-item>
                <a-form-item label="市再担机构证件号码" >
                        <a-input v-decorator="['cityReguarOrgCertNo',validatorRules.cityReguarOrgCertNo ]"/>
                </a-form-item>
                <a-form-item label="省再担机构名称" >
                        <a-input v-decorator="['provReguarOrgName',validatorRules.provReguarOrgName ]"/>
                </a-form-item>
                <a-form-item label="省再担机构证件号码" >
                        <a-input v-decorator="['provReguarOrgCertNo',validatorRules.provReguarOrgCertNo ]"/>
                </a-form-item>
                <a-form-item label="[再]担保期限(月)" >
                        <a-input-number v-decorator="['reguarPeriod',validatorRules.reguarPeriod ]" :min="1" :max="10" style="width:100%"/>
                </a-form-item>
                <a-form-item label="[再]担保费率(省级)(年)" >
                        <a-input-number v-decorator="['reguarRateProv',validatorRules.reguarRateProv ]" :min="1" :max="10" style="width:100%"/>
                </a-form-item>
                <a-form-item label="[再]合计担保费(省级)(元)" >
                        <a-input-number v-decorator="['reguarAmtProvSum',validatorRules.reguarAmtProvSum ]" :min="1" :max="10" style="width:100%"/>
                </a-form-item>
                <a-form-item label="[再]本次担保费(省级)(元)" >
                        <a-input-number v-decorator="['reguarAmtProvCur',validatorRules.reguarAmtProvCur ]" :min="1" :max="10" style="width:100%"/>
                </a-form-item>
                <a-form-item label="[再]担保费率(国担)(年)" >
                        <a-input-number v-decorator="['reguarAmtRateNation',validatorRules.reguarAmtRateNation ]" :min="1" :max="10" style="width:100%"/>
                </a-form-item>
                <a-form-item label="[再]合计担保费(国担)(元)" >
                        <a-input-number v-decorator="['reguarAmtNationSum',validatorRules.reguarAmtNationSum ]" :min="1" :max="10" style="width:100%"/>
                </a-form-item>
                <a-form-item label="[再]本次担保费(国担)(元)" >
                        <a-input-number v-decorator="['reguarAmtNationCur',validatorRules.reguarAmtNationCur ]" :min="1" :max="10" style="width:100%"/>
                </a-form-item>
                <a-form-item label="分险比例（债权人）" >
                        <a-input-number v-decorator="['riskRateDebtee',validatorRules.riskRateDebtee ]" :min="1" :max="10" style="width:100%"/>
                </a-form-item>
                <a-form-item label="分险比例（原担保）" >
                        <a-input-number v-decorator="['riskRateOriginGuar',validatorRules.riskRateOriginGuar ]" :min="1" :max="10" style="width:100%"/>
                </a-form-item>
                <a-form-item label="分险比例(市级)(再担保)" >
                        <a-input-number v-decorator="['riskRateCityReguar',validatorRules.riskRateCityReguar ]" :min="1" :max="10" style="width:100%"/>
                </a-form-item>
                <a-form-item label="分险比例(省级)(再担保）" >
                        <a-input-number v-decorator="['riskRateProvinceReguar',validatorRules.riskRateProvinceReguar ]" :min="1" :max="10" style="width:100%"/>
                </a-form-item>
                <a-form-item label="分险比例（其他）" >
                        <a-input-number v-decorator="['riskRateOther',validatorRules.riskRateOther ]" :min="1" :max="10" style="width:100%"/>
                </a-form-item>
                <a-form-item label="分险比例（国担）" >
                        <a-input-number v-decorator="['riskRateNation',validatorRules.riskRateNation ]" :min="1" :max="10" style="width:100%"/>
                </a-form-item>
                <a-form-item label="分险比例（工信部）" >
                        <a-input-number v-decorator="['riskRateTechInfo',validatorRules.riskRateTechInfo ]" :min="1" :max="10" style="width:100%"/>
                </a-form-item>
                <a-form-item label="第N次备案" >
                        <a-input-number v-decorator="['regCnt',validatorRules.regCnt ]" :min="1" :max="10" style="width:100%"/>
                </a-form-item>
                <a-form-item label="创建时间" >
                        <a-date-picker v-decorator="['createTime',validatorRules.createTime ]" format="YYYY-MM-DD HH:mm:ss" show-time style="width: 100%"/>
                </a-form-item>
                <a-form-item label="修改时间" >
                        <a-date-picker v-decorator="['updateTime',validatorRules.updateTime ]" format="YYYY-MM-DD HH:mm:ss" show-time style="width: 100%"/>
                </a-form-item>
                <a-form-item label="创建人" >
                        <a-input v-decorator="['creator',validatorRules.creator ]"/>
                </a-form-item>
                <a-form-item label="修改人" >
                        <a-input v-decorator="['updator',validatorRules.updator ]"/>
                </a-form-item>
                <a-form-item label="删除标记" >
                        <a-input v-decorator="['delFlag',validatorRules.delFlag ]"/>
                </a-form-item>
                <a-form-item label="备注" >
                        <a-input v-decorator="['remark',validatorRules.remark ]"/>
                </a-form-item>
        </a-form>
    </a-spin>
</template>

<script>
    import pick from 'lodash.pick'
    import { httpGet, httpPost, httpDelete, httpPut, downFile } from '@/utils/httpClient'
    import { getDictionaryByCodes } from '@/utils/dictUtil'
    import FileUploadMixin from '@/core/mixins/FileUploadMixin'
    import FormMixin from '@/core/mixins/FormMixin'
    import storage from 'store'


    // 表单字段
    // const fields = ['id', 'dictCode', 'code', 'value', 'parentDictValueCode', 'orderBy', 'extA', 'extB', 'enable']
    const fields = [
        'id',
        'wfNodeId',
        'wfInstanceId',
        'status',
        'code',
        'custName',
        'custNature',
        'custCertType',
        'custCertNo',
        'custLinkTel',
        'custPostCode',
        'custEmail',
        'legalName',
        'legalLinkTel',
        'legalCertNo',
        'industCategNation',
        'industCateg',
        'workNumLastYear',
        'assetAmtLastYear',
        'incomeAmt',
        'taxAmt',
        'compType',
        'policySupportFieldType',
        'standingStatus',
        'regAddr',
        'provinceName',
        'cityName',
        'countyName',
        'reportBatch',
        'debtorName',
        'serviceCateg',
        'loanUse',
        'isFirstLoan',
        'branchBank',
        'mainDebtAmt',
        'loanContNo',
        'mainDebtStartDate',
        'mainDeptEndDate',
        'loanRate',
        'guarContNo',
        'securContNo',
        'guarRate',
        'protectGuarMethod',
        'originGuarOrgName',
        'originGuarOrgCertNo',
        'cityReguarOrgName',
        'cityReguarOrgCertNo',
        'provReguarOrgName',
        'provReguarOrgCertNo',
        'reguarPeriod',
        'reguarRateProv',
        'reguarAmtProvSum',
        'reguarAmtProvCur',
        'reguarAmtRateNation',
        'reguarAmtNationSum',
        'reguarAmtNationCur',
        'riskRateDebtee',
        'riskRateOriginGuar',
        'riskRateCityReguar',
        'riskRateProvinceReguar',
        'riskRateOther',
        'riskRateNation',
        'riskRateTechInfo',
        'regCnt',
        'createTime',
        'updateTime',
        'creator',
        'updator',
        'delFlag',
        'remark',
    ]

    const StringToArrFields = [
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    ]


    export default {
        props: {
        },
        mixins: [FileUploadMixin, FormMixin],
        data () {
            return {
                uploadApi: '/api-file/files/upload',
                headers: {
                  Authorization: `Bearer ${storage.get('Access-Token')}`,
                },
                model: {},
                loading: false,
                // 布局
                formLayout: {
                    labelCol: {
                        xs: { span: 24 },
                        sm: { span: 5 },
                        md: { span: 5 },
                        lg: { span: 5 },
                        xl: { span: 5 },
                        xxl: { span: 5 }
                    },
                    wrapperCol: {
                        xs: { span: 24 },
                        sm: { span: 16 },
                        md: { span: 16 },
                        lg: { span: 16 },
                        xl: { span: 16 },
                        xxl: { span: 16 }
                    }
                },
                //页面级字典
                pageDict: {},
                validatorRules: {
                    id:{rules:[{required:true,message:"雪花算法生成的递增主键不能为空"},{ validator: this.validateId }]},
                    wfNodeId:{rules:[{required:false,message:"工作流nodeId不能为空"},{ validator: this.validateWfNodeId }]},
                    wfInstanceId:{rules:[{required:false,message:"工作流实例id不能为空"},{ validator: this.validateWfInstanceId }]},
                    status:{rules:[{required:false,message:"项目状态不能为空"},{ validator: this.validateStatus }]},
                    code:{rules:[{required:false,message:"业务编号不能为空"},{ validator: this.validateCode }]},
                    custName:{rules:[{required:false,message:"客户名称不能为空"},{ validator: this.validateCustName }]},
                    custNature:{rules:[{required:false,message:"客户性质 取字典值不能为空"},{ validator: this.validateCustNature }]},
                    custCertType:{rules:[{required:false,message:"客户证件号类别 取字典值不能为空"},{ validator: this.validateCustCertType }]},
                    custCertNo:{rules:[{required:false,message:"证件号码不能为空"},{ validator: this.validateCustCertNo }]},
                    custLinkTel:{rules:[{required:false,message:"联系电话不能为空"},{ validator: this.validateCustLinkTel }]},
                    custPostCode:{rules:[{required:false,message:"客户邮编不能为空"},{ validator: this.validateCustPostCode }]},
                    custEmail:{rules:[{required:false,message:"客户邮件不能为空"},{ validator: this.validateCustEmail }]},
                    legalName:{rules:[{required:false,message:"法人姓名不能为空"},{ validator: this.validateLegalName }]},
                    legalLinkTel:{rules:[{required:false,message:"法人联系方式不能为空"},{ validator: this.validateLegalLinkTel }]},
                    legalCertNo:{rules:[{required:false,message:"法人身份证号不能为空"},{ validator: this.validateLegalCertNo }]},
                    industCategNation:{rules:[{required:false,message:"所属行业(国家)不能为空"},{ validator: this.validateIndustCategNation }]},
                    industCateg:{rules:[{required:false,message:"所属行业(工业)不能为空"},{ validator: this.validateIndustCateg }]},
                    workNumLastYear:{rules:[{required:false,message:"从业人数(上一年末)不能为空"},{ validator: this.validateWorkNumLastYear }]},
                    assetAmtLastYear:{rules:[{required:false,message:"上一年末资产总额不能为空"},{ validator: this.validateAssetAmtLastYear }]},
                    incomeAmt:{rules:[{required:false,message:"营业额不能为空"},{ validator: this.validateIncomeAmt }]},
                    taxAmt:{rules:[{required:false,message:"纳税额不能为空"},{ validator: this.validateTaxAmt }]},
                    compType:{rules:[{required:true,message:"企业划型，大中小微等不能为空"},{ validator: this.validateCompType }]},
                    policySupportFieldType:{rules:[{required:false,message:"政策扶持领域类别(服务对象类型)不能为空"},{ validator: this.validatePolicySupportFieldType }]},
                    standingStatus:{rules:[{required:false,message:"存续状态不能为空"},{ validator: this.validateStandingStatus }]},
                    regAddr:{rules:[{required:false,message:"登记地址不能为空"},{ validator: this.validateRegAddr }]},
                    provinceName:{rules:[{required:false,message:"省不能为空"},{ validator: this.validateProvinceName }]},
                    cityName:{rules:[{required:false,message:"市不能为空"},{ validator: this.validateCityName }]},
                    countyName:{rules:[{required:false,message:"县/区不能为空"},{ validator: this.validateCountyName }]},
                    reportBatch:{rules:[{required:false,message:"上报批次不能为空"},{ validator: this.validateReportBatch }]},
                    debtorName:{rules:[{required:false,message:"债务人名称不能为空"},{ validator: this.validateDebtorName }]},
                    serviceCateg:{rules:[{required:false,message:"业务品种 取字典不能为空"},{ validator: this.validateServiceCateg }]},
                    loanUse:{rules:[{required:false,message:"融资用途 取字典不能为空"},{ validator: this.validateLoanUse }]},
                    isFirstLoan:{rules:[{required:false,message:"是否首次贷款不能为空"},{ validator: this.validateIsFirstLoan }]},
                    branchBank:{rules:[{required:false,message:"分支行不能为空"},{ validator: this.validateBranchBank }]},
                    mainDebtAmt:{rules:[{required:false,message:"主债权金额不能为空"},{ validator: this.validateMainDebtAmt }]},
                    loanContNo:{rules:[{required:false,message:"借款合同号不能为空"},{ validator: this.validateLoanContNo }]},
                    mainDebtStartDate:{rules:[{required:false,message:"主债权起始日期不能为空"},{ validator: this.validateMainDebtStartDate }]},
                    mainDeptEndDate:{rules:[{required:false,message:"主债权到期日不能为空"},{ validator: this.validateMainDeptEndDate }]},
                    loanRate:{rules:[{required:false,message:"贷款利率不能为空"},{ validator: this.validateLoanRate }]},
                    guarContNo:{rules:[{required:false,message:"保证合同号不能为空"},{ validator: this.validateGuarContNo }]},
                    securContNo:{rules:[{required:false,message:"委保合同号不能为空"},{ validator: this.validateSecurContNo }]},
                    guarRate:{rules:[{required:false,message:"担保费率不能为空"},{ validator: this.validateGuarRate }]},
                    protectGuarMethod:{rules:[{required:false,message:"反担保措施不能为空"},{ validator: this.validateProtectGuarMethod }]},
                    originGuarOrgName:{rules:[{required:false,message:"原担保机构名称不能为空"},{ validator: this.validateOriginGuarOrgName }]},
                    originGuarOrgCertNo:{rules:[{required:false,message:"原担保机构证件号码不能为空"},{ validator: this.validateOriginGuarOrgCertNo }]},
                    cityReguarOrgName:{rules:[{required:false,message:"市再担机构名称不能为空"},{ validator: this.validateCityReguarOrgName }]},
                    cityReguarOrgCertNo:{rules:[{required:false,message:"市再担机构证件号码不能为空"},{ validator: this.validateCityReguarOrgCertNo }]},
                    provReguarOrgName:{rules:[{required:false,message:"省再担机构名称不能为空"},{ validator: this.validateProvReguarOrgName }]},
                    provReguarOrgCertNo:{rules:[{required:false,message:"省再担机构证件号码不能为空"},{ validator: this.validateProvReguarOrgCertNo }]},
                    reguarPeriod:{rules:[{required:false,message:"[再]担保期限(月)不能为空"},{ validator: this.validateReguarPeriod }]},
                    reguarRateProv:{rules:[{required:false,message:"[再]担保费率(省级)(年)不能为空"},{ validator: this.validateReguarRateProv }]},
                    reguarAmtProvSum:{rules:[{required:false,message:"[再]合计担保费(省级)(元)不能为空"},{ validator: this.validateReguarAmtProvSum }]},
                    reguarAmtProvCur:{rules:[{required:false,message:"[再]本次担保费(省级)(元)不能为空"},{ validator: this.validateReguarAmtProvCur }]},
                    reguarAmtRateNation:{rules:[{required:false,message:"[再]担保费率(国担)(年)不能为空"},{ validator: this.validateReguarAmtRateNation }]},
                    reguarAmtNationSum:{rules:[{required:false,message:"[再]合计担保费(国担)(元)不能为空"},{ validator: this.validateReguarAmtNationSum }]},
                    reguarAmtNationCur:{rules:[{required:false,message:"[再]本次担保费(国担)(元)不能为空"},{ validator: this.validateReguarAmtNationCur }]},
                    riskRateDebtee:{rules:[{required:false,message:"分险比例（债权人）不能为空"},{ validator: this.validateRiskRateDebtee }]},
                    riskRateOriginGuar:{rules:[{required:false,message:"分险比例（原担保）不能为空"},{ validator: this.validateRiskRateOriginGuar }]},
                    riskRateCityReguar:{rules:[{required:false,message:"分险比例(市级)(再担保)不能为空"},{ validator: this.validateRiskRateCityReguar }]},
                    riskRateProvinceReguar:{rules:[{required:false,message:"分险比例(省级)(再担保）不能为空"},{ validator: this.validateRiskRateProvinceReguar }]},
                    riskRateOther:{rules:[{required:false,message:"分险比例（其他）不能为空"},{ validator: this.validateRiskRateOther }]},
                    riskRateNation:{rules:[{required:false,message:"分险比例（国担）不能为空"},{ validator: this.validateRiskRateNation }]},
                    riskRateTechInfo:{rules:[{required:false,message:"分险比例（工信部）不能为空"},{ validator: this.validateRiskRateTechInfo }]},
                    regCnt:{rules:[{required:false,message:"第N次备案不能为空"},{ validator: this.validateRegCnt }]},
                    createTime:{rules:[{required:false,message:"创建时间不能为空"},{ validator: this.validateCreateTime }]},
                    updateTime:{rules:[{required:false,message:"修改时间不能为空"},{ validator: this.validateUpdateTime }]},
                    creator:{rules:[{required:false,message:"创建人不能为空"},{ validator: this.validateCreator }]},
                    updator:{rules:[{required:false,message:"修改人不能为空"},{ validator: this.validateUpdator }]},
                    delFlag:{rules:[{required:false,message:"删除标记不能为空"},{ validator: this.validateDelFlag }]},
                    remark:{rules:[{required:false,message:"备注不能为空"},{ validator: this.validateRemark }]},
                },
                urls: {
                    add: '/api-sample/Registration/add',
                    edit: '/api-sample/Registration/edit'
                }
            }
        },
        beforeCreate () {},
        created () {
            console.log('custom modal created')
            // 初始化字典配置 在自己页面定义
            this.initDictConfig()
            this.form = this.$form.createForm(this)

            // 防止表单未注册
            fields.forEach((v) => this.form.getFieldDecorator(v))

            // 当 model 发生改变时，为表单设置值
            // this.$watch('model', () => {
            //   console.log('model change', this.model)
            //   this.model && this.form.setFieldsValue(pick(this.model, fields))
            // })
        },
        mounted () {
            // vue 生命周期钩子，已完成模板渲染，此处可以进行dom操作
        },
        methods: {
            initDictConfig(){
                console.log('初始化页面级字典项')
                const dictCodes = [
                    'custNature',
                    'custCertType',
                ]
                getDictionaryByCodes(dictCodes).then((res) => {
                    this.pageDict = res
                })
            },
            beforeUpload() {
              // 上传前 的图片校验等操作
              return true
            },
            normFile(e) {
              console.log('Upload event:', e);
              if (Array.isArray(e)) {
                return e;
              }
              return e && e.fileList;
            },
            handleFileUpload(type, info) {
              console.log('上传组件状态改变', type, info)
              let fileList = [...info.fileList]
              // 2. read from response and show file link
              fileList = fileList
                .filter((item) => {
                  if (item.response && item.error) {
                    this.$message.error('文件上传失败')
                    return false
                  }
                  return true
                })
                .map((file) => {
                  if (file.response) {
                    // Component will show file.url as link
                    file.url = file.response.url
                    file.attFileType = type
                    file.attFileId = file.response.id
                    file.attFileName = file.name
                  }
                  return file
                })
            },
            handleChange () {
                console.log(arguments)
            },
            add () {
                this.edit({})
            },
            edit (record) {
                console.log('edit::', record)
                this.model = Object.assign({}, record)

                let newModel = JSON.parse(JSON.stringify(this.model));
                for(let i = 0 ; i<StringToArrFields.length ; i++) {
                  if( newModel.hasOwnProperty(StringToArrFields[i]) ){
                    newModel[StringToArrFields[i]] = newModel[StringToArrFields[i]] ? JSON.parse(newModel[StringToArrFields[i]]) : []
                  }
                }

                this.model = newModel;

                this.form.resetFields()
                this.$nextTick(() => {
                    this.model && this.form.setFieldsValue(pick(this.model, fields))
                })
            },

            submit () {
                // 触发表单验证
                this.form.validateFields((err, values) => {
                    if (!err) {
                        this.loading = true
                        let formData = Object.assign(this.model, values)
                        for (let i = 0; i < StringToArrFields.length; i++) {
                          if (formData.hasOwnProperty(StringToArrFields[i]) && formData[StringToArrFields[i]]) {
                            formData[StringToArrFields[i]] = JSON.stringify(formData[StringToArrFields[i]])
                          }
                        }

                        let httpRequest = null
                        if (!this.model.id) {
                            httpRequest = httpPost(this.urls.add, formData)
                        } else {
                            httpRequest = httpPut(this.urls.edit, formData)
                        }
                        console.log('表单提交数据', formData)
                        httpRequest
                            .then(
                                (res) => {
                                    if (res.resp_code === 0) {
                                        this.$message.success(res.resp_msg)
                                        this.$emit('ok')
                                    } else {
                                        this.$message.warning(res.resp_msg)
                                    }
                                },
                                (err, con) => {
                                    this.$message.warning('保存失败！')
                                    console.log(err, con)
                                }
                            )
                            .finally(() => {
                                this.loading = false
                            })
                    }
                })
            },
            // 自定义校验逻辑代码写在这里
            // 若校验成功，回调callback，参数为空 ： callback()
            // 若校验失败，回调callback ： callback('校验失败提示信息')
            // 示例如下：
            // 示例1：正则校验
            // eslint-disable-next-line standard/no-callback-literal
            // if (!value || new RegExp(/^1[3|4|5|7|8][0-9]\d{8}$/).test(value)) {
            //   callback()
            // } else {
            //   // eslint-disable-next-line standard/no-callback-literal
            //   callback('请输入正确格式的手机号码!')
            // }
            // 示例2：获取form中一个值，与当前输入值尽心比较
            // const form = this.form
            // const confirmPassword = form.getFieldValue('confirmPassword')
            // if (value && confirmPassword && value !== confirmPassword) {
            //   // eslint-disable-next-line standard/no-callback-literal
            //   callback('两次输入的密码不一样！')
            // }
            // if (value && this.confirmDirty) {
            //   form.validateFields(['confirm'], { force: true })
            // }
            // callback()
            validateId (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的雪花算法生成的递增主键')
            },
            validateWfNodeId (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的工作流nodeId')
            },
            validateWfInstanceId (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的工作流实例id')
            },
            validateStatus (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的项目状态')
            },
            validateCode (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的业务编号')
            },
            validateCustName (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的客户名称')
            },
            validateCustNature (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的客户性质 取字典值')
            },
            validateCustCertType (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的客户证件号类别 取字典值')
            },
            validateCustCertNo (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的证件号码')
            },
            validateCustLinkTel (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的联系电话')
            },
            validateCustPostCode (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的客户邮编')
            },
            validateCustEmail (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的客户邮件')
            },
            validateLegalName (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的法人姓名')
            },
            validateLegalLinkTel (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的法人联系方式')
            },
            validateLegalCertNo (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的法人身份证号')
            },
            validateIndustCategNation (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的所属行业(国家)')
            },
            validateIndustCateg (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的所属行业(工业)')
            },
            validateWorkNumLastYear (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的从业人数(上一年末)')
            },
            validateAssetAmtLastYear (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的上一年末资产总额')
            },
            validateIncomeAmt (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的营业额')
            },
            validateTaxAmt (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的纳税额')
            },
            validateCompType (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的企业划型，大中小微等')
            },
            validatePolicySupportFieldType (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的政策扶持领域类别(服务对象类型)')
            },
            validateStandingStatus (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的存续状态')
            },
            validateRegAddr (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的登记地址')
            },
            validateProvinceName (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的省')
            },
            validateCityName (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的市')
            },
            validateCountyName (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的县/区')
            },
            validateReportBatch (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的上报批次')
            },
            validateDebtorName (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的债务人名称')
            },
            validateServiceCateg (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的业务品种 取字典')
            },
            validateLoanUse (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的融资用途 取字典')
            },
            validateIsFirstLoan (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的是否首次贷款')
            },
            validateBranchBank (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的分支行')
            },
            validateMainDebtAmt (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的主债权金额')
            },
            validateLoanContNo (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的借款合同号')
            },
            validateMainDebtStartDate (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的主债权起始日期')
            },
            validateMainDeptEndDate (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的主债权到期日')
            },
            validateLoanRate (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的贷款利率')
            },
            validateGuarContNo (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的保证合同号')
            },
            validateSecurContNo (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的委保合同号')
            },
            validateGuarRate (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的担保费率')
            },
            validateProtectGuarMethod (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的反担保措施')
            },
            validateOriginGuarOrgName (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的原担保机构名称')
            },
            validateOriginGuarOrgCertNo (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的原担保机构证件号码')
            },
            validateCityReguarOrgName (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的市再担机构名称')
            },
            validateCityReguarOrgCertNo (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的市再担机构证件号码')
            },
            validateProvReguarOrgName (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的省再担机构名称')
            },
            validateProvReguarOrgCertNo (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的省再担机构证件号码')
            },
            validateReguarPeriod (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的[再]担保期限(月)')
            },
            validateReguarRateProv (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的[再]担保费率(省级)(年)')
            },
            validateReguarAmtProvSum (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的[再]合计担保费(省级)(元)')
            },
            validateReguarAmtProvCur (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的[再]本次担保费(省级)(元)')
            },
            validateReguarAmtRateNation (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的[再]担保费率(国担)(年)')
            },
            validateReguarAmtNationSum (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的[再]合计担保费(国担)(元)')
            },
            validateReguarAmtNationCur (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的[再]本次担保费(国担)(元)')
            },
            validateRiskRateDebtee (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的分险比例（债权人）')
            },
            validateRiskRateOriginGuar (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的分险比例（原担保）')
            },
            validateRiskRateCityReguar (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的分险比例(市级)(再担保)')
            },
            validateRiskRateProvinceReguar (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的分险比例(省级)(再担保）')
            },
            validateRiskRateOther (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的分险比例（其他）')
            },
            validateRiskRateNation (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的分险比例（国担）')
            },
            validateRiskRateTechInfo (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的分险比例（工信部）')
            },
            validateRegCnt (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的第N次备案')
            },
            validateCreateTime (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的创建时间')
            },
            validateUpdateTime (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的修改时间')
            },
            validateCreator (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的创建人')
            },
            validateUpdator (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的修改人')
            },
            validateDelFlag (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的删除标记')
            },
            validateRemark (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的备注')
            },

        }
    }
</script>
