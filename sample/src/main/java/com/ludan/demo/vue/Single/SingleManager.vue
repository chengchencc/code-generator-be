
<template>
    <page-header-wrapper>
                <a-card :bordered="false">
                    <!-- 搜索过滤区域 -->
            <div class="table-page-search-wrapper">
                <a-form layout="inline">
                    <a-row :gutter="48">
                            <a-col :md="8" :sm="24">
                            <a-form-item label="id">
                                        <a-input-number v-model="queryParam.id" style="width:100%"/>
                            </a-form-item>
                            </a-col>
                            <a-col :md="8" :sm="24">
                            <a-form-item label="propInt">
                                        <a-input-number v-model="queryParam.propInt" style="width:100%"/>
                            </a-form-item>
                            </a-col>
                            <a-col :md="8" :sm="24">
                            <a-form-item label="propString">
                                        <a-input v-model="queryParam.propString"  />
                            </a-form-item>
                            </a-col>
                            <a-col :md="8" :sm="24">
                            <a-form-item label="propBool">
                                        <a-checkbox-group v-model="queryParam.propBool" style="width: 100%;">
                                            <a-row>
                                            </a-row>
                                        </a-checkbox-group>
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
                <a-button type="default" icon="export" @click="handleExportXls">导出</a-button>
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
                <a-table
                    ref="table"
                    bordered
                    size="default"
                    rowKey="id"
                    :columns="columns"
                    :dataSource="dataSource"
                    :pagination="ipagination"
                    :loading="loading"
                    @change="handleTableChange"
                    :rowSelection="rowSelection"
                    :scroll="{x: 1050}"
                    class="table-page-container-wrapper"
                >
                    <span slot="serial" slot-scope="text, record, index">
                      {{ index + 1 }}
                    </span>
                    <span slot="status" slot-scope="text"><a-tag color="orange">{{text}}</a-tag></span>
                    <span slot="action" slot-scope="text, record">
                      <template>
                        <a-dropdown>
                          <a-menu slot="overlay">
                            <a-menu-item key="1" type="primary">
                              <a @click="handleEdit(record)">编辑</a>
                            </a-menu-item>
                            <a-menu-item key="2" type="primary">
                              <a @click="handleDelete(record.id)">删除</a>
                            </a-menu-item>
                          </a-menu>
                          <a>更多<a-icon type="down" /></a>
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
    import { getNameByDict } from '@/utils/dealData'
    import { dictMixin } from '@/store/dict-mixin'
    import { TablePageMixin } from '@/core/mixins/TablePageMixin2'
    import ModalForm from './components/SingleModal' // 切换到抽屉模式 引用改为 './drawer.vue'
    import { getDictionaryByCodes } from '@/utils/dictUtil'

    export default {
        name: 'TableList',
        components: {
            ModalForm
        },
        mixins: [dictMixin, TablePageMixin],
        data() {
            return {
                columns: [
                   {
                       title: '序号',
                       scopedSlots: {customRender: 'serial'},
                       width: '70px',
                   },
                       {
                          title: 'id',
                          dataIndex: 'id',
                          ellipsis: false, // 超过宽度将自动省略
                          align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                          width: '180px',
                          customRender: (value) => value
                       },
                       {
                          title: 'propInt',
                          dataIndex: 'propInt',
                          ellipsis: false, // 超过宽度将自动省略
                          align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                          width: '180px',
                          customRender: (value) => value
                       },
                       {
                          title: 'propString',
                          dataIndex: 'propString',
                          ellipsis: false, // 超过宽度将自动省略
                          align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                          width: '180px',
                          customRender: (value) => value
                       },
                       {
                          title: 'propBool',
                          dataIndex: 'propBool',
                          ellipsis: false, // 超过宽度将自动省略
                          align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                          width: '180px',
                          customRender: (value) => value
                       },
                   {
                       title: '操作',
                       dataIndex: 'action',
                       width: '80px',
                       fixed: 'right',
                       scopedSlots: {customRender: 'action'}
                   }
                ],
                //页面级字典
                pageDict: {},
                url: {
                    list: '/api-sample/Single/list',
                    delete: '/api-sample/Single/delete',
                    deleteBatch: '/api-sample/Single/deleteBatch',
                    exportXlsUrl: '/api-sample/Single/exportXlsx',
                    importExcelUrl: '/api-sample/Single/importExcel'
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
                ]
                if(dictCodes.length>0) {
                    getDictionaryByCodes(dictCodes).then((res) => {
                        this.pageDict = res
                    })
                }
            },
            handleEdit: function (record) {
                // 弹框编辑
                this.$refs.modalForm.edit(record)
                this.$refs.modalForm.title = '编辑'

                // 详情页编辑
                // this.$router.push(`/RegistrationManager/detail/zdba/true/${record.id}`)
            }
        }
    }
</script>
