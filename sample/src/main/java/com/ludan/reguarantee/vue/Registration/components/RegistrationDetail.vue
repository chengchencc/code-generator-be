<template>
    <page-header-wrapper
            title="单号：234231029431"
            :tab-list="tabList"
            :tab-active-key="tabActiveKey"
            @tabChange="handleTabChange"
    >
        <!--
        <template v-slot:content>
            <a-descriptions size="small" :column="isMobile ? 1 : 2">
                <a-descriptions-item label="创建人">曲丽丽</a-descriptions-item>
                <a-descriptions-item label="订购产品">XX 服务</a-descriptions-item>
                <a-descriptions-item label="创建时间">2017-07-07</a-descriptions-item>
                <a-descriptions-item label="关联单据">
                    <a href="">12421</a>
                </a-descriptions-item>
                <a-descriptions-item label="生效日期">2017-07-07 ~ 2017-08-08</a-descriptions-item>
                <a-descriptions-item label="备注">请于两个工作日内确认</a-descriptions-item>
            </a-descriptions>
        </template>
        -->

        <!-- actions -->
        <template v-slot:extra>
            <a-button-group style="margin-right: 4px;">
                <a-button>操作</a-button>
                <a-button>操作</a-button>
                <a-button><a-icon type="ellipsis"/></a-button>
            </a-button-group>
            <a-button type="primary" >主操作</a-button>
        </template>

        <!--
        <template v-slot:extraContent>
            <a-row class="status-list">
                <a-col :xs="12" :sm="12">
                    <div class="text">状态</div>
                    <div class="heading">待审批</div>
                </a-col>
                <a-col :xs="12" :sm="12">
                    <div class="text">订单金额</div>
                    <div class="heading">¥ 568.08</div>
                </a-col>
            </a-row>
        </template>
        -->

        <a-card :bordered="true" v-if="tabActiveKey === 'detail'">
          <div>
            <real-form ref="realForm" @ok="submitCallback"></real-form>
            <!-- v-if="tabInfo ? tabInfo.editable : false" -->
            <div style="display: flex; justify-content: center">
              <a-button type="primary" @click="submit" :loading="saveButtonLoading">保存</a-button>
            </div>
          </div>
        </a-card>

        <a-card style="margin-top: 24px" :bordered="false" title="用户信息" v-if="tabActiveKey === 'rule'">
            <a-descriptions>
               待定
            </a-descriptions>
        </a-card>

    </page-header-wrapper>
</template>

/**
 * 详情页
 */
<script>
    import { baseMixin } from '@/store/app-mixin'
    import RealForm from './RegistrationForm'
    import { httpGet, httpPost, httpDelete, httpPut, downFile } from '@/utils/httpClient'

    export default {
        name: 'Advanced',
        mixins: [baseMixin],
        components: { RealForm },
        props: {
          projectId: {
            type: String,
            default: '',
          },
          tabInfo: {
            type: Object,
            default: () => {},
          },
          nodeId: {
            type: String,
            default: () => 'projectDetail',
          },
        },
        data () {
            return {
                tabList: [
                    { key: 'detail', tab: '详情' },
                    { key: 'rule', tab: '规则' }
                ],
                tabActiveKey: 'detail',
                form: this.$form.createForm(this),
                saveButtonLoading: false,
                colInfo8: {
                  labelCol: { span: 9 },
                  wrapperCol: { span: 12 },
                },
                colInfo24: {
                  labelCol: { span: 3 },
                  wrapperCol: { span: 20 },
                },
                url: {
                  detail: "/api-sample/GeneratorRule/detail"
                }

            }
        },
        filters: {
            statusFilter (status) {
                const statusMap = {
                    'agree': '成功',
                    'reject': '驳回'
                }
                return statusMap[status]
            },
            statusTypeFilter (type) {
                const statusTypeMap = {
                    'agree': 'success',
                    'reject': 'error'
                }
                return statusTypeMap[type]
            }
        },
        mounted() {
          setTimeout(() => {
            this.getInitData();
          }, 1)
        },
        computed: {
          unEditable() {
            if (!this.tabInfo) {
              return true
            }
            return !this.tabInfo.editable
          },
        },
        methods: {
          getInitData() {
            httpGet(this.url.detail, {id: this.$route.params.taskId}).then((res) => {
              if (res.code === 0) {
                this.$refs.realForm.edit(res.datas)
              }
            }).catch((e)=>{});
          },
          edit (record) {
              this.$nextTick(() => {
                  this.$refs.realForm.edit(record)
              })
          },
          submit() {
            this.$refs.realForm.submit()
          },
          submitCallback () {
              console.log("保存成功了。。。")
          },
          handleTabChange (key) {
              this.tabActiveKey = key
          }
        }
    }
</script>

<style lang="less" scoped>

    .detail-layout {
        margin-left: 44px;
    }
    .text {
        color: rgba(0, 0, 0, .45);
    }

    .heading {
        color: rgba(0, 0, 0, .85);
        font-size: 20px;
    }

    .no-data {
        color: rgba(0, 0, 0, .25);
        text-align: center;
        line-height: 64px;
        font-size: 16px;

    i {
        font-size: 24px;
        margin-right: 16px;
        position: relative;
        top: 3px;
    }
    }

    .mobile {
    .detail-layout {
        margin-left: unset;
    }
    .text {

    }
    .status-list {
        text-align: left;
    }
    }
</style>


