<template>
  <a-card :bordered="false">
    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">
<#assign query_field_no=0>
<#assign query_field_select=false>
<#assign query_field_date=false>
<#assign list_need_dict=false>
<#assign list_need_category=false>
<#assign query_field_pca=false>
<#assign list_need_pca=false>
<#assign query_flag=false>
<#assign query_inp=false>
<#assign query_popup=false>
<#assign query_sel_user=false>
<#assign query_sel_dep=false>
<#assign query_sel_multi=false>
<#assign query_sel_cat=false>
<#assign query_sel_search=false>
<#assign query_switch=false>
<#assign list_need_switch=false>
<#assign bpm_flag=false>

<#-- 开始循环 -->
<#list columns as po>
<#if po.fieldDbName=='bpm_status'>
  <#assign bpm_flag=true>
</#if>
<#if po.isQuery=='Y'>
<#assign query_flag=true>
	<#if query_field_no==2>
          <template v-if="toggleSearchStatus">
	</#if>
	<#assign query_field_dictCode="">
	<#if po.dictTable?default("")?trim?length gt 1>
	    <#assign query_field_dictCode="${po.dictTable},${po.dictText},${po.dictField}">
    <#elseif po.dictField?default("")?trim?length gt 1>
        <#assign query_field_dictCode="${po.dictField}">
    </#if>
	<#if po.queryMode=='single'>
          <#if query_field_no gt 1>  </#if><a-col :xl="6" :lg="7" :md="8" :sm="24">
            <#if query_field_no gt 1>  </#if><a-form-item label="${po.filedComment}">
            <#if po.classType=='sel_search'>
<#assign query_sel_search=true>
              <#if query_field_no gt 1>  </#if><j-search-select-tag placeholder="请选择${po.filedComment}" v-model="queryParam.${po.fieldName}" dict="${po.dictTable},${po.dictText},${po.dictField}"/>
            <#elseif po.classType=='sel_user'>
<#assign query_sel_user=true>
              <#if query_field_no gt 1>  </#if><j-select-user-by-dep placeholder="请选择${po.filedComment}" v-model="queryParam.${po.fieldName}"/>
            <#elseif po.classType=='switch'>
<#assign query_switch=true>
              <#if query_field_no gt 1>  </#if><j-switch placeholder="请选择${po.filedComment}" v-model="queryParam.${po.fieldName}" <#if po.dictField?default("")?trim?length gt 1>:options="${po.dictField}"</#if> query></j-switch>
            <#elseif po.classType=='sel_depart'>
<#assign query_sel_dep=true>
              <#if query_field_no gt 1>  </#if><j-select-depart placeholder="请选择${po.filedComment}" v-model="queryParam.${po.fieldName}"/>
            <#elseif po.classType=='list_multi'>
<#assign query_sel_multi=true>
              <#if query_field_no gt 1>  </#if><j-multi-select-tag placeholder="请选择${po.filedComment}" dictCode="${query_field_dictCode?default("")}" v-model="queryParam.${po.fieldName}"/>
            <#elseif po.classType=='cat_tree'>
<#assign query_sel_cat=true>
              <#if query_field_no gt 1>  </#if><j-category-select placeholder="请选择${po.filedComment}" v-model="queryParam.${po.fieldName}" pcode="${po.dictField?default("")}"/>
			<#elseif po.classType=='date'>
<#assign query_field_date=true>
              <#if query_field_no gt 1>  </#if><j-date placeholder="请选择${po.filedComment}" v-model="queryParam.${po.fieldName}"></j-date>
			<#elseif po.classType=='datetime'>
<#assign query_field_date=true>
              <#if query_field_no gt 1>  </#if><j-date :show-time="true" date-format="YYYY-MM-DD HH:mm:ss" placeholder="请选择${po.filedComment}" v-model="queryParam.${po.fieldName}"></j-date>
            <#elseif po.classType=='pca'>
<#assign query_field_pca=true>
              <#if query_field_no gt 1>  </#if><j-area-linkage type="cascader" v-model="queryParam.${po.fieldName}" placeholder="请选择省市区"/>
            <#elseif po.classType=='popup'>
              <#if query_field_no gt 1>  </#if><j-popup placeholder="请选择${po.filedComment}" v-model="queryParam.${po.fieldName}" code="${po.dictTable}" org-fields="${po.dictField}" dest-fields="${po.dictText}" :field="getPopupField('${po.dictText}')"/>
			<#elseif po.classType=='list' || po.classType=='radio' || po.classType=='checkbox'>
