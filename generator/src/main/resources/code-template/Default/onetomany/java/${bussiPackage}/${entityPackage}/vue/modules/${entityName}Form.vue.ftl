<#include "/common/utils.ftl">
<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <!-- 主表单区域 -->
      <a-form :form="form" slot="detail">
        <a-row>
<#assign form_date = false>
<#assign form_select = false>
<#assign form_select_multi = false>
<#assign form_select_search = false>
<#assign form_popup = false>
<#assign form_sel_depart = false>
<#assign form_sel_user = false>
<#assign form_file = false>
<#assign form_image = false>
<#assign form_editor = false>
<#assign form_cat_tree = false>
<#assign form_cat_back = "">
<#assign form_pca = false>
<#assign form_md = false>
<#assign form_switch=false>
<#assign form_span = 24>
<#if tableVo.fieldRowNum==2>
    <#assign form_span = 12>
<#elseif tableVo.fieldRowNum==3>
    <#assign form_span = 8>
<#elseif tableVo.fieldRowNum==4>
    <#assign form_span = 6>
</#if>
<#list columns as po>
<#if po.isShow =='Y' && po.fieldName != 'id'>
<#assign form_field_dictCode="">
	<#if po.dictTable?default("")?trim?length gt 1>
		<#assign form_field_dictCode="${po.dictTable},${po.dictText},${po.dictField}">
	<#elseif po.dictField?default("")?trim?length gt 1>
		<#assign form_field_dictCode="${po.dictField}">
	</#if>
	<#if po.classType =='textarea'>
          <a-col :span="24">
            <a-form-item label="${po.filedComment}" :labelCol="labelCol2" :wrapperCol="wrapperCol2">
	<#else>
          <a-col :span="${form_span}" >
            <a-form-item label="${po.filedComment}" :labelCol="labelCol" :wrapperCol="wrapperCol">
	</#if>
	<#if po.classType =='date'>
		<#assign form_date=true>
              <j-date placeholder="请选择${po.filedComment}" v-decorator="['${po.fieldName}'${autoWriteRules(po)}]" :trigger-change="true" style="width: 100%" <#if po.readonly=='Y'>disabled</#if>/>
	<#elseif po.classType =='datetime'>
		<#assign form_date=true>
              <j-date placeholder="请选择${po.filedComment}" v-decorator="['${po.fieldName}'${autoWriteRules(po)}]" :trigger-change="true" :show-time="true" date-format="YYYY-MM-DD HH:mm:ss" style="width: 100%" <#if po.readonly=='Y'>disabled</#if>/>
	<#elseif po.classType =='popup'>
		<#assign form_popup=true>
              <j-popup
                v-decorator="['${po.fieldName}'${autoWriteRules(po)}]"
                :trigger-change="true"
                org-fields="${po.dictField}"
                dest-fields="${Format.underlineToHump(po.dictText)}"
                code="${po.dictTable}"
                @callback="popupCallback"
                <#if po.readonly=='Y'>disabled</#if>/>
    <#elseif po.classType =='sel_depart'>
		<#assign form_sel_depart=true>
              <j-select-depart v-decorator="['${po.fieldName}'${autoWriteRules(po)}]" multi <#if po.readonly=='Y'>disabled</#if>/>
