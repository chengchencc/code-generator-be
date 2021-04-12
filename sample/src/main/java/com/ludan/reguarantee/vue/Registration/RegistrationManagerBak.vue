
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
        {
            title: '雪花算法生成的递增主键',
            dataIndex: 'id',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: (value) => value
        },
        {
            title: '工作流nodeId',
            dataIndex: 'wfNodeId',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: (value) => value
        },
        {
            title: '工作流实例id',
            dataIndex: 'wfInstanceId',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: (value) => value
        },
        {
            title: '项目状态',
            dataIndex: 'status',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: (value) => value
        },
        {
            title: '业务编号',
            dataIndex: 'code',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: (value) => value
        },
        {
            title: '客户名称',
            dataIndex: 'custName',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: (value) => value
        },
        {
            title: '客户性质 取字典值',
            dataIndex: 'custNature',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: (value) => value
        },
        {
            title: '客户证件号类别 取字典值',
            dataIndex: 'custCertType',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: (value) => value
        },
        {
            title: '证件号码',
            dataIndex: 'custCertNo',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: (value) => value
        },
        {
            title: '联系电话',
            dataIndex: 'custLinkTel',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: (value) => value
        },
        {
            title: '客户邮编',
            dataIndex: 'custPostCode',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: (value) => value
        },
        {
            title: '客户邮件',
            dataIndex: 'custEmail',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: (value) => value
        },
        {
            title: '法人姓名',
            dataIndex: 'legalName',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: (value) => value
        },
        {
            title: '法人联系方式',
            dataIndex: 'legalLinkTel',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: (value) => value
        },
        {
            title: '法人身份证号',
            dataIndex: 'legalCertNo',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: (value) => value
        },
        {
            title: '所属行业(国家)',
            dataIndex: 'industCategNation',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: (value) => value
        },
        {
            title: '所属行业(工业)',
            dataIndex: 'industCateg',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: (value) => value
        },
        {
            title: '从业人数(上一年末)',
            dataIndex: 'workNumLastYear',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: (value) => value
        },
        {
            title: '上一年末资产总额',
            dataIndex: 'assetAmtLastYear',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: (value) => value
        },
        {
            title: '营业额',
            dataIndex: 'incomeAmt',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: (value) => value
        },
        {
            title: '纳税额',
            dataIndex: 'taxAmt',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: (value) => value
        },
        {
            title: '企业划型，大中小微等',
            dataIndex: 'compType',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: (value) => value
        },
        {
            title: '政策扶持领域类别(服务对象类型)',
            dataIndex: 'policySupportFieldType',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: (value) => value
        },
        {
            title: '存续状态',
            dataIndex: 'standingStatus',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: (value) => value
        },
        {
            title: '登记地址',
            dataIndex: 'regAddr',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: (value) => value
        },
        {
            title: '省',
            dataIndex: 'provinceName',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: (value) => value
        },
        {
            title: '市',
            dataIndex: 'cityName',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: (value) => value
        },
        {
            title: '县/区',
            dataIndex: 'countyName',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: (value) => value
        },
        {
            title: '上报批次',
            dataIndex: 'reportBatch',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: (value) => value
        },
        {
            title: '债务人名称',
            dataIndex: 'debtorName',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: (value) => value
        },
        {
            title: '业务品种 取字典',
            dataIndex: 'serviceCateg',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: (value) => value
        },
        {
            title: '融资用途 取字典',
            dataIndex: 'loanUse',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: (value) => value
        },
        {
            title: '是否首次贷款',
            dataIndex: 'isFirstLoan',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: (value) => value
        },
        {
            title: '分支行',
            dataIndex: 'branchBank',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: (value) => value
        },
        {
            title: '主债权金额',
            dataIndex: 'mainDebtAmt',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: (value) => value
        },
        {
            title: '借款合同号',
            dataIndex: 'loanContNo',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: (value) => value
        },
        {
            title: '主债权起始日期',
            dataIndex: 'mainDebtStartDate',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: toDateTime,
        },
        {
            title: '主债权到期日',
            dataIndex: 'mainDeptEndDate',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: toDateTime,
        },
        {
            title: '贷款利率',
            dataIndex: 'loanRate',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: (value) => value
        },
        {
            title: '保证合同号',
            dataIndex: 'guarContNo',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: (value) => value
        },
        {
            title: '委保合同号',
            dataIndex: 'securContNo',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: (value) => value
        },
        {
            title: '担保费率',
            dataIndex: 'guarRate',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: (value) => value
        },
        {
            title: '反担保措施',
            dataIndex: 'protectGuarMethod',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: (value) => value
        },
        {
            title: '原担保机构名称',
            dataIndex: 'originGuarOrgName',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: (value) => value
        },
        {
            title: '原担保机构证件号码',
            dataIndex: 'originGuarOrgCertNo',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: (value) => value
        },
        {
            title: '市再担机构名称',
            dataIndex: 'cityReguarOrgName',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: (value) => value
        },
        {
            title: '市再担机构证件号码',
            dataIndex: 'cityReguarOrgCertNo',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: (value) => value
        },
        {
            title: '省再担机构名称',
            dataIndex: 'provReguarOrgName',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: (value) => value
        },
        {
            title: '省再担机构证件号码',
            dataIndex: 'provReguarOrgCertNo',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: (value) => value
        },
        {
            title: '[再]担保期限(月)',
            dataIndex: 'reguarPeriod',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: (value) => value
        },
        {
            title: '[再]担保费率(省级)(年)',
            dataIndex: 'reguarRateProv',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: (value) => value
        },
        {
            title: '[再]合计担保费(省级)(元)',
            dataIndex: 'reguarAmtProvSum',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: (value) => value
        },
        {
            title: '[再]本次担保费(省级)(元)',
            dataIndex: 'reguarAmtProvCur',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: (value) => value
        },
        {
            title: '[再]担保费率(国担)(年)',
            dataIndex: 'reguarAmtRateNation',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: (value) => value
        },
        {
            title: '[再]合计担保费(国担)(元)',
            dataIndex: 'reguarAmtNationSum',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: (value) => value
        },
        {
            title: '[再]本次担保费(国担)(元)',
            dataIndex: 'reguarAmtNationCur',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: (value) => value
        },
        {
            title: '分险比例（债权人）',
            dataIndex: 'riskRateDebtee',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: (value) => value
        },
        {
            title: '分险比例（原担保）',
            dataIndex: 'riskRateOriginGuar',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: (value) => value
        },
        {
            title: '分险比例(市级)(再担保)',
            dataIndex: 'riskRateCityReguar',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: (value) => value
        },
        {
            title: '分险比例(省级)(再担保）',
            dataIndex: 'riskRateProvinceReguar',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: (value) => value
        },
        {
            title: '分险比例（其他）',
            dataIndex: 'riskRateOther',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: (value) => value
        },
        {
            title: '分险比例（国担）',
            dataIndex: 'riskRateNation',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: (value) => value
        },
        {
            title: '分险比例（工信部）',
            dataIndex: 'riskRateTechInfo',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: (value) => value
        },
        {
            title: '第N次备案',
            dataIndex: 'regCnt',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: (value) => value
        },
        {
            title: '创建时间',
            dataIndex: 'createTime',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: toDateTime,
        },
        {
            title: '修改时间',
            dataIndex: 'updateTime',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: toDateTime,
        },
        {
            title: '创建人',
            dataIndex: 'creator',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: (value) => value
        },
        {
            title: '修改人',
            dataIndex: 'updator',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: (value) => value
        },
        {
            title: '删除标记',
            dataIndex: 'delFlag',
            ellipsis: false, // 超过宽度将自动省略
            align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
            width: '200px',
            customRender: (value) => value
        },
        {
            title: '备注',
            dataIndex: 'remark',
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
                // this.$router.push(param)
            },
            /** 查看详情 */
            handleDetail(record) {
                this.detailVisible = true
                this.mdl = {...record}
            },
            /** 单条业务删除 */
            handleDelete(record) {
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
