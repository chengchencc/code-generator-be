<#include "/common/utils.ftl">
<#include "/common/dataEntityUtils.ftl">
<template>
    <page-header-wrapper>
                <a-card :bordered="false">
                    <!-- 搜索过滤区域 -->
            <div class="table-page-search-wrapper">
                <a-form layout="inline">
                    <a-row :gutter="48">
                        <#list queryFields as field >
                            <#assign fieldui = field.dataFieldUI>
                            <a-col :md="8" :sm="24">
                            <a-form-item label="${field.description}">
                                <#switch fieldui.controlType>
                                    <#case "PlainText">
                                        <a-input v-model="queryParam.${field.name}" />
                                    <#--                        <span class="ant-form-text">-->
                                    <#--                        -->
                                    <#--                        </span>-->
                                        <#break>
                                    <#case "InputText">
                                        <a-input v-model="queryParam.${field.name}"  />
                                        <#break>
                                    <#case "Number">
                                        <a-input-number v-model="queryParam.${field.name}" style="width:100%"/>
                                        <#break>
                                    <#case "Password">
                                        <a-input v-model="queryParam.${field.name}" />
                                        <#break>
                                    <#case "SelectOne">
                                        <a-select v-model="queryParam.${field.name}" >
                                            <a-select-option value="">请选择</a-select-option>
                                            <a-select-option v-for="(item, name) in pageDict.${field.dataFieldUI.dictCode}" :key="name" :value="item.code">
                                                {{ item.value }}
                                            </a-select-option>
<#--                                            <a-select-option value="china">-->
<#--                                                China-->
<#--                                            </a-select-option>-->
<#--                                            <a-select-option value="usa">-->
<#--                                                U.S.A-->
<#--                                            </a-select-option>-->
                                        </a-select>
                                        <#break>
                                    <#case "SelectMany">
                                        <a-select v-model="queryParam.${field.name}" mode="multiple">
                                            <a-select-option v-for="(item, name) in pageDict.${field.dataFieldUI.dictCode}" :key="name" :value="item.code">
                                                {{ item.value }}
                                            </a-select-option>
<#--                                            <a-select-option value="china">-->
<#--                                                China-->
<#--                                            </a-select-option>-->
<#--                                            <a-select-option value="usa">-->
<#--                                                U.S.A-->
<#--                                            </a-select-option>-->
                                        </a-select>
                                        <#break>
                                    <#case "Checkbox">
                                        <a-checkbox-group v-model="queryParam.${field.name}" style="width: 100%;">
                                            <a-row>
                                                <a-col :span="8" v-for="(item, name) in pageDict.${field.dataFieldUI.dictCode}" :key="name" >
                                                    <a-checkbox :value="item.code">
                                                        {{ item.value }}
                                                    </a-checkbox>
                                                </a-col>
<#--                                                <a-col :span="8" >-->
<#--                                                    <a-checkbox value="A">-->
<#--                                                        A-->
<#--                                                    </a-checkbox>-->
<#--                                                </a-col>-->
<#--                                                <a-col :span="8">-->
<#--                                                    <a-checkbox disabled value="B">-->
<#--                                                        B-->
<#--                                                    </a-checkbox>-->
<#--                                                </a-col>-->
<#--                                                <a-col :span="8">-->
<#--                                                    <a-checkbox value="C">-->
<#--                                                        C-->
<#--                                                    </a-checkbox>-->
<#--                                                </a-col>-->
<#--                                                <a-col :span="8">-->
<#--                                                    <a-checkbox value="D">-->
<#--                                                        D-->
<#--                                                    </a-checkbox>-->
<#--                                                </a-col>-->
<#--                                                <a-col :span="8">-->
<#--                                                    <a-checkbox value="E">-->
<#--                                                        E-->
<#--                                                    </a-checkbox>-->
<#--                                                </a-col>-->
                                            </a-row>
                                        </a-checkbox-group>
                                        <#break>
                                    <#case "Radio">
                                        <a-radio-group v-model="queryParam.${field.name}">
                                            <a-radio v-for="(item, name) in pageDict.${field.dataFieldUI.dictCode}" :key="name" :value="item.code">
                                                {{ item.value }}
                                            </a-radio>