<#elseif po.classType =='switch'>
        <#assign form_switch=true>
              <j-switch v-decorator="['${po.fieldName}'${autoWriteRules(po)}]" <#if po.dictField?default("")?trim?length gt 1>:options="${po.dictField}"</#if> <#if po.readonly=='Y'>disabled</#if>></j-switch>
	<#elseif po.classType =='pca'>
		<#assign form_pca=true>
		      <j-area-linkage type="cascader" v-decorator="['${po.fieldName}'${autoWriteRules(po)}]" placeholder="请输入省市区" <#if po.readonly=='Y'>disabled</#if>/>
	<#elseif po.classType =='markdown'>
	    <#assign form_md=true>
	          <j-markdown-editor v-decorator="['${po.fieldName}']" id="${po.fieldName}"></j-markdown-editor>
    <#elseif po.classType =='password'>
	          <a-input-password v-decorator="['${po.fieldName}'${autoWriteRules(po)}]" placeholder="请输入${po.filedComment}" <#if po.readonly=='Y'>disabled</#if>/>
	<#elseif po.classType =='sel_user'>
		<#assign form_sel_user = true>
              <j-select-user-by-dep v-decorator="['${po.fieldName}'${autoWriteRules(po)}]" <#if po.readonly=='Y'>disabled</#if>/>
	<#elseif po.classType =='textarea'>
              <a-textarea v-decorator="['${po.fieldName}'${autoWriteRules(po)}]" rows="4" placeholder="请输入${po.filedComment}" <#if po.readonly=='Y'>disabled</#if>/>
	<#elseif po.classType=='list' || po.classType=='radio'>
		<#assign form_select = true>
              <j-dict-select-tag type="${po.classType}" v-decorator="['${po.fieldName}'${autoWriteRules(po)}]" :trigger-change="true" dictCode="${form_field_dictCode}" placeholder="请选择${po.filedComment}" <#if po.readonly=='Y'>disabled</#if>/>
	<#elseif po.classType=='list_multi' || po.classType=='checkbox'>
		<#assign form_select_multi = true>
              <j-multi-select-tag type="${po.classType}" v-decorator="['${po.fieldName}'${autoWriteRules(po)}]" :trigger-change="true" dictCode="${form_field_dictCode}" placeholder="请选择${po.filedComment}" <#if po.readonly=='Y'>disabled</#if>/>
	<#elseif po.classType=='sel_search'>
    	<#assign form_select_search = true>
              <j-search-select-tag v-decorator="['${po.fieldName}'${autoWriteRules(po)}]" dict="${form_field_dictCode}" <#if po.readonly=='Y'>disabled</#if> />
    <#elseif po.classType=='cat_tree'>
    	<#assign form_cat_tree = true>
              <j-category-select v-decorator="['${po.fieldName}'${autoWriteRules(po)}]" pcode="${po.dictField?default("")}" placeholder="请选择${po.filedComment}" <#if po.dictText?default("")?trim?length gt 1>back="${po.dictText}" @change="handleCategoryChange"</#if> <#if po.readonly=='Y'>disabled</#if>/>
    	<#if po.dictText?default("")?trim?length gt 1>
    	<#assign form_cat_back = "${po.dictText}">
    	</#if>
	<#elseif po.fieldDbType=='int' || po.fieldDbType=='double' || po.fieldDbType=='BigDecimal'>
              <a-input-number v-decorator="['${po.fieldName}'${autoWriteRules(po)}]" placeholder="请输入${po.filedComment}" style="width: 100%" <#if po.readonly=='Y'>disabled</#if>/>
	<#elseif po.classType=='file'>
		<#assign form_file = true>
              <j-upload v-decorator="['${po.fieldName}'${autoWriteRules(po)}]" :trigger-change="true" <#if po.readonly=='Y'>disabled</#if> <#if po.uploadnum??>:number=${po.uploadnum}</#if>></j-upload>
	<#elseif po.classType=='image'>
	    <#assign form_image = true>
              <j-image-upload isMultiple <#if po.uploadnum??>:number=${po.uploadnum}</#if> v-decorator="['${po.fieldName}'${autoWriteRules(po)}]" <#if po.readonly=='Y'>disabled</#if>></j-image-upload>
	<#elseif po.classType=='umeditor'>
        <#assign form_editor = true>
              <j-editor v-decorator="['${po.fieldName}',{trigger:'input'}]" <#if po.readonly=='Y'>disabled</#if>/>
    <#elseif po.fieldDbType=='Blob'>
              <a-input v-decorator="['${po.fieldName}String'${autoWriteRules(po)}]" placeholder="请输入${po.filedComment}" <#if po.readonly=='Y'>disabled</#if>></a-input>
	<#else>
              <a-input v-decorator="['${po.fieldName}'${autoWriteRules(po)}]" placeholder="请输入${po.filedComment}" <#if po.readonly=='Y'>disabled</#if>></a-input>
    </#if>
            </a-form-item>
            <#if form_cat_tree && form_cat_back?length gt 1>
            <a-form-item v-show="false">
              <a-input v-decorator="['${form_cat_back}']"></a-input>
            </a-form-item>
            </#if>
          </a-col>
