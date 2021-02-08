<#assign pidFieldName = "">
<#assign hasChildrenField = "">
<#list originalColumns as po>
<#if po.fieldDbName == tableVo.extendParams.pidField>
<#assign pidFieldName = po.fieldName>
</#if>
<#if po.fieldDbName == tableVo.extendParams.hasChildren>
<#assign hasChildrenField = po.fieldName>
</#if>
</#list>
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
        rowKey="id"
        :columns="columns"
        :dataSource="dataSource"
        :pagination="ipagination"
        :loading="loading"
        :expandedRowKeys="expandedRowKeys"
        @change="handleTableChange"
        @expand="handleExpand"
        v-bind="tableProps">

        <template slot="imgSlot" slot-scope="text">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无图片</span>
          <img v-else :src="getImgView(text)" height="25px" alt="" style="max-width:80px;font-size: 12px;font-style: italic;"/>
        </template>
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
                <a @click="handleAddChild(record)">添加下级</a>
              </a-menu-item>
              <a-menu-item>
                <a-popconfirm title="确定删除吗?" @confirm="() => handleDeleteNode(record.id)">
                  <a>删除</a>
                </a-popconfirm>
              </a-menu-item>
            </a-menu>
          </a-dropdown>
        </span>

      </a-table>
    </div>

    <${entityName?uncap_first}-modal ref="modalForm" @ok="modalFormOk"></${entityName?uncap_first}-modal>
  </a-card>
</template>