<#--                                            <a-radio value="b">-->
<#--                                                item 2-->
<#--                                            </a-radio>-->
<#--                                            <a-radio value="c">-->
<#--                                                item 3-->
<#--                                            </a-radio>-->
                                        </a-radio-group>
                                        <#break>
                                    <#case "Date">
                                        <a-date-picker v-model="queryParam.${field.name}" style="width: 100%" />
                                        <#break>
                                    <#case "DateTime">
                                        <a-date-picker v-model="queryParam.${field.name}" style="width: 100%" />
                                        <#break>
                                    <#case "Time">
                                        <a-date-picker v-model="queryParam.${field.name}" style="width: 100%"/>
                                        <#break>
                                    <#case "File">
                                        <span>${fieldui.controlType} 类型不支持搜索</span>
                                        <#break>
                                    <#case "Image">
                                        <span>${fieldui.controlType} 类型不支持搜索</span>
                                        <#break>
                                    <#case "TextArea">
                                        <span>${fieldui.controlType} 类型不支持搜索</span>
                                        <#break>
                                    <#case "RichText">
                                        <span>${fieldui.controlType} 类型不支持搜索</span>
                                        <#break>
                                    <#case "UserSelect">
                                        <span>${fieldui.controlType} 类型不支持搜索</span>
                                        <#break>
                                    <#case "OrgSelect">
                                        <span>${fieldui.controlType} 类型不支持搜索</span>
                                        <#break>
                                    <#case "Region">
                                        <span>${fieldui.controlType} 类型不支持搜索</span>
                                        <#break>
                                    <#case "TreeSelect">
                                        <span>${fieldui.controlType} 类型不支持搜索</span>
                                        <#break>
                                    <#case "Modal">
                                        <span>${fieldui.controlType} 类型不支持搜索</span>
                                        <#break>
                                    <#default>
                                        <span>不支持类型</span>
                                </#switch>
                            </a-form-item>
                            </a-col>
                        </#list>

                        <template v-if="advanced">
                            <!--<a-col :md="8" :sm="24">
                                <a-form-item label="调用次数">
                                    <a-input-number v-model="queryParam.callNo" style="width: 100%"/>
                                </a-form-item>
                            </a-col>-->
                        </template>
                        <a-col :md="!advanced && 8 || 24" :sm="24">
                          <span class="table-page-search-submitButtons"
                                :style="advanced && { float: 'right', overflow: 'hidden' } || {} ">
                            <a-button type="primary" @click="loadData(1)">查询</a-button>
                            <a-button style="margin-left: 8px" @click="() => this.queryParam = {}">重置</a-button>
                            <a @click="toggleAdvanced" style="margin-left: 8px">
                              {{ advanced ? '收起' : '展开' }}
                              <a-icon :type="advanced ? 'up' : 'down'"/>
                            </a>
                          </span>
                        </a-col>
                    </a-row>
                </a-form>
            </div>
            <!-- 功能按钮区域 -->
            <div class="table-operator">
                <a-button type="primary" icon="plus" @click="handleAdd">新建</a-button>
                <a-dropdown v-if="selectedRowKeys.length > 0">
                    <a-menu slot="overlay">
                        <a-menu-item key="1">
                            <a-icon type="delete"/>
                            删除
                        </a-menu-item>
                        <!-- lock | unlock -->
                        <!-- <a-menu-item key="2">
                                      <a-icon type="lock" />锁定
                                    </a-menu-item> -->
                    </a-menu>
                    <a-button style="margin-left: 8px">
                        批量操作
                        <a-icon type="down"/>
                    </a-button>
                </a-dropdown>
            </div>

            <!-- 表格内容区域 -->
            <table-wrapper>
                <div class="ant-alert ant-alert-info" style="margin-bottom: 16px">
                    <i class="anticon anticon-info-circle ant-alert-icon"></i>已选择&nbsp;<a style="font-weight: 600">{{
                        selectedRowKeys.length
                        }}</a>项&nbsp;&nbsp;
                    <a style="margin-left: 24px" @click="onClearSelected">清空</a>
                </div>
                <a-table ref="table"
                         bordered
                         size="default"
                         rowKey="id"
                         :columns="columns"
                         :dataSource="dataSource"
                         :pagination="ipagination"
                         :loading="loading"
                         @change="handleTableChange"
                         :rowSelection="rowSelection"
                         class="table-page-container-wrapper">
                  <span slot="serial" slot-scope="text, record, index">
                    {{ index + 1 }}
                  </span>
                    <span slot="status" slot-scope="text"><a-tag color="orange">text</a-tag></span>
                    <span slot="action" slot-scope="text, record">
            <template>
              <a-dropdown>
                <a-menu slot="overlay">
                  <a-menu-item key="1" type="primary">
                    <a @click="handleEdit(record)">编辑</a>
                  </a-menu-item>
                </a-menu>
                <a>更多
                  <a-icon type="down" />
                </a>
              </a-dropdown>
            </template>
          </span>
                </a-table>
            </table-wrapper>
            <!-- 嵌入表单区域 -->
            <modal-form ref="modalForm" @ok="handleOk" @cancel="handleCancel" />

