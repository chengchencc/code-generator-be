
<template>
    <page-header-wrapper>
                <a-card :bordered="false">
                    <!-- 搜索过滤区域 -->
            <div class="table-page-search-wrapper">
                <a-form layout="inline">
                    <a-row :gutter="48">
                            <a-col :md="8" :sm="24">
                            <a-form-item label="租户Id">
                                        <a-input v-model="queryParam.tenantId"  />
                            </a-form-item>
                            </a-col>
                            <a-col :md="8" :sm="24">
                            <a-form-item label="创建时间">
                                        <a-date-picker v-model="queryParam.creationTime" style="width: 100%" />
                            </a-form-item>
                            </a-col>
                            <a-col :md="8" :sm="24">
                            <a-form-item label="创建者">
                                        <a-input v-model="queryParam.creationUserId"  />
                            </a-form-item>
                            </a-col>
                            <a-col :md="8" :sm="24">
                            <a-form-item label="删除时间">
                                        <a-date-picker v-model="queryParam.deletionTime" style="width: 100%" />
                            </a-form-item>
                            </a-col>
                            <a-col :md="8" :sm="24">
                            <a-form-item label="删除人">
                                        <a-input v-model="queryParam.deletionUserId"  />
                            </a-form-item>
                            </a-col>
                            <a-col :md="8" :sm="24">
                            <a-form-item label="最后修改时间">
                                        <a-date-picker v-model="queryParam.lastModifyTime" style="width: 100%" />
                            </a-form-item>
                            </a-col>
                            <a-col :md="8" :sm="24">
                            <a-form-item label="最后修改人">
                                        <a-input v-model="queryParam.lastModifyUserId"  />
                            </a-form-item>
                            </a-col>
                            <a-col :md="8" :sm="24">
                            <a-form-item label="作者">
                                        <a-input v-model="queryParam.authorName"  />
                            </a-form-item>
                            </a-col>
                            <a-col :md="8" :sm="24">
                            <a-form-item label="作者邮箱">
                                        <a-input v-model="queryParam.email"  />
                            </a-form-item>
                            </a-col>
                            <a-col :md="8" :sm="24">
                            <a-form-item label="模块名">
                                        <a-input v-model="queryParam.moduleName"  />
                            </a-form-item>
                            </a-col>
                            <a-col :md="8" :sm="24">
                            <a-form-item label="包名">
                                        <a-input v-model="queryParam.packageName"  />
                            </a-form-item>
                            </a-col>
                            <a-col :md="8" :sm="24">
                            <a-form-item label="模板">
                                        <a-select v-model="queryParam.uiTemplate" >
                                            <a-select-option value="">请选择</a-select-option>
                                            <a-select-option v-for="(item, name) in pageDict.projectSource" :key="name" :value="item.code">
                                                {{ item.value }}
                                            </a-select-option>
                                        </a-select>
                            </a-form-item>
                            </a-col>

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
        {
            title: '主键',
            dataIndex: 'id',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: (value) => value
        },
        {
            title: '租户Id',
            dataIndex: 'tenantId',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: (value) => value
        },
        {
            title: '创建时间',
            dataIndex: 'creationTime',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: toDateTime,
        },
        {
            title: '创建者',
            dataIndex: 'creationUserId',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: (value) => value
        },
        {
            title: '删除时间',
            dataIndex: 'deletionTime',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: toDateTime,
        },
        {
            title: '删除人',
            dataIndex: 'deletionUserId',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: (value) => value
        },
        {
            title: '最后修改时间',
            dataIndex: 'lastModifyTime',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: toDateTime,
        },
        {
            title: '最后修改人',
            dataIndex: 'lastModifyUserId',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: (value) => value
        },
        {
            title: '作者',
            dataIndex: 'authorName',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: (value) => value
        },
        {
            title: '作者邮箱',
            dataIndex: 'email',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: (value) => value
        },
        {
            title: '模块名',
            dataIndex: 'moduleName',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: (value) => value
        },
        {
            title: '包名',
            dataIndex: 'packageName',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: (value) => value
        },
        {
            title: '模板',
            dataIndex: 'uiTemplate',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: (value) => value
        },
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
                    'projectSource',
                ]
                getDictionaryByCodes(dictCodes).then((res) => {
                    this.pageDict = res
                })
            }
        }
    }
</script>
