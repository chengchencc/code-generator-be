
<template>
    <page-header-wrapper>
                <a-card :bordered="false">
                    <!-- 搜索过滤区域 -->
            <div class="table-page-search-wrapper">
                <a-form layout="inline">
                    <a-row :gutter="48">
                            <a-col :md="8" :sm="24">
                            <a-form-item label="业务编号">
                                        <a-input v-model="queryParam.code"  />
                            </a-form-item>
                            </a-col>
                            <a-col :md="8" :sm="24">
                            <a-form-item label="客户名称">
                                        <a-input v-model="queryParam.custName"  />
                            </a-form-item>
                            </a-col>
                            <a-col :md="8" :sm="24">
                            <a-form-item label="客户性质 取字典值">
                                        <a-select v-model="queryParam.custNature" >
                                            <a-select-option value="">请选择</a-select-option>
                                            <a-select-option v-for="(item, name) in pageDict.custNature" :key="name" :value="item.code">
                                                {{ item.value }}
                                            </a-select-option>
                                        </a-select>
                            </a-form-item>
                            </a-col>
                            <a-col :md="8" :sm="24">
                            <a-form-item label="客户证件号类别 取字典值">
                                        <a-select v-model="queryParam.custCertType" >
                                            <a-select-option value="">请选择</a-select-option>
                                            <a-select-option v-for="(item, name) in pageDict.custCertType" :key="name" :value="item.code">
                                                {{ item.value }}
                                            </a-select-option>
                                        </a-select>
                            </a-form-item>
                            </a-col>
                            <a-col :md="8" :sm="24">
                            <a-form-item label="证件号码">
                                        <a-input v-model="queryParam.custCertNo"  />
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
                         :scroll="{x: 1050}"
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
    import { getNameByDict } from '@/utils/dealData'
    import { dictMixin } from '@/store/dict-mixin'
    import { TablePageMixin } from '@/core/mixins/TablePageMixin2'
    import ModalForm from './components/RegistrationModal' // 切换到抽屉模式 引用改为 './drawer.vue'
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
                       title: '雪花算法生成的递增主键',
                       dataIndex: 'id',
                       ellipsis: false, // 超过宽度将自动省略
                       align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                       width: '180px',
                       customRender: (value) => value
                   },
                   {
                       title: '工作流nodeId',
                       dataIndex: 'wfNodeId',
                       ellipsis: false, // 超过宽度将自动省略
                       align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                       width: '180px',
                       customRender: (value) => value
                   },
                   {
                       title: '工作流实例id',
                       dataIndex: 'wfInstanceId',
                       ellipsis: false, // 超过宽度将自动省略
                       align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                       width: '180px',
                       customRender: (value) => value
                   },
                   {
                       title: '项目状态',
                       dataIndex: 'status',
                       ellipsis: false, // 超过宽度将自动省略
                       align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                       width: '180px',
                       customRender: (value) => value
                   },
                   {
                       title: '业务编号',
                       dataIndex: 'code',
                       ellipsis: false, // 超过宽度将自动省略
                       align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                       width: '180px',
                       customRender: (value) => value
                   },
                   {
                       title: '客户名称',
                       dataIndex: 'custName',
                       ellipsis: false, // 超过宽度将自动省略
                       align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                       width: '180px',
                       customRender: (value) => value
                   },
                   {
                       title: '客户性质 取字典值',
                       dataIndex: 'custNature',
                       ellipsis: false, // 超过宽度将自动省略
                       align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                       width: '180px',
                       customRender: (value) => {
                          return getNameByDict(value, this.pageDict.custNature || [])
                       }
                   },
                   {
                       title: '客户证件号类别 取字典值',
                       dataIndex: 'custCertType',
                       ellipsis: false, // 超过宽度将自动省略
                       align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                       width: '180px',
                       customRender: (value) => {
                          return getNameByDict(value, this.pageDict.custCertType || [])
                       }
                   },
                   {
                       title: '证件号码',
                       dataIndex: 'custCertNo',
                       ellipsis: false, // 超过宽度将自动省略
                       align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                       width: '180px',
                       customRender: (value) => value
                   },
                   {
                       title: '联系电话',
                       dataIndex: 'custLinkTel',
                       ellipsis: false, // 超过宽度将自动省略
                       align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                       width: '180px',
                       customRender: (value) => value
                   },
                   {
                       title: '客户邮编',
                       dataIndex: 'custPostCode',
                       ellipsis: false, // 超过宽度将自动省略
                       align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                       width: '180px',
                       customRender: (value) => value
                   },
                   {
                       title: '客户邮件',
                       dataIndex: 'custEmail',
                       ellipsis: false, // 超过宽度将自动省略
                       align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                       width: '180px',
                       customRender: (value) => value
                   },
                   {
                       title: '法人姓名',
                       dataIndex: 'legalName',
                       ellipsis: false, // 超过宽度将自动省略
                       align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                       width: '180px',
                       customRender: (value) => value
                   },
                   {
                       title: '法人联系方式',
                       dataIndex: 'legalLinkTel',
                       ellipsis: false, // 超过宽度将自动省略
                       align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                       width: '180px',
                       customRender: (value) => value
                   },
                   {
                       title: '法人身份证号',
                       dataIndex: 'legalCertNo',
                       ellipsis: false, // 超过宽度将自动省略
                       align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                       width: '180px',
                       customRender: (value) => value
                   },
                   {
                       title: '所属行业(国家)',
                       dataIndex: 'industCategNation',
                       ellipsis: false, // 超过宽度将自动省略
                       align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                       width: '180px',
                       customRender: (value) => value
                   },
                   {
                       title: '所属行业(工业)',
                       dataIndex: 'industCateg',
                       ellipsis: false, // 超过宽度将自动省略
                       align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                       width: '180px',
                       customRender: (value) => value
                   },
                   {
                       title: '从业人数(上一年末)',
                       dataIndex: 'workNumLastYear',
                       ellipsis: false, // 超过宽度将自动省略
                       align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                       width: '180px',
                       customRender: (value) => value
                   },
                   {
                       title: '上一年末资产总额',
                       dataIndex: 'assetAmtLastYear',
                       ellipsis: false, // 超过宽度将自动省略
                       align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                       width: '180px',
                       customRender: (value) => value
                   },
                   {
                       title: '营业额',
                       dataIndex: 'incomeAmt',
                       ellipsis: false, // 超过宽度将自动省略
                       align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                       width: '180px',
                       customRender: (value) => value
                   },
                   {
                       title: '纳税额',
                       dataIndex: 'taxAmt',
                       ellipsis: false, // 超过宽度将自动省略
                       align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                       width: '180px',
                       customRender: (value) => value
                   },
                   {
                       title: '企业划型，大中小微等',
                       dataIndex: 'compType',
                       ellipsis: false, // 超过宽度将自动省略
                       align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                       width: '180px',
                       customRender: (value) => value
                   },
                   {
                       title: '政策扶持领域类别(服务对象类型)',
                       dataIndex: 'policySupportFieldType',
                       ellipsis: false, // 超过宽度将自动省略
                       align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                       width: '180px',
                       customRender: (value) => value
                   },
                   {
                       title: '存续状态',
                       dataIndex: 'standingStatus',
                       ellipsis: false, // 超过宽度将自动省略
                       align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                       width: '180px',
                       customRender: (value) => value
                   },
                   {
                       title: '登记地址',
                       dataIndex: 'regAddr',
                       ellipsis: false, // 超过宽度将自动省略
                       align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                       width: '180px',
                       customRender: (value) => value
                   },
                   {
                       title: '省',
                       dataIndex: 'provinceName',
                       ellipsis: false, // 超过宽度将自动省略
                       align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                       width: '180px',
                       customRender: (value) => value
                   },
                   {
                       title: '市',
                       dataIndex: 'cityName',
                       ellipsis: false, // 超过宽度将自动省略
                       align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                       width: '180px',
                       customRender: (value) => value
                   },
                   {
                       title: '县/区',
                       dataIndex: 'countyName',
                       ellipsis: false, // 超过宽度将自动省略
                       align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                       width: '180px',
                       customRender: (value) => value
                   },
                   {
                       title: '上报批次',
                       dataIndex: 'reportBatch',
                       ellipsis: false, // 超过宽度将自动省略
                       align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                       width: '180px',
                       customRender: (value) => value
                   },
                   {
                       title: '债务人名称',
                       dataIndex: 'debtorName',
                       ellipsis: false, // 超过宽度将自动省略
                       align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                       width: '180px',
                       customRender: (value) => value
                   },
                   {
                       title: '业务品种 取字典',
                       dataIndex: 'serviceCateg',
                       ellipsis: false, // 超过宽度将自动省略
                       align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                       width: '180px',
                       customRender: (value) => {
                          return getNameByDict(value, this.pageDict.serviceCateg || [])
                       }
                   },
                   {
                       title: '融资用途 取字典',
                       dataIndex: 'loanUse',
                       ellipsis: false, // 超过宽度将自动省略
                       align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                       width: '180px',
                       customRender: (value) => {
                          return getNameByDict(value, this.pageDict.loanUse || [])
                       }
                   },
                   {
                       title: '是否首次贷款',
                       dataIndex: 'isFirstLoan',
                       ellipsis: false, // 超过宽度将自动省略
                       align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                       width: '180px',
                       customRender: (value) => value
                   },
                   {
                       title: '分支行',
                       dataIndex: 'branchBank',
                       ellipsis: false, // 超过宽度将自动省略
                       align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                       width: '180px',
                       customRender: (value) => value
                   },
                   {
                       title: '主债权金额',
                       dataIndex: 'mainDebtAmt',
                       ellipsis: false, // 超过宽度将自动省略
                       align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                       width: '180px',
                       customRender: (value) => value
                   },
                   {
                       title: '借款合同号',
                       dataIndex: 'loanContNo',
                       ellipsis: false, // 超过宽度将自动省略
                       align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                       width: '180px',
                       customRender: (value) => value
                   },
                   {
                       title: '主债权起始日期',
                       dataIndex: 'mainDebtStartDate',
                       ellipsis: false, // 超过宽度将自动省略
                       align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                       width: '180px',
                       customRender: toDateTime,
                   },
                   {
                       title: '主债权到期日',
                       dataIndex: 'mainDeptEndDate',
                       ellipsis: false, // 超过宽度将自动省略
                       align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                       width: '180px',
                       customRender: toDateTime,
                   },
                   {
                       title: '贷款利率',
                       dataIndex: 'loanRate',
                       ellipsis: false, // 超过宽度将自动省略
                       align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                       width: '180px',
                       customRender: (value) => value
                   },
                   {
                       title: '保证合同号',
                       dataIndex: 'guarContNo',
                       ellipsis: false, // 超过宽度将自动省略
                       align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                       width: '180px',
                       customRender: (value) => value
                   },
                   {
                       title: '委保合同号',
                       dataIndex: 'securContNo',
                       ellipsis: false, // 超过宽度将自动省略
                       align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                       width: '180px',
                       customRender: (value) => value
                   },
                   {
                       title: '担保费率',
                       dataIndex: 'guarRate',
                       ellipsis: false, // 超过宽度将自动省略
                       align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                       width: '180px',
                       customRender: (value) => value
                   },
                   {
                       title: '反担保措施',
                       dataIndex: 'protectGuarMethod',
                       ellipsis: false, // 超过宽度将自动省略
                       align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                       width: '180px',
                       customRender: (value) => value
                   },
                   {
                       title: '原担保机构名称',
                       dataIndex: 'originGuarOrgName',
                       ellipsis: false, // 超过宽度将自动省略
                       align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                       width: '180px',
                       customRender: (value) => value
                   },
                   {
                       title: '原担保机构证件号码',
                       dataIndex: 'originGuarOrgCertNo',
                       ellipsis: false, // 超过宽度将自动省略
                       align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                       width: '180px',
                       customRender: (value) => value
                   },
                   {
                       title: '市再担机构名称',
                       dataIndex: 'cityReguarOrgName',
                       ellipsis: false, // 超过宽度将自动省略
                       align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                       width: '180px',
                       customRender: (value) => value
                   },
                   {
                       title: '市再担机构证件号码',
                       dataIndex: 'cityReguarOrgCertNo',
                       ellipsis: false, // 超过宽度将自动省略
                       align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                       width: '180px',
                       customRender: (value) => value
                   },
                   {
                       title: '省再担机构名称',
                       dataIndex: 'provReguarOrgName',
                       ellipsis: false, // 超过宽度将自动省略
                       align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                       width: '180px',
                       customRender: (value) => value
                   },
                   {
                       title: '省再担机构证件号码',
                       dataIndex: 'provReguarOrgCertNo',
                       ellipsis: false, // 超过宽度将自动省略
                       align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                       width: '180px',
                       customRender: (value) => value
                   },
                   {
                       title: '[再]担保期限(月)',
                       dataIndex: 'reguarPeriod',
                       ellipsis: false, // 超过宽度将自动省略
                       align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                       width: '180px',
                       customRender: (value) => value
                   },
                   {
                       title: '[再]担保费率(省级)(年)',
                       dataIndex: 'reguarRateProv',
                       ellipsis: false, // 超过宽度将自动省略
                       align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                       width: '180px',
                       customRender: (value) => value
                   },
                   {
                       title: '[再]合计担保费(省级)(元)',
                       dataIndex: 'reguarAmtProvSum',
                       ellipsis: false, // 超过宽度将自动省略
                       align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                       width: '180px',
                       customRender: (value) => value
                   },
                   {
                       title: '[再]本次担保费(省级)(元)',
                       dataIndex: 'reguarAmtProvCur',
                       ellipsis: false, // 超过宽度将自动省略
                       align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                       width: '180px',
                       customRender: (value) => value
                   },
                   {
                       title: '[再]担保费率(国担)(年)',
                       dataIndex: 'reguarAmtRateNation',
                       ellipsis: false, // 超过宽度将自动省略
                       align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                       width: '180px',
                       customRender: (value) => value
                   },
                   {
                       title: '[再]合计担保费(国担)(元)',
                       dataIndex: 'reguarAmtNationSum',
                       ellipsis: false, // 超过宽度将自动省略
                       align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                       width: '180px',
                       customRender: (value) => value
                   },
                   {
                       title: '[再]本次担保费(国担)(元)',
                       dataIndex: 'reguarAmtNationCur',
                       ellipsis: false, // 超过宽度将自动省略
                       align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                       width: '180px',
                       customRender: (value) => value
                   },
                   {
                       title: '分险比例（债权人）',
                       dataIndex: 'riskRateDebtee',
                       ellipsis: false, // 超过宽度将自动省略
                       align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                       width: '180px',
                       customRender: (value) => value
                   },
                   {
                       title: '分险比例（原担保）',
                       dataIndex: 'riskRateOriginGuar',
                       ellipsis: false, // 超过宽度将自动省略
                       align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                       width: '180px',
                       customRender: (value) => value
                   },
                   {
                       title: '分险比例(市级)(再担保)',
                       dataIndex: 'riskRateCityReguar',
                       ellipsis: false, // 超过宽度将自动省略
                       align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                       width: '180px',
                       customRender: (value) => value
                   },
                   {
                       title: '分险比例(省级)(再担保）',
                       dataIndex: 'riskRateProvinceReguar',
                       ellipsis: false, // 超过宽度将自动省略
                       align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                       width: '180px',
                       customRender: (value) => value
                   },
                   {
                       title: '分险比例（其他）',
                       dataIndex: 'riskRateOther',
                       ellipsis: false, // 超过宽度将自动省略
                       align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                       width: '180px',
                       customRender: (value) => value
                   },
                   {
                       title: '分险比例（国担）',
                       dataIndex: 'riskRateNation',
                       ellipsis: false, // 超过宽度将自动省略
                       align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                       width: '180px',
                       customRender: (value) => value
                   },
                   {
                       title: '分险比例（工信部）',
                       dataIndex: 'riskRateTechInfo',
                       ellipsis: false, // 超过宽度将自动省略
                       align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                       width: '180px',
                       customRender: (value) => value
                   },
                   {
                       title: '第N次备案',
                       dataIndex: 'regCnt',
                       ellipsis: false, // 超过宽度将自动省略
                       align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                       width: '180px',
                       customRender: (value) => value
                   },
                   {
                       title: '创建时间',
                       dataIndex: 'createTime',
                       ellipsis: false, // 超过宽度将自动省略
                       align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                       width: '180px',
                       customRender: toDateTime,
                   },
                   {
                       title: '修改时间',
                       dataIndex: 'updateTime',
                       ellipsis: false, // 超过宽度将自动省略
                       align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                       width: '180px',
                       customRender: toDateTime,
                   },
                   {
                       title: '创建人',
                       dataIndex: 'creator',
                       ellipsis: false, // 超过宽度将自动省略
                       align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                       width: '180px',
                       customRender: (value) => value
                   },
                   {
                       title: '修改人',
                       dataIndex: 'updator',
                       ellipsis: false, // 超过宽度将自动省略
                       align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                       width: '180px',
                       customRender: (value) => value
                   },
                   {
                       title: '删除标记',
                       dataIndex: 'delFlag',
                       ellipsis: false, // 超过宽度将自动省略
                       align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                       width: '180px',
                       customRender: (value) => value
                   },
                   {
                       title: '备注',
                       dataIndex: 'remark',
                       ellipsis: false, // 超过宽度将自动省略
                       align: 'left', // 设置列内容的对齐方式 'left' | 'right' | 'center'
                       width: '180px',
                       customRender: (value) => value
                   },
                   {
                       title: '操作',
                       dataIndex: 'action',
                       width: '200px',
                       fixed: 'right',
                       scopedSlots: {customRender: 'action'}
                   }
                ],
                //页面级字典
                pageDict: {},
                url: {
                    list: '/api-sample/Registration/list',
                    delete: '/api-sample/Registration/delete',
                    deleteBatch: '/api-sample/Registration/deleteBatch',
                    exportXlsUrl: '/api-sample/Registration/exportXlsx',
                    importExcelUrl: '/api-sample/Registration/importExcel'
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
                    'custNature',
                    'custCertType',
                ]
                getDictionaryByCodes(dictCodes).then((res) => {
                    this.pageDict = res
                })
            }
        }
    }
</script>