<#assign query_field_select=true>
			<#--  ---------------------------下拉或是单选 判断数据字典是表字典还是普通字典------------------------------- -->
			<#if po.dictTable?default("")?trim?length gt 1>
              <#if query_field_no gt 1>  </#if><j-dict-select-tag placeholder="请选择${po.filedComment}" v-model="queryParam.${po.fieldName}" dictCode="${po.dictTable},${po.dictText},${po.dictField}"/>
			<#elseif po.dictField?default("")?trim?length gt 1>
              <#if query_field_no gt 1>  </#if><j-dict-select-tag placeholder="请选择${po.filedComment}" v-model="queryParam.${po.fieldName}" dictCode="${po.dictField}"/>
			<#else>
              <#if query_field_no gt 1>  </#if><a-input placeholder="请输入${po.filedComment}" v-model="queryParam.${po.fieldName}"></a-input>
			</#if>
			<#else>
              <#if query_field_no gt 1>  </#if><a-input placeholder="请输入${po.filedComment}" v-model="queryParam.${po.fieldName}"></a-input>
			</#if>
            <#if query_field_no gt 1>  </#if></a-form-item>
          <#if query_field_no gt 1>  </#if></a-col>
	<#else>
          <#if query_field_no gt 1>  </#if><a-col :xl="10" :lg="11" :md="12" :sm="24">
            <#if query_field_no gt 1>  </#if><a-form-item label="${po.filedComment}">
			<#if po.classType=='date'>
<#assign query_field_date=true>
              <#if query_field_no gt 1>  </#if><j-date placeholder="请选择开始日期" class="query-group-cust" v-model="queryParam.${po.fieldName}_begin"></j-date>
              <#if query_field_no gt 1>  </#if><span class="query-group-split-cust"></span>
              <#if query_field_no gt 1>  </#if><j-date placeholder="请选择结束日期" class="query-group-cust" v-model="queryParam.${po.fieldName}_end"></j-date>
			<#elseif po.classType=='datetime'>
<#assign query_field_date=true>
              <#if query_field_no gt 1>  </#if><j-date :show-time="true" date-format="YYYY-MM-DD HH:mm:ss" placeholder="请选择开始时间" class="query-group-cust" v-model="queryParam.${po.fieldName}_begin"></j-date>
              <#if query_field_no gt 1>  </#if><span class="query-group-split-cust"></span>
              <#if query_field_no gt 1>  </#if><j-date :show-time="true" date-format="YYYY-MM-DD HH:mm:ss" placeholder="请选择结束时间" class="query-group-cust" v-model="queryParam.${po.fieldName}_end"></j-date>
			<#else>
              <#if query_field_no gt 1>  </#if><a-input placeholder="请输入最小值" class="query-group-cust" v-model="queryParam.${po.fieldName}_begin"></a-input>
              <#if query_field_no gt 1>  </#if><span class="query-group-split-cust"></span>
              <#if query_field_no gt 1>  </#if><a-input placeholder="请输入最大值" class="query-group-cust" v-model="queryParam.${po.fieldName}_end"></a-input>
			</#if>
            <#if query_field_no gt 1>  </#if></a-form-item>
          <#if query_field_no gt 1>  </#if></a-col>
	</#if>
<#assign query_field_no=query_field_no+1>
</#if>
<#if !list_need_dict && po.fieldShowType!='popup' && po.dictField?default("")?trim?length gt 1>
<#assign list_need_dict=true>
</#if>
<#if po.classType=='cat_tree' && po.dictText?default("")?trim?length == 0>
<#assign list_need_category=true>
</#if>
<#if po.classType=='pca'>
<#assign list_need_pca=true>
</#if>
<#if po.classType=='switch'>
<#assign list_need_switch=true>
</#if>
</#list>
<#-- 结束循环 -->
<#t>
<#if query_field_no gt 2>
          </template>
