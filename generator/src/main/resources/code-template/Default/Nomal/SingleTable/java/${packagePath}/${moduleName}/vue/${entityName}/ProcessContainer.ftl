<template>
  <page-header-wrapper
    :title="'单号：' + ( this.$route.params.taskId || '')"
    :tab-list="tabList"
    :tab-active-key="tabActiveKey"
    @tabChange="handleTabChange"
  >
    <!-- 顶部按钮部分 -->
     <!-- <template v-slot:extra>
            <a-button-group style="margin-right: 4px;">
                <a-button>操作</a-button>
                <a-button>操作</a-button>
                <a-button><a-icon type="ellipsis"/></a-button>
            </a-button-group>
            <a-button type="primary" >主操作</a-button>
        </template> -->
    <template slot="extra">
      <a-space v-if="nodeId == 'basq' && editable == 'true'">
        <!-- <a-button  v-for="item in formSchema.functions" :key="item.action" type="primary" @click="handleClick(item)" style="margin-left: 10px">
          {{ item.title }}
        </a-button> -->
        <a-button v-for="item in btns.basq" :key="item.action" type="primary" @click="handleClick(item)"
          style="margin-left: 10px">
          {{ item.title }}
        </a-button>
      </a-space>

      <a-space v-if="nodeId == 'zdqr' && editable == 'true'">
        <a-button v-for="item in btns.zdqr" :key="item.action" type="primary" @click="handleClick(item)"
          style="margin-left: 10px">
          {{ item.title }}
        </a-button>
      </a-space>

      <a-space v-if="nodeId == 'gdqr' && editable == 'true'">
        <a-button v-for="item in btns.gdqr" :key="item.action" type="primary" @click="handleClick(item)"
          style="margin-left: 10px">
          {{ item.title }}
        </a-button>
      </a-space>

      <a-space v-if="editable == 'false'">
        <a-button key="goBack" type="primary" @click="handleClick({
            title: '返回上页',
            type: 'goBack',
            action: 'goBack',
          })"
          style="margin-left: 10px"
        >
          返回上页
        </a-button>
      </a-space>
    </template>

 <!-- 顶部基本信息 -->
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

 <!-- 顶部右侧按钮+信息 -->
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

<script>
import CardIcon from '@/components/CardIcon/CardIcon'
import { getProjectById, getProjectSJQY, getGuarCount, getRiskCheckByPid } from '@/api/form'
import { submitProject } from '@/api/workflow'
import { mapActions, mapState, mapMutations } from 'vuex'
import { deepCopy } from '@/utils/util'
import formMixin from '@/core/mixins/form-mixin'
//import RegistrationDetail from './components/RegistrationDetail'
 import { baseMixin } from '@/store/app-mixin'
import RealForm from './components/RegistrationForm'
import { httpGet, httpPost, httpDelete, httpPut, downFile } from '@/utils/httpClient'