<script>

  import { getAction, deleteAction } from '@/api/manage'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import ${entityName}Modal from './modules/${entityName}Modal'
  import {filterMultiDictText} from '@/components/dict/JDictSelectUtil'
  import { filterObj } from '@/utils/util';
  <#if query_field_select>
  import JDictSelectTag from '@/components/dict/JDictSelectTag.vue'
  </#if>
  <#if query_field_date>
  import JDate from '@/components/jeecg/JDate.vue'
  </#if>
  <#if list_need_category>
  import { loadCategoryData } from '@/api/api'
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
    name: "${entityName}List",
    mixins:[JeecgListMixin],
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
      JSuperQuery
    },
    data () {
      return {
        description: '${tableVo.ftlDescription}管理页面',
        // 表头
        columns: [
    <#list columns as po>
    <#if po.fieldDbName==tableVo.extendParams.textField>
          {
            title:'${po.filedComment}',
            align:"left",
            dataIndex: '${po.fieldName}'
          },
    </#if>
    </#list>
	<#list columns as po>
	<#if po.isShowList =='Y'>
	<#if po.fieldDbName!=tableVo.extendParams.textField && po.fieldName!=pidFieldName>
          {
            title:'${po.filedComment}',
            align:"left",
            <#if po.sort=='Y'>
            sorter: true,
            </#if>
            <#if po.classType=='date'>
            dataIndex: '${po.fieldName}',
            customRender:function (text) {
              return !text?"":(text.length>10?text.substr(0,10):text)
            }
            <#elseif po.classType=='file'>
            dataIndex: '${po.fieldName}',
            scopedSlots: {customRender: 'fileSlot'}
            <#elseif po.classType=='image'>
            dataIndex: '${po.fieldName}',
            scopedSlots: {customRender: 'imgSlot'}
            <#elseif po.classType=='list' || po.classType=='list_multi' || po.classType=='sel_search' || po.classType=='radio' || po.classType=='checkbox' || po.classType=='sel_depart' || po.classType=='sel_user'>
            dataIndex: '${po.fieldName}_dictText'
            <#elseif po.classType=='switch'>
            dataIndex: '${po.fieldName}',
            <#if po.dictField?default("")?trim?length gt 1>
            customRender: (text) => (!text ? "" : (text == ${po.dictField}[0] ? "是" : "否"))
            <#else>
            customRender: (text) => (!text ? "" : (text == "Y" ? "是" : "否"))
            </#if>
			<#else>
            dataIndex: '${po.fieldName}'
			</#if>
          },
     </#if>
     </#if>
     </#list>
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            width:147,
            scopedSlots: { customRender: 'action' },
          }
        ],
        url: {
          list: "/${entityPackage}/${entityName?uncap_first}/rootList",
          childList: "/${entityPackage}/${entityName?uncap_first}/childList",
          getChildListBatch: "/${entityPackage}/${entityName?uncap_first}/getChildListBatch",
          delete: "/${entityPackage}/${entityName?uncap_first}/delete",
          deleteBatch: "/${entityPackage}/${entityName?uncap_first}/deleteBatch",
          exportXlsUrl: "/${entityPackage}/${entityName?uncap_first}/exportXls",
          importExcelUrl: "${entityPackage}/${entityName?uncap_first}/importExcel",
        },
        expandedRowKeys:[],
        hasChildrenField:"${hasChildrenField}",
        pidField:"${pidFieldName}",
        dictOptions: {},
        loadParent: false,
        superFieldList:[],
      }
    },
    created() {
      this.getSuperFieldList();
    },
    computed: {
      importExcelUrl(){
        <#noparse>return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;</#noparse>
      },
      tableProps() {
        let _this = this
        return {
          // 列表项是否可选择
          rowSelection: {
            selectedRowKeys: _this.selectedRowKeys,
            onChange: (selectedRowKeys) => _this.selectedRowKeys = selectedRowKeys
          }
        }
      }
    },
    methods: {
      loadData(arg){
        if(arg==1){
          this.ipagination.current=1
        }
        this.loading = true
        let params = this.getQueryParams()
        params.hasQuery = 'true'
        getAction(this.url.list,params).then(res=>{
          if(res.success){
            let result = res.result
            if(Number(result.total)>0){
              this.ipagination.total = Number(result.total)
              this.dataSource = this.getDataByResult(res.result.records)
              return this.loadDataByExpandedRows(this.dataSource)
            }else{
              this.ipagination.total=0
              this.dataSource=[]
            }
          }else{
            this.$message.warning(res.message)
          }
        }).finally(()=>{
          this.loading = false
        })
      },
      // 根据已展开的行查询数据（用于保存后刷新时异步加载子级的数据）
      loadDataByExpandedRows(dataList) {
        if (this.expandedRowKeys.length > 0) {
          return getAction(this.url.getChildListBatch,{ parentIds: this.expandedRowKeys.join(',') }).then(res=>{
            if (res.success && res.result.records.length>0) {
              //已展开的数据批量子节点
              let records = res.result.records
              const listMap = new Map();
              for (let item of records) {
                let pid = item[this.pidField];
                if (this.expandedRowKeys.join(',').includes(pid)) {
                 let mapList = listMap.get(pid);
                  if (mapList == null) {
                    mapList = [];
                  }
                  mapList.push(item);
                  listMap.set(pid, mapList);
                }
              }
              let childrenMap = listMap;
              let fn = (list) => {
                if(list) {
                  list.forEach(data => {
                    if (this.expandedRowKeys.includes(data.id)) {
                      data.children = this.getDataByResult(childrenMap.get(data.id))
                      fn(data.children)
                    }
                  })
                }
              }
              fn(dataList)
            }
          })
        } else {
          return Promise.resolve()
        }
      },
      getQueryParams(arg) {
        //获取查询条件
        let sqp = {}
        let param = {}
        if(this.superQueryParams){
          sqp['superQueryParams']=encodeURI(this.superQueryParams)
          sqp['superQueryMatchType'] = this.superQueryMatchType
        }
        if(arg){
          param = Object.assign(sqp, this.isorter ,this.filters);
        }else{
          param = Object.assign(sqp, this.queryParam, this.isorter ,this.filters);
        }
        if(JSON.stringify(this.queryParam) === "{}" || arg){
          param.hasQuery = 'false'
        }else{
          param.hasQuery = 'true'
        }
        param.field = this.getQueryField();
        param.pageNo = this.ipagination.current;
        param.pageSize = this.ipagination.pageSize;
        return filterObj(param);
      },
      searchReset() {
        //重置
        this.expandedRowKeys = []
        this.queryParam = {}
        this.loadData(1);
      },
      getDataByResult(result){
        if(result){
          return result.map(item=>{
            //判断是否标记了带有子节点
            if(item[this.hasChildrenField]=='1'){
              let loadChild = { id: item.id+'_loadChild', name: 'loading...', isLoading: true }
              item.children = [loadChild]
            }
            return item
          })
        }
      },
      handleExpand(expanded, record){
        // 判断是否是展开状态
        if (expanded) {
          this.expandedRowKeys.push(record.id)
          if (record.children.length>0 && record.children[0].isLoading === true) {
            let params = this.getQueryParams(1);//查询条件
            params[this.pidField] = record.id
            params.hasQuery = 'false'
            params.superQueryParams=""
            getAction(this.url.childList,params).then((res)=>{
              if(res.success){
                if(res.result.records){
                  record.children = this.getDataByResult(res.result.records)
                  this.dataSource = [...this.dataSource]
                }else{
                  record.children=''
                  record.hasChildrenField='0'
                }
              }else{
                this.$message.warning(res.message)
              }
            })
          }
        }else{
          let keyIndex = this.expandedRowKeys.indexOf(record.id)
          if(keyIndex>=0){
            this.expandedRowKeys.splice(keyIndex, 1);
          }
        }
      },
      handleAddChild(record){
        this.loadParent = true
        let obj = {}
        obj[this.pidField] = record['id']
        this.$refs.modalForm.add(obj);
      },
      handleDeleteNode(id) {
        if(!this.url.delete){
          this.$message.error("请设置url.delete属性!")
          return
        }
        var that = this;
        deleteAction(that.url.delete, {id: id}).then((res) => {
          if (res.success) {
             that.loadData(1)
          } else {
            that.$message.warning(res.message);
          }
        });
      },
      batchDel(){
        if(this.selectedRowKeys.length<=0){
          this.$message.warning('请选择一条记录！');
          return false;
        }else{
          let ids = "";
          let that = this;
          that.selectedRowKeys.forEach(function(val) {
            ids+=val+",";
          });
          that.$confirm({
            title:"确认删除",
            content:"是否删除选中数据?",
            onOk: function(){
              that.handleDeleteNode(ids)
              that.onClearSelected();
            }
          });
        }
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