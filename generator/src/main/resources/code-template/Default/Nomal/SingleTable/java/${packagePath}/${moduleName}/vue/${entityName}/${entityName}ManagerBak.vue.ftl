<#list entity.fields as field>
    <#if field.isPrimaryKey>
        <#assign pk=field>
    </#if>
</#list>

<template>
    <page-header-wrapper>
        <a-card :bordered="false">
            <!-- 搜索过滤区域 -->
            <div class="table-page-search-wrapper">
                <!--<a-form layout="inline">
                  <a-row :gutter="48">
                    <a-col :md="8" :sm="24">
                      <a-form-item label="规则编号">
                        <a-input v-model="queryParam.id" placeholder="" />
                      </a-form-item>
                    </a-col>
                    <a-col :md="8" :sm="24">
                      <a-form-item label="使用状态">
                        <a-select v-model="queryParam.status" placeholder="请选择" default-value="0">
                          <a-select-option value="0">全部</a-select-option>
                          <a-select-option value="1">关闭</a-select-option>
                          <a-select-option value="2">运行中</a-select-option>
                        </a-select>
                      </a-form-item>
                    </a-col>
                    <template v-if="advanced">
                      <a-col :md="8" :sm="24">
                        <a-form-item label="调用次数">
                          <a-input-number v-model="queryParam.callNo" style="width: 100%" />
                        </a-form-item>
                      </a-col>
                      <a-col :md="8" :sm="24">
                        <a-form-item label="更新日期">
                          <a-date-picker v-model="queryParam.date" style="width: 100%" placeholder="请输入更新日期" />
                        </a-form-item>
                      </a-col>
                      <a-col :md="8" :sm="24">
                        <a-form-item label="使用状态">
                          <a-select v-model="queryParam.useStatus" placeholder="请选择" default-value="0">
                            <a-select-option value="0">全部</a-select-option>
                            <a-select-option value="1">关闭</a-select-option>
                            <a-select-option value="2">运行中</a-select-option>
                          </a-select>
                        </a-form-item>
                      </a-col>
                      <a-col :md="8" :sm="24">
                        <a-form-item label="使用状态">
                          <a-select placeholder="请选择" default-value="0">
                            <a-select-option value="0">全部</a-select-option>
                            <a-select-option value="1">关闭</a-select-option>
                            <a-select-option value="2">运行中</a-select-option>
                          </a-select>
                        </a-form-item>
                      </a-col>
                    </template>
                    <a-col :md="!advanced && 8 || 24" :sm="24">
                      <span class="table-page-search-submitButtons" :style="advanced && { float: 'right', overflow: 'hidden' } || {} ">
                        <a-button type="primary" @click="$refs.table.refresh(true)">查询</a-button>
                        <a-button style="margin-left: 8px" @click="() => this.queryParam = {}">重置</a-button>
                        <a @click="toggleAdvanced" style="margin-left: 8px">
                          {{ advanced ? '收起' : '展开' }}
                          <a-icon :type="advanced ? 'up' : 'down'" />
                        </a>
                      </span>
                    </a-col>
                  </a-row>
                </a-form>-->
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
            <s-table
                    ref="table"
                    size="middle"
                    rowKey="id"
                    :columns="columns"
                    :data="loadData"
                    :alert="true"
                    :rowSelection="rowSelection"
                    :bordered="true"
                    showPagination="auto">
        <span slot="serial" slot-scope="text, record, index">
          {{ index + 1 }}
        </span>
                <!-- <span slot="status" slot-scope="text">
                  <a-badge :status="text | statusTypeFilter" :text="text | statusFilter" />
                </span> -->
                <!-- <span slot="description" slot-scope="text">
                  <ellipsis :length="4" tooltip>{{ text }}</ellipsis>
                </span> -->

                <span slot="action" slot-scope="text, record">
          <template>
            <a @click="handleEdit(record)">编辑</a>
            <a-divider type="vertical"/>
            <a-popconfirm title="删除后不能恢复，确定删除？" ok-text="是" cancel-text="否" @confirm="handleDelete(record)">
              <a>删除</a>
            </a-popconfirm>
              <!-- <a-divider type="vertical" /> -->
              <!-- <a @click="handleDetail(record)">详情</a> -->
              <!-- <a-dropdown>
                <a-menu slot="overlay">
                  <a-menu-item>
                    <a @click="handleEdit(record)">编辑</a>
                  </a-menu-item>
                  <a-menu-item>
                    <a @click="handleDelete(record)">删除</a>
                  </a-menu-item>
                  <a-menu-item>
                    <a @click="handleDetail(record)">详情</a>
                  </a-menu-item>
                </a-menu>
                <a>更多
                  <a-icon type="down" />
                </a>
              </a-dropdown> -->
          </template>
        </span>
            </s-table>
            <!-- 嵌入表单区域 -->
            <create-modal
                    ref="createModal"
                    :visible="visible"
                    :loading="confirmLoading"
                    :model="mdl"
                    @cancel="handleCancel"
                    @ok="handleOk"/>
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
    import {STable, Ellipsis} from '@/components'
    import {toDateTime, toDate} from '@/utils/datetime'
    import {getList, findById, save, deleteItem} from './api'
    import CreateModal from './form-modal.vue' // 切换到抽屉模式 引用改为 './form-drawer.vue'
    import DetailModal from './detail-modal.vue'

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
            STable,
            Ellipsis,
            CreateModal,
            DetailModal
        },
        data() {
            return {
                columns: columns,
                // create model
                visible: false,
                // 详情页显示
                detailVisible: false,
                confirmLoading: false,
                mdl: null,
                // 高级搜索 展开/关闭
                advanced: false,
                // 查询参数
                queryParam: {},
                // 加载数据方法 必须为 Promise 对象
                loadData: (parameter) => {
                    const requestParameters = Object.assign({}, parameter, this.queryParam)
                    console.log('loadData request parameters:', requestParameters)
                    // TODO:sss
                    return getList(requestParameters).then((res) => {
                        console.log(res)
                        const totalPage = parseInt(res.count / requestParameters.pageSize) + 1
                        const result = {
                            pageSize: requestParameters.pageSize,
                            pageNo: requestParameters.pageNo,
                            totalCount: res.count,
                            totalPage: totalPage,
                            data: res.data
                        }
                        console.log(result)
                        return result
                    })
                },
                selectedRowKeys: [],
                selectedRows: []
            }
        },
        filters: {},
        created() {
            // 此处可以进行表单中的一些字典的初始化
            // getRoleList({ t: new Date() })
        },
        computed: {
            rowSelection() {
                return {
                    selectedRowKeys: this.selectedRowKeys,
                    onChange: this.onSelectChange
                }
            }
        },
        methods: {
            /** 触发新增 */
            handleAdd() {
                // 方式一 弹框编辑模式
                this.mdl = null
                this.visible = true

                // 方式二 编辑页的方式,需要将form-page 注册到路由，path替换为真实注册的路由
                //this.$router.push({ path: '填入真实路由,示例：/tpl/formpage' })
            },
            /** 触发编辑 */
            handleEdit(record) {
                // 方式一 弹框编辑模式
                this.visible = true
                this.mdl = {...record}
                // 方式二 编辑页的方式,需要将form-page 注册到路由，path替换为真实注册的路由
                // const param = {path: '填入真实路由,示例：/tpl/formpage'};
                <#--// param.query = {id:record.${pk.attrname}}-->
                // this.$router.push(param)
            },
            /** 查看详情 */
            handleDetail(record) {
                this.detailVisible = true
                this.mdl = {...record}
            },
            /** 单条业务删除 */
            handleDelete(record) {
                <#--                ${pk.attrname}-->
                deleteItem(record.id)
                    .then((res) => {
                        this.refreshTable()
                        this.$message.info('删除成功')
                    }).catch((e) => {
                    this.$message.error('删除失败')
                })
            },
            /** 表单保存 */
            handleOk() {
                const form = this.$refs.createModal.form
                this.confirmLoading = true
                form.validateFields((errors, values) => {
                    if (!errors) {
                        console.log('values', values)
                        save(values)
                            .then((res) => {
                                this.refreshTable()
                                this.$message.info('修改成功')
                            })
                            .catch((e) => {
                                // 保存失败
                                // 触发加载动画
                                this.confirmLoading = false
                                this.$message.error('保存失败')
                            })
                    } else {
                        this.confirmLoading = false
                    }
                })
            },
            /** 表单填写过程中取消 */
            handleCancel() {
                this.visible = false
                const form = this.$refs.createModal.form
                form.resetFields() // 清理表单数据（可不做）
            },
            handleDetailCancel() {
                this.detailVisible = false
            },
            /** 当表格中行选中执行 */
            onSelectChange(selectedRowKeys, selectedRows) {
                this.selectedRowKeys = selectedRowKeys
                this.selectedRows = selectedRows
            },
            /** 切换高级搜索模式 */
            toggleAdvanced() {
                this.advanced = !this.advanced
            },
            /** 重置表单搜索表单项 */
            resetSearchForm() {
                this.queryParam = {
                    date: moment(new Date())
                }
            },
            /** 刷新表格数据 */
            refreshTable() {
                const form = this.$refs.createModal.form
                // 关闭新增、编辑的窗口
                this.visible = false
                // 触发加载动画
                this.confirmLoading = false
                // 重置表单数据
                form.resetFields()
                // 刷新表格
                this.$refs.table.refresh()
            }
        }
    }
</script>