export default {
  name: 'ProcessContainer',
  mixins: [baseMixin, formMixin],
  components: {
    CardIcon,
    RealForm
    //RegistrationDetail
  },
  data () {
    return {
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
        detail: "/api-sample/${entityName}/detail/"
      },
      tabList: [
          { key: 'detail', tab: '详情' },
          /* { key: 'rule', tab: '规则' } */
      ],
      tabActiveKey: 'detail',
      projectMain: {},
      riskCheckData: {},
      businessMapId: '',
      projectMainLoaded: false,
      btns: {
        basq: [
          {
            title: '提报',
            type: 'apply',
            action: 'apply',
          },
          {
            title: '返回上页',
            type: 'goBack',
            action: 'goBack',
          },
        ],
        zdqr: [
          {
            title: '受理',
            type: 'complete',
            action: 'complete',
          },
          {
            title: '提报国担',
            type: 'completegd',
            action: 'completegd',
          },
          {
            title: '退回',
            type: 'reback',
            action: 'reback',
          },
          {
            title: '终止',
            type: 'end',
            action: 'end',
          },
          {
            title: '返回上页',
            type: 'goBack',
            action: 'goBack',
          },
        ],
        gdqr: [
          {
            title: '提交',
            type: 'complete',
            action: 'complete',
          },
          {
            title: '退回',
            type: 'reback',
            action: 'reback',
          },
          {
            title: '返回上页',
            type: 'goBack',
            action: 'goBack',
          },
        ],
      },
    }
  },
  props: {
    editable: {
      type: String,
      default: () => 'true',
    },
    nodeId: {
      type: String,
      default: () => 'basq',
    },
    projectId: {
      type: String,
      default: () => '',
    },
    taskId: {
      type: String,
      default: () => 'taskId',
    },
    processKey: {
      type: String,
      default: '',
    },
    businessId: {
      type: String,
      default: '',
    },
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
  mounted () {


    /* <a-menu v-model="formSchema.currentTabKey">
          <a-menu-item :key="tab.tabKey" v-for="tab in formSchema.tabs">
            <card-icon :editable="tab.editable" :complete="tab.complete" :type="tab.type" />
            {{ tab.tabName }}
          </a-menu-item>
        </a-menu> */
        this.getTabList();

    if (this.nodeId == 'sjqy') {
      getProjectSJQY(this.projectId).then((res) => {
        if (res && res.datas) {
          this.projectMain = res.datas
          delete this.projectMain['updateName']
          delete this.projectMain['updateTime']
          delete this.projectMain['updator']
        }
        this.projectMainLoaded = true
      })
    } else {
      // localStorage.setItem('business_type', 'busUnStart')
      this.getProjectById()
      this.getRiskCheck()
    }
  //  this.getButtonAndTab({
  //    nodeId: this.nodeId,
  //    taskId: this.taskId,
  //    processKey: this.processKey,
  //    roles: this.roles,
  //    // queryId: this.processKey.indexOf('guaranteev2') !== -1 ? this.projectId : this.businessId || this.businessMapId
  //    queryId: this.businessId || this.businessMapId
  //  })

    this.getInitData();

  },
  updated () {
    console.log('更新了')
  },
  computed: {
    unEditable() {
      if (!this.tabInfo) {
        return true
      }
      return !this.tabInfo.editable
    },
    currentTab () {
      return this.formSchema.tabs.find((item) => item.tabKey === this.formSchema.currentTabKey[0])
    },
    ...mapState({
      formSchema: (state) => state.form.formSchema,
      roles: (state) => state.user.roles
    })
  },
  methods: {
    getTabList() {
      console.log(" mounted ------ ")
      console.log(this.$store.state.form.formSchema)

      this.tabList = [{ key: 'detail', tab: '详情' }]
    },
    getInitData() {
      httpGet(this.url.detail+this.$route.params.taskId).then((res) => {
        if (res.resp_code === 0) {
          this.$refs.realForm.edit(res.datas)
        }
      }).catch((e)=>{
        console.log(e)
      });
    },
    edit (record) {
        this.visible = true
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
        console.log('')
        this.tabActiveKey = key
    },
    handleTabStatusChange (tab, complete) {
      this.formSchema.tabs.find((item) => item.tabKey === tab)['complete'] = complete
      this.updateFormSchema(deepCopy(this.formSchema))
    },
    handleClick (item) {
      console.log('触发按钮动作：' + item.type)
      this.projectMain['taskId'] = this.taskId
      this.projectMain['nodeId'] = this.nodeId
      this.projectMain['processKey'] = this.processKey
      if ((item.type == 'submit' || item.type == 'apply') && item.action != 'bczl' && item.action != 'sglzx' && !this.checkAllTabs()) {
        return
      }
      if (item.type === 'apply') {
        submitProject(
          this.businessId || this.businessMapId,
          // this.processKey.indexOf('guarantee-') !== -1 ? this.businessMapId || this.businessId : this.projectMain.id,
          this.taskId,
          this.processKey
        ).then((res) => {
          this.$message.success(res.resp_msg)
          this.$router.push('/taskcenter/todo')
        })
      } else if (item.type === 'goBack') {
        this.$router.go(-1)
      } else if (item.type === 'cancel') {
        this.$router.go(-1) // 返回上一层
      }
      // if (this.processKey.indexOf('guarantee-') !== -1) {
        // this.projectMain.id = this.businessId
      // }
      this.projectMain.id = this.businessId || this.businessMapId
      this.$refs.buttonClick.handleClick(item, this.projectMain, this.businessId)
    },
    getbusid (item) {
      this.businessMapId = item
    },
    checkAllTabs () {
      if (this.formSchema.tabs.some((tab) => tab.type == 'normal' && tab.editable && !tab.complete)) {
        // 有tab页面没保存
        this.$message.error('未保存所有标签')
        return false
      }
      return true
    },
    getProjectById () {
      getProjectById(this.projectId).then((res) => {
        if (res && res.datas) {
          this.projectMain = res.datas
          const params = {
            custIdentityNo: this.projectMain.custIdentityNo,
            code: this.projectMain.code
          }
          getGuarCount(params).then((response) => {
            this.projectMain['count'] = response
          })
          delete this.projectMain['updateName']
          delete this.projectMain['updateTime']
          delete this.projectMain['updator']
        }
        this.projectMainLoaded = true
      })
    },
    getRiskCheck () {
      getRiskCheckByPid(this.businessId).then((res) => {
        if (res.datas) {
          this.riskCheckData = res.datas[0]
        }
      })
    },
    ...mapActions(['updateFormSchema', 'getButtonAndTab']),
    ...mapMutations(['updateFormSchema'])
  },
  destroyed () {
    this.updateFormSchema({})
  }
}
</script>

<style lang="less">
.process-form-container {
  display: flex;
  justify-content: space-between;
  height: calc(100vh - 145px);
  .ant-menu-root {
    border: 1px solid #d9d9d9;
    border-right: 0px;
  }
  .base-info-form {
    padding: 24px;
    background: #fbfbfb;
    border: 1px solid #d9d9d9;
    width: 100%;
    overflow: auto;
    // border-radius: 6px;
  }
}
</style>
