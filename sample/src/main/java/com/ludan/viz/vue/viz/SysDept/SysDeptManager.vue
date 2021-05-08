
<template>
    <page-header-wrapper>
                <a-card :bordered="false">
                    <!-- 搜索过滤区域 -->
            <div class="table-page-search-wrapper">
                <a-form layout="inline">
                    <a-row :gutter="48">
                            <a-col :md="8" :sm="24">
                            <a-form-item label="部门名称" v-bind="layoutCol">
                                        <a-input v-model="queryParam.deptName"  />
                            </a-form-item>
                            </a-col>
                            <a-col :md="8" :sm="24">
                            <a-form-item label="负责人" v-bind="layoutCol">
                                        <a-input v-model="queryParam.leader"  />
                            </a-form-item>
                            </a-col>
                            <a-col :md="8" :sm="24">
                            <a-form-item label="联系电话" v-bind="layoutCol">
                                        <a-input v-model="queryParam.phone"  />
                            </a-form-item>
                            </a-col>
                            <a-col :md="8" :sm="24">
                            <a-form-item label="邮箱" v-bind="layoutCol">
                                        <a-input v-model="queryParam.email"  />
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
                    rowKey="deptId"
                    :columns="columns"
                    :dataSource="dataSource"
                    :pagination="ipagination"
                    :loading="loading"
                    @change="handleTableChange"
                    @expand="handleExpand"
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
                                <a @click="handleAddChild(record)">新增下级</a>
                            </a-menu-item>
                            <a-menu-item key="3" type="primary">
                                <a @click="handleDelete(record.deptId)">删除</a>
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
    import ModalForm from './components/SysDeptModal' // 切换到抽屉模式 引用改为 './drawer.vue'
    import { getDictionaryByCodes } from '@/utils/dictUtil'
    import { httpGet } from '@/utils/httpClient'

    export default {
        name: 'TableList',
        components: {
            ModalForm
        },
        mixins: [dictMixin, TablePageMixin],
        data() {
            return {
                layoutCol: {
                    labelCol: { span: 4 },
                    wrapperCol: { span: 20 }
                },
                columns: [
                   // {
                   //     title: '序号',
                   //     scopedSlots: {customRender: 'serial'},
                   //     width: '70px',
                   // },
                       {
                          title: '部门名称',
                          dataIndex: 'deptName',
                          ellipsis: false, // 超过宽度将自动省略
                          align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                          width: '180px',
                          customRender: (value) => value
                       },
                       {
                          title: '负责人',
                          dataIndex: 'leader',
                          ellipsis: false, // 超过宽度将自动省略
                          align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                          width: '180px',
                          customRender: (value) => value
                       },
                       {
                          title: '联系电话',
                          dataIndex: 'phone',
                          ellipsis: false, // 超过宽度将自动省略
                          align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                          width: '180px',
                          customRender: (value) => value
                       },
                       {
                          title: '邮箱',
                          dataIndex: 'email',
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
                    list: '/api-sample/SysDept/list',
                    findRoot: '/api-sample/SysDept/root',
                    findChildren: '/api-sample/SysDept/children',
                    delete: '/api-sample/SysDept/delete',
                    deleteBatch: '/api-sample/SysDept/deleteBatch',
                    exportXlsUrl: '/api-sample/SysDept/exportXlsx',
                    importExcelUrl: '/api-sample/SysDept/importExcel'
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
            loadData (arg) {
                if (!this.url.list) {
                    this.$message.error('请设置url.list属性!')
                    return
                }
                // 加载数据 若传入参数1则加载第一页的内容
                if (arg === 1) {
                    this.ipagination.current = 1
                }
                var params = this.getQueryParams() // 查询条件
                this.loading = true
                httpGet(this.url.findRoot, params).then((res) => {
                    if (res.code === 0) {
                        this.dataSource = this.transformListResponseData(res.data)
                        this.ipagination.total = res.count
                    }
                    this.loading = false
                })
            },
            transformListResponseData (data) {
                data.forEach((element) => {
                    element.children = []
                })
                return data
            },
            handleOk (res) {
                // 新增/修改 成功时，重载列表
                // this.loadData()
                console.log('handleOk', res)

                if (this.operation && this.operation === 'addChild') {
                    res.children = []
                    this.currentItem.children.push(res)
                }
                if (this.operation && this.operation === 'edit') {
                    Object.assign(this.currentItem, res)
                }
                if (this.operation && this.operation === 'delete') {
                    console.log('delete')
                }
            },
            handleEdit: function (record) {
                this.currentItem = record
                this.operation = 'edit'
                // 弹框编辑
                this.$refs.modalForm.edit(record)
                this.$refs.modalForm.title = '编辑'

                // 详情页编辑
                // this.$router.push(`/RegistrationManager/detail/zdba/true/${record.id}`)
            },
            handleAddChild (current) {
                this.currentItem = current
                this.operation = 'addChild'

                this.$refs.modalForm.edit({ parentId: current.deptId })
                this.$refs.modalForm.title = `新增下级`
            },
            handleExpand (expanded, record) {
                console.log(expanded)
                console.log(record)
                // 折叠，不再加载数据
                if (!expanded) {
                    return
                }
                // 当有children，则不再加载数据
                if (record.children.length > 0) {
                    return
                }
                // const hide = this.$message.loading('加载中...', 0)
                this.loading = true
                httpGet(`${this.url.findChildren}/${record.deptId}`)
                    .then((res) => {
                        if (res.datas.length > 0) {
                            res.datas.forEach((el) => {
                                el.children = []
                            })
                            record.children = res.datas
                        } else {
                            this.$message.info('无更多数据')
                        }
                    })
                    .catch((err) => {
                        this.$message.error('数据加载异常')
                        console.error(err)
                    })
                    .finally(() => {
                        this.loading = false
                        // hide()
                    })
            }
        }
    }
</script>