</#if>
<#if query_flag>
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <span style="float: left;overflow: hidden;" class="table-page-search-submitButtons">
              <a-button type="primary" @click="searchQuery" icon="search">查询</a-button>
              <a-button type="primary" @click="searchReset" icon="reload" style="margin-left: 8px">重置</a-button>
              <a @click="handleToggleSearch" style="margin-left: 8px">
                {{ toggleSearchStatus ? '收起' : '展开' }}
                <a-icon :type="toggleSearchStatus ? 'up' : 'down'"/>
              </a>
            </span>
          </a-col>
</#if>
        </a-row>
      </a-form>
    </div>
    <!-- 查询区域-END -->

    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>
      <a-button type="primary" icon="download" @click="handleExportXls('${tableVo.ftlDescription}')">导出</a-button>
      <a-upload name="file" :showUploadList="false" :multiple="false" :headers="tokenHeader" :action="importExcelUrl" @change="handleImportExcel">
        <a-button type="primary" icon="import">导入</a-button>
      </a-upload>
      <!-- 高级查询区域 -->
      <j-super-query :fieldList="superFieldList" ref="superQueryModal" @handleSuperQuery="handleSuperQuery"></j-super-query>
      <a-dropdown v-if="selectedRowKeys.length > 0">
        <a-menu slot="overlay">
          <a-menu-item key="1" @click="batchDel"><a-icon type="delete"/>删除</a-menu-item>
        </a-menu>
        <a-button style="margin-left: 8px"> 批量操作 <a-icon type="down" /></a-button>
      </a-dropdown>
    </div>

    <!-- table区域-begin -->
    <div>
      <div class="ant-alert ant-alert-info" style="margin-bottom: 16px;">
        <i class="anticon anticon-info-circle ant-alert-icon"></i> 已选择 <a style="font-weight: 600">{{ selectedRowKeys.length }}</a>项
        <a style="margin-left: 24px" @click="onClearSelected">清空</a>
      </div>

      <a-table
        ref="table"
        size="middle"
        <#if tableVo.extendParams.scroll=='1'>
        :scroll="{x:true}"
        </#if>
        bordered
        rowKey="id"
        :columns="columns"
        :dataSource="dataSource"
        :pagination="ipagination"
        :loading="loading"
        :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange}"
        class="j-table-force-nowrap"
        @change="handleTableChange">

        <template slot="htmlSlot" slot-scope="text">
          <div v-html="text"></div>
        </template>
        <template slot="imgSlot" slot-scope="text">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无图片</span>
          <img v-else :src="getImgView(text)" height="25px" alt="" style="max-width:80px;font-size: 12px;font-style: italic;"/>
        </template>
        <#if list_need_pca>
        <template slot="pcaSlot" slot-scope="text">
          <div>{{ getPcaText(text) }}</div>
        </template>
        </#if>
        <template slot="fileSlot" slot-scope="text">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无文件</span>
          <a-button
            v-else
            :ghost="true"
            type="primary"
            icon="download"
            size="small"
            @click="downloadFile(text)">
            下载
          </a-button>
        </template>

        <span slot="action" slot-scope="text, record">
          <a @click="handleEdit(record)">编辑</a>

          <a-divider type="vertical" />
          <a-dropdown>
            <a class="ant-dropdown-link">更多 <a-icon type="down" /></a>
            <a-menu slot="overlay">
              <a-menu-item>
                <a @click="handleDetail(record)">详情</a>
              </a-menu-item>
              <#if bpm_flag>
              <a-menu-item v-if="record.bpmStatus === '1'">
                <a @click="startProcess(record)">发起流程</a>
              </a-menu-item>
              </#if>
              <a-menu-item>
                <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">
                  <a>删除</a>
                </a-popconfirm>
              </a-menu-item>
            </a-menu>
          </a-dropdown>
        </span>

      </a-table>
    </div>

    <${Format.humpToShortbar(entityName)}-modal ref="modalForm" @ok="modalFormOk"></${Format.humpToShortbar(entityName)}-modal>
  </a-card>
</template>