<#--            <modal-form-->
<#--                    ref="modalForm"-->
<#--                    :visible="visible"-->
<#--                    :loading="confirmLoading"-->
<#--                    :model="mdl"-->
<#--                    @cancel="handleCancel"-->
<#--                    @ok="handleOk"/>-->
            <!-- 表单详情 -->
            <!-- <detail-modal
                    ref="detailModal"
                    :visible="detailVisible"
                    :loading="confirmLoading"
                    :model="mdl"
                    @cancel="handleDetailCancel" /> -->
        </a-card>
    </page-header-wrapper>
</template>

<script>
    import moment from 'moment'
    import { toDateTime, toDate } from '@/utils/datetime'
    import { dictMixin } from '@/store/dict-mixin'
    import { TablePageMixin } from '@/core/mixins/TablePageMixin2'
    import ModalForm from './components/GeneratorRuleModal' // 切换到抽屉模式 引用改为 './form-drawer.vue'
    import { getDictionaryByCodes } from '@/utils/dictUtil'

    const columns = [
        {
            title: '#',
            scopedSlots: {customRender: 'serial'}
        },
        <#list entity.fields as field >
        <#assign fieldui = field.dataFieldUI>
        {
            title: '${field.description}',
            dataIndex: '${field.name}',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            <#switch field.dataFieldType>
            <#case "DATETIME">
            customRender: toDateTime,
            <#break>
            <#case "DECIMAL">
            customRender: (value) => value
            <#break>
            <#case "BOOLEAN">
            customRender: (value) => value ? '是' : '否'
            <#break>
            <#default>
            customRender: (value) => value
            </#switch>
            <#--            <#if field.dataFieldType = "">-->
            <#--            customRender: toDateTime,-->
            <#--            <#elseif field.-->
            <#--            </#if>-->
        },
        </#list>
        {
            title: '操作',
            dataIndex: 'action',
            width: '200px',
            scopedSlots: {customRender: 'action'}
        }
    ]

    export default {
        name: 'TableList',
        components: {
            ModalForm
        },
        mixins: [dictMixin, TablePageMixin],
        data() {
            return {
                columns: columns,
                //页面级字典
                pageDict: {},
                url: {
                    list: '/api-sample/GeneratorRule/list',
                    delete: '/api-sample/GeneratorRule/delete',
                    deleteBatch: '/api-sample/GeneratorRule/deleteBatch',
                    exportXlsUrl: '/api-sample/GeneratorRule/exportXlsx',
                    importExcelUrl: '/api-sample/GeneratorRule/importExcel'
                }
            }
        },
        filters: {},
        created() {
            // 此处可以进行表单中的一些字典的初始化
            // getRoleList({ t: new Date() })
        },
        computed: {},
        methods: {
            initDictConfig () {
                console.log('初始化页面级字典项')
                const dictCodes = [
                <#list dictFields as dictField>
                    <#if dictField.dataFieldUI.dictCode?has_content>
                    '${dictField.dataFieldUI.dictCode}',
                    </#if>
                </#list>
                ]
                getDictionaryByCodes(dictCodes).then((res) => {
                    this.pageDict = res
                })
            }
        }
    }
</script>