</#if>
</#list>
        </a-row>
      </a-form>
    </j-form-container>
      <!-- 子表单区域 -->
    <a-tabs v-model="activeKey" @change="handleChangeTabs">
<#list subTables as sub><#rt/>
  <#if sub.foreignRelationType =='1'>
      <a-tab-pane tab="${sub.ftlDescription}" :key="refKeys[${sub_index}]" :forceRender="true">
        <${Format.humpToShortbar(sub.entityName)}-form ref="${sub.entityName?uncap_first}Form" @validateError="validateError" :disabled="formDisabled"></${Format.humpToShortbar(sub.entityName)}-form>
      </a-tab-pane>
      
  <#else>
      <a-tab-pane tab="${sub.ftlDescription}" :key="refKeys[${sub_index}]" :forceRender="true">
        <j-editable-table
          :ref="refKeys[${sub_index}]"
          :loading="${sub.entityName?uncap_first}Table.loading"
          :columns="${sub.entityName?uncap_first}Table.columns"
          :dataSource="${sub.entityName?uncap_first}Table.dataSource"
          :maxHeight="300"
          :disabled="formDisabled"
          :rowNumber="true"
          :rowSelection="true"
          :actionButton="true"/>
      </a-tab-pane>
  </#if>
</#list>
    </a-tabs>
  </a-spin>
</template>