<script>

  import '@/assets/less/TableExpand.less'
  import { mixinDevice } from '@/utils/mixin'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import ${entityName}Modal from './modules/${entityName}Modal'
  <#if bpm_flag>
  import { postAction } from '@/api/manage'
  </#if>
  <#if query_field_select>
  import JDictSelectTag from '@/components/dict/JDictSelectTag.vue'
  </#if>
  <#if query_field_date>
  import JDate from '@/components/jeecg/JDate.vue'
  </#if>
  <#if list_need_category>
  import { loadCategoryData } from '@/api/api'
  </#if>
  <#if list_need_dict>
  import {filterMultiDictText} from '@/components/dict/JDictSelectUtil'
  </#if>
  <#if query_field_pca>
  import JAreaLinkage from '@comp/jeecg/JAreaLinkage'
  </#if>
  <#if list_need_pca>
  import Area from '@/components/_util/Area'
  </#if>
  <#if query_inp>
  import JInput from '@comp/jeecg/JInput'
  </#if>
  <#if query_sel_user>
  import JSelectUserByDep from '@/components/jeecgbiz/JSelectUserByDep'
  </#if>
  <#if query_sel_dep>
  import JSelectDepart from '@/components/jeecgbiz/JSelectDepart'
  </#if>
  <#if query_sel_multi>
  import JMultiSelectTag from '@/components/dict/JMultiSelectTag'
  </#if>
  <#if query_sel_cat>
  import JCategorySelect from '@comp/jeecg/JCategorySelect'
  </#if>
  <#if query_sel_search>
  import JSearchSelectTag from '@/components/dict/JSearchSelectTag'
  </#if>
  <#if query_switch>
  import JSwitch from '@/components/jeecg/JSwitch'
  </#if>
  import JSuperQuery from '@/components/jeecg/JSuperQuery.vue'

  export default {
    name: '${entityName}List',
    mixins:[JeecgListMixin, mixinDevice],
    components: {
      <#if query_field_select>
      JDictSelectTag,
      </#if>
      <#if query_field_date>
      JDate,
      </#if>
      <#if query_field_pca>
      JAreaLinkage,
      </#if>
      <#if query_inp>
      JInput,
      </#if>
      <#if query_sel_user>
      JSelectUserByDep,
      </#if>
      <#if query_sel_dep>
      JSelectDepart,
      </#if>
      <#if query_sel_multi>
      JMultiSelectTag,
      </#if>
      <#if query_sel_cat>
      JCategorySelect,
      </#if>
      <#if query_sel_search>
      JSearchSelectTag,
      </#if>
      <#if query_switch>
      JSwitch,
      </#if>
      ${entityName}Modal,
      JSuperQuery,
    },
    data () {
      return {
        description: '${tableVo.ftlDescription}管理页面',
        // 表头
        columns: [
          {
            title: '#',
            dataIndex: '',
            key:'rowIndex',
            width:60,
            align:"center",
            customRender:function (t,r,index) {
              return parseInt(index)+1;
            }
          },
    <#assign showColNum=0>
	<#list columns as po>
	<#if po.isShowList =='Y' && po.fieldName !='id'>
	<#assign showColNum=showColNum+1>
          {
            title:'${po.filedComment}',
            align:"center",
            <#if po.sort=='Y'>
            sorter: true,
            </#if>
            <#if po.classType=='date'>
            dataIndex: '${po.fieldName}',
            customRender:function (text) {
              return !text?"":(text.length>10?text.substr(0,10):text)
            }
            <#elseif po.fieldDbType=='Blob'>
            dataIndex: '${po.fieldName}String'
            <#elseif po.classType=='umeditor'>
            dataIndex: '${po.fieldName}',
            scopedSlots: {customRender: 'htmlSlot'}
            <#elseif po.classType=='pca'>
            dataIndex: '${po.fieldName}',
            scopedSlots: {customRender: 'pcaSlot'}
            <#elseif po.classType=='file'>
            dataIndex: '${po.fieldName}',
            scopedSlots: {customRender: 'fileSlot'}
            <#elseif po.classType=='image'>
            dataIndex: '${po.fieldName}',
            scopedSlots: {customRender: 'imgSlot'}
            <#elseif po.classType=='switch'>
            dataIndex: '${po.fieldName}',
            customRender: (text) => (text ? filterMultiDictText(this.dictOptions['${po.fieldName}'], text) : ''),
            <#elseif po.classType=='list' || po.classType=='list_multi' || po.classType=='sel_search' || po.classType=='radio' || po.classType=='checkbox' || po.classType=='sel_depart' || po.classType=='sel_user'>
            dataIndex: '${po.fieldName}_dictText'
            <#elseif po.classType=='cat_tree'>
            <#if list_need_category>
            dataIndex: '${po.fieldName}',
            customRender: (text) => (text ? filterMultiDictText(this.dictOptions['${po.fieldName}'], text) : '')
            <#else>
            dataIndex: '${po.fieldName}',
            customRender: (text, record) => (text ? record['${po.dictText}'] : '')
            </#if>
			<#else>
            dataIndex: '${po.fieldName}'
			</#if>
          },
     </#if>
     </#list>
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            <#if tableVo.extendParams.scroll=='1'>
            fixed:"right",
            width:147,
            </#if>
            scopedSlots: { customRender: 'action' }
          }
        ],
        url: {
          list: "/${entityPackage}/${entityName?uncap_first}/list",
          delete: "/${entityPackage}/${entityName?uncap_first}/delete",
          deleteBatch: "/${entityPackage}/${entityName?uncap_first}/deleteBatch",
          exportXlsUrl: "/${entityPackage}/${entityName?uncap_first}/exportXls",
          importExcelUrl: "${entityPackage}/${entityName?uncap_first}/importExcel",
          <#if bpm_flag>startProcess: '/act/process/extActProcess/startMutilProcess'</#if>
        },
        <#if bpm_flag>
        //代码生成后需手动设置流程编码
        flowCode: 'dev_${tableName}_001',
        </#if>
        dictOptions:{},
        <#if list_need_pca>
        pcaData:'',
        </#if>
        superFieldList:[],
      }
    },
    created() {
    <#if list_need_pca>
      this.pcaData = new Area()
    </#if>
    <#if list_need_switch>
    <#list columns as po>
    <#if po.classType=='switch'>
      <#if po.dictField?default("")?trim?length gt 1>
      <#assign switch_extend_arr=po.dictField?eval>
      <#else>
       <#assign switch_extend_arr=['Y','N']>
      </#if>
      <#list switch_extend_arr as a>
       <#if a_index == 0>
       <#assign switch_extend_arr1=a>
       <#else>
       <#assign switch_extend_arr2=a>
       </#if>
      </#list>
      this.$set(this.dictOptions, '${po.fieldName}', [{text:'是',value:'${switch_extend_arr1}'},{text:'否',value:'${switch_extend_arr2}'}])
    </#if>
    </#list>
    </#if>
    this.getSuperFieldList();
    },
    computed: {
      importExcelUrl: function(){
        <#noparse>return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;</#noparse>
      },
    },
    methods: {
    <#if list_need_pca>
      getPcaText(code){
        return this.pcaData.getText(code);
      },
    </#if>
      <#if bpm_flag>
      startProcess(record){
        this.$confirm({
          title:'提示',
          content:'确认提交流程吗?',
          onOk:()=>{
            let params = {
              flowCode: this.flowCode,
              id: record.id,
              formUrl: '${entityPackage}/modules/${entityName}Form',
              formUrlMobile: ''
            }
            postAction(this.url.startProcess, params).then(res=>{
              if(res.success){
                this.$message.success(res.message);
                this.loadData();
                this.onClearSelected();
              }else{
                this.$message.warning(res.message);
              }
            }).catch((e)=>{
              this.$message.warning('不识别的请求!');
            })
          }
        })
      },
      </#if>
      initDictConfig(){
      <#list columns as po>
      <#if (po.isQuery=='Y' || po.isShowList=='Y') && po.classType!='popup'>
        <#if po.classType=='cat_tree' && list_need_category==true>
        loadCategoryData({code:'${po.dictField?default("")}'}).then((res) => {
          if (res.success) {
            this.$set(this.dictOptions, '${po.fieldName}', res.result)
          }
        })
        </#if>
      </#if>
      </#list>
      },
      getSuperFieldList(){
        <#include "/common/utils.ftl">
        let fieldList=[];
         <#list columns as po>
        fieldList.push(${superQueryFieldList(po)})
         </#list>
        this.superFieldList = fieldList
      }
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less';
</style>