<script>

  import pick from 'lodash.pick'
  import { getAction } from '@/api/manage'
  import { FormTypes,getRefPromise } from '@/utils/JEditableTableUtil'
  import { JEditableTableMixin } from '@/mixins/JEditableTableMixin'
  import { validateDuplicateValue } from '@/utils/util'
  import JFormContainer from '@/components/jeecg/JFormContainer'
  <#list subTables as sub>
  <#if sub.foreignRelationType =='1'>
  import ${sub.entityName}Form from './${sub.entityName}Form.vue'
  </#if>
  </#list>
  <#if form_date>
  import JDate from '@/components/jeecg/JDate'  
  </#if>
  <#if form_file>
  import JUpload from '@/components/jeecg/JUpload'
  </#if>
  <#if form_image>
  import JImageUpload from '@/components/jeecg/JImageUpload'
  </#if>
  <#if form_sel_depart>
  import JSelectDepart from '@/components/jeecgbiz/JSelectDepart'
  </#if>
  <#if form_sel_user>
  import JSelectUserByDep from '@/components/jeecgbiz/JSelectUserByDep'
  </#if>
  <#if form_select>
  import JDictSelectTag from "@/components/dict/JDictSelectTag"
  </#if>
  <#if form_select_multi>
  import JMultiSelectTag from "@/components/dict/JMultiSelectTag"
  </#if>
  <#if form_select_search>
  import JSearchSelectTag from '@/components/dict/JSearchSelectTag'
  </#if>
  <#if form_editor>
  import JEditor from '@/components/jeecg/JEditor'
  </#if>
  <#if form_cat_tree>
  import JCategorySelect from '@/components/jeecg/JCategorySelect'
  </#if>
  <#if form_pca>
  import JAreaLinkage from '@comp/jeecg/JAreaLinkage'
  </#if>
  <#if form_md>
  import JMarkdownEditor from '@/components/jeecg/JMarkdownEditor/index'
  </#if>
  <#if form_switch==true >
  import JSwitch from '@/components/jeecg/JSwitch'
  </#if>

  export default {
    name: '${entityName}Form',
    mixins: [JEditableTableMixin],
    components: {
      JFormContainer,
    <#list subTables as sub>
    <#if sub.foreignRelationType =='1'>
      ${sub.entityName}Form,
    </#if>
    </#list>
    <#if form_date>
      JDate,
    </#if>
    <#if form_file>
      JUpload,
    </#if>
    <#if form_image>
      JImageUpload,
    </#if>
    <#if form_sel_depart>
      JSelectDepart,
    </#if>
    <#if form_sel_user>
      JSelectUserByDep,
    </#if>
    <#if form_select>
      JDictSelectTag,
    </#if>
    <#if form_select_multi>
      JMultiSelectTag,
    </#if>
    <#if form_select_search>
      JSearchSelectTag,
    </#if>
    <#if form_editor>
      JEditor,
    </#if>
    <#if form_pca>
      JAreaLinkage,
    </#if>
    <#if form_cat_tree>
      JCategorySelect,
    </#if>
    <#if form_md>
      JMarkdownEditor,
    </#if>
    <#if form_switch==true >
      JSwitch,
    </#if>
    },
    data() {
      return {
        labelCol: {
          xs: { span: 24 },
          sm: { span: 6 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },
        labelCol2: {
          xs: { span: 24 },
          sm: { span: 3 },
        },
        wrapperCol2: {
          xs: { span: 24 },
          sm: { span: 20 },
        },
        // 新增时子表默认添加几行空数据
        addDefaultRowNum: 1,
        <#include "/common/validatorRulesTemplate/main.ftl">
        refKeys: [<#list subTables as sub>'${sub.entityName?uncap_first}', </#list>],
        <#assign hasOne2Many = false>
        tableKeys:[<#list subTables as sub><#if sub.foreignRelationType =='0'>'${sub.entityName?uncap_first}', <#assign hasOne2Many = true></#if></#list>],
        activeKey: '${subTables[0].entityName?uncap_first}',
<#list subTables as sub><#rt/>
        // ${sub.ftlDescription}
        ${sub.entityName?uncap_first}Table: {
          loading: false,
          dataSource: [],
          columns: [
<#if sub.foreignRelationType =='0'>
<#assign popupBackFields = "">

<#-- 循环子表的列 开始 -->
<#list sub.colums as col><#rt/>
<#if col.isShow =='Y'>
    <#if col.filedComment !='外键' >
            {
              title: '${col.filedComment}',
              key: '${col.fieldName}',
      <#if col.classType =='date'>
              type: FormTypes.date,
              <#if col.readonly=='Y'>
              disabled:true,
              </#if>
      <#elseif col.classType =='datetime'>
              type: FormTypes.datetime,
              <#if col.readonly=='Y'>
              disabled:true,
              </#if>
      <#elseif "int,decimal,double,"?contains(col.classType)>
              type: FormTypes.inputNumber,
              <#if col.readonly=='Y'>
              disabled:true,
              </#if>
      <#elseif col.classType =='list' || col.classType =='radio'>
              type: FormTypes.select,
              <#if col.dictTable?default("")?trim?length gt 1>
              dictCode:"${col.dictTable},${col.dictText},${col.dictField}",
              <#else>
              dictCode:"${col.dictField}",
              </#if>
              <#if col.readonly=='Y'>
              disabled:true,
              </#if>
      <#elseif col.classType =='list_multi' || col.classType =='checkbox'>
              type: FormTypes.list_multi,
              <#if col.dictTable?default("")?trim?length gt 1>
              dictCode:"${col.dictTable},${col.dictText},${col.dictField}",
              <#else>
              dictCode:"${col.dictField}",
              </#if>
              <#if col.readonly=='Y'>
              disabled:true,
              </#if>
      <#elseif col.classType =='switch'>
              type: FormTypes.checkbox,
              <#if col.dictField?default("")?trim?length gt 1>
              customValue:${col.dictField},
              <#else>
              customValue: ['Y', 'N'],
              </#if>
              <#if col.readonly=='Y'>
              disabled:true,
              </#if>
      <#elseif col.classType =='sel_search'>
              type: FormTypes.sel_search,
              <#if col.dictTable?default("")?trim?length gt 1>
              dictCode:"${col.dictTable},${col.dictText},${col.dictField}",
              <#else>
              dictCode:"${col.dictField}",
              </#if>
              <#if col.readonly=='Y'>
              disabled:true,
              </#if>
      <#elseif col.classType =='image'>
              type: FormTypes.image,
              token:true,
              responseName:"message",
              <#if col.readonly=='Y'>
              disabled:true,
              </#if>
              <#if col.uploadnum??>
              number: ${col.uploadnum},
              </#if>
      <#elseif col.classType =='file'>
              type: FormTypes.file,
              token:true,
              responseName:"message",
              <#if col.readonly=='Y'>
              disabled:true,
              </#if>
              <#if col.uploadnum??>
              number: ${col.uploadnum},
              </#if>
      <#elseif col.classType =='popup'>
        <#if popupBackFields?length gt 0>
            <#assign popupBackFields = "${popupBackFields}"+","+"${col.dictText}">
        <#else>
            <#assign popupBackFields = "${col.dictText}">
        </#if>
              type: FormTypes.popup,
              popupCode:"${col.dictTable}",
              destFields:"${Format.underlineToHump(col.dictText)}",
              orgFields:"${col.dictField}",
              <#if col.readonly=='Y'>
              disabled:true,
              </#if>
      <#elseif col.fieldDbType=='int' || col.fieldDbType=='double' || col.fieldDbType=='BigDecimal'>
              type: FormTypes.inputNumber,
              <#if col.readonly=='Y'>
              disabled:true,
              </#if>
      <#else>
              type: FormTypes.input,
              <#if col.readonly=='Y'>
              disabled:true,
              </#if>
      </#if>
      <#if col.classType =='list_multi' || col.classType =='checkbox'>
              width:"250px",
      <#else>
              width:"200px",
      </#if>
      <#if col.classType =='file'>
              placeholder: '请选择文件',
      <#else>
              placeholder: '请输入${'$'}{title}',
      </#if>
      <#if col.defaultVal??>
        <#if col.fieldDbType=="BigDecimal" || col.fieldDbType=="double" || col.fieldDbType=="int">
              defaultValue:${col.defaultVal},
              <#else>
              defaultValue:"${col.defaultVal}",
        </#if>
      <#else>
              defaultValue:'',
      </#if>
      <#-- 子表的校验 -->
      <#assign subFieldValidType = col.fieldValidType!''>
      <#-- 非空校验 -->
      <#if col.nullable == 'N' || subFieldValidType == '*'>
              validateRules: [{ required: true, message: '${'$'}{title}不能为空' }],
      <#-- 其他情况下，只要有值就被认为是正则校验 -->
      <#elseif subFieldValidType?length gt 0>
        <#assign subMessage = '格式不正确'>
        <#if subFieldValidType == 'only' >
          <#assign subMessage = '不能重复'>
        </#if>
              validateRules: [{ pattern: "${subFieldValidType}", message: "${'$'}{title}${subMessage}" }],
      </#if>
            },
    </#if>
</#if>
</#list>
<#-- 循环子表的列 结束 -->

<#-- 处理popup的隐藏列 -->
<#if popupBackFields?length gt 0>
<#list popupBackFields?split(",") as item>
<#if item?length gt 0>
<#assign tempItemFlag = true>

<#list sub.colums as col>
<#if col.isShow =='Y' && col.fieldName == item>
<#assign tempItemFlag = false>
</#if>
</#list>
<#if tempItemFlag>
            {
              title: '${item}',
              key: '${item}',
              type:"hidden"
            },
</#if>
</#if>
</#list>
</#if>
</#if>
          ]
        },
</#list>
        url: {
          add: "/${entityPackage}/${entityName?uncap_first}/add",
          edit: "/${entityPackage}/${entityName?uncap_first}/edit",
          queryById: "/${entityPackage}/${entityName?uncap_first}/queryById",
<#list subTables as sub><#rt/>
          ${sub.entityName?uncap_first}: {
            list: '/${entityPackage}/${entityName?uncap_first}/query${sub.entityName}ByMainId'
          },
</#list>
        }
      }
    },
    props: {
      //流程表单data
      formData: {
        type: Object,
        default: ()=>{},
        required: false
      },
      //表单模式：false流程表单 true普通表单
      formBpm: {
        type: Boolean,
        default: false,
        required: false
      },
      //表单禁用
      disabled: {
        type: Boolean,
        default: false,
        required: false
      }
    },
    computed: {
      formDisabled(){
        if(this.formBpm===true){
          if(this.formData.disabled===false){
            return false
          }
          return true
        }
        return this.disabled
      },
      showFlowSubmitButton(){
        if(this.formBpm===true){
          if(this.formData.disabled===false){
            return true
          }
        }
        return false
      }
    },
    created () {
      //如果是流程中表单，则需要加载流程表单data
      this.showFlowData();
    },
    methods: {
      addBefore(){
        this.form.resetFields()
<#list subTables as sub><#rt/>
  <#if sub.foreignRelationType =='1'>
        this.$refs.${sub.entityName?uncap_first}Form.clearFormData()
  <#else>
        this.${sub.entityName?uncap_first}Table.dataSource=[]
  </#if>
</#list>
      },
      getAllTable() {
        <#if hasOne2Many==true>
        let values = this.tableKeys.map(key => getRefPromise(this, key))
        return Promise.all(values)
        <#else>
        return new Promise(resolve => {
          resolve([]);
        })
        </#if>
      },
      /** 调用完edit()方法之后会自动调用此方法 */
      editAfter() {
        let fieldval = pick(this.model<#list columns as po><#if po.fieldName !='id'><#if po.fieldDbType=='Blob'>,'${po.fieldName}String'<#else>,'${po.fieldName}'</#if></#if></#list>)
        this.$nextTick(() => {
          this.form.setFieldsValue(fieldval)
<#list subTables as sub><#rt/>
<#if sub.foreignRelationType =='1'>
          this.$refs.${sub.entityName?uncap_first}Form.initFormData(this.url.${sub.entityName?uncap_first}.list,this.model.id)
</#if>
</#list>         
        })
        // 加载子表数据
        if (this.model.id) {
          let params = { id: this.model.id }
<#list subTables as sub><#rt/>
<#if sub.foreignRelationType =='0'>
          this.requestSubTableData(this.url.${sub.entityName?uncap_first}.list, params, this.${sub.entityName?uncap_first}Table)
</#if>
</#list>
        }
      },
      /** 整理成formData */
      classifyIntoFormData(allValues) {
        let main = Object.assign(this.model, allValues.formValue)
        return {
          ...main, // 展开
<#assign subManyIndex = 0>
<#list subTables as sub><#rt/>
<#if sub.foreignRelationType =='0'>
          ${sub.entityName?uncap_first}List: allValues.tablesValue[${subManyIndex}].values,
          <#assign subManyIndex = subManyIndex+1>
<#else>
          ${sub.entityName?uncap_first}List: this.$refs.${sub.entityName?uncap_first}Form.getFormData(),
</#if>
</#list>
        }
      },
      //渲染流程表单数据
      showFlowData(){
        if(this.formBpm === true){
          let params = {id:this.formData.dataId};
          getAction(this.url.queryById,params).then((res)=>{
            if(res.success){
              this.edit (res.result);
            }
          })
        }
      },
      validateError(msg){
        this.$message.error(msg)
      },
     popupCallback(row){
       this.form.setFieldsValue(pick(row<#list columns as po><#if po.fieldName !='id'><#if po.fieldDbType=='Blob'>,'${po.fieldName}String'<#else>,'${po.fieldName}'</#if></#if></#list>))
     },
   <#if form_cat_tree>
     handleCategoryChange(value,backObj){
       this.form.setFieldsValue(backObj)
      }
   </#if>

    }
  }
</script>

<style scoped>
</style>