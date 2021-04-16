<template>
  <page-header-wrapper :title="'单号：' + ( this.$route.params.projectId || '')" :tab-list="tabList"
    :tab-active-key="tabActiveKey" @tabChange="handleTabChange">
    <!-- 顶部按钮部分 -->
    <template slot="extra">
      <div>
        <a-button  v-for="item in formSchema.functions" :key="item.type" type="primary" @click="handleClick(item)" style="margin-left: 10px">
          {{ item.title }}
        </a-button>
      </div>
    </template>

    <a-card :bordered="true" v-if="tabActiveKey === 'detail'">
      <div>
        <real-form ref="realForm" @ok="submitCallback" :unEditable="editable=='true' ? false : true"></real-form>

        <div style="display: flex; justify-content: center">
          <a-button type="primary" @click="submit" :loading="saveButtonLoading">保存</a-button>
        </div>
      </div>
    </a-card>

  </page-header-wrapper>
</template>

<script>
import CardIcon from '@/components/CardIcon/CardIcon'
import { mapActions, mapState, mapMutations } from 'vuex'
import { deepCopy } from '@/utils/util'
import formMixin from '@/core/mixins/form-mixin'
//import RegistrationDetail from './components/RegistrationDetail'
import { baseMixin } from '@/store/app-mixin'
import RealForm from './components/RegistrationForm'
import { httpGet } from '@/utils/httpClient'

export default {
  name: 'ProcessContainer',
  mixins: [baseMixin, formMixin],
  components: {
    CardIcon,
    RealForm,
    //RegistrationDetail
  },
  data() {
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
        detail: '/api-reguar/Registration/detail/',
      },
      tabList: [{ key: 'detail', tab: '详情' }], /* todo 可以在这手动添加多tab，也可通过接口请求tab信息赋值 */
      tabActiveKey: 'detail',
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
      default: () => '',
    },
  },
  filters: {
    statusFilter(status) {
      const statusMap = {
        agree: '成功',
        reject: '驳回',
      }
      return statusMap[status]
    },
    statusTypeFilter(type) {
      const statusTypeMap = {
        agree: 'success',
        reject: 'error',
      }
      return statusTypeMap[type]
    },
  },
  mounted() {
    /** todo 可以通过 this.getButtonAndTab 从接口中获得流程相关的按钮，标签等； 也可通过如下手动配置 */
    /* this.getButtonAndTab({
      nodeId: this.nodeId,
      taskId: this.taskId,
      processKey: this.processKey,
      roles: this.roles,
      queryId: this.businessId || this.projectId
    }) */
    this.handBtnAndTab()

    this.getInitData()
  },
  updated() { },
  computed: {
    unEditable() {
      return false
    },
    currentTab() {
      return this.formSchema.tabs.find((item) => item.tabKey === this.formSchema.currentTabKey[0])
    },
    ...mapState({
      formSchema: (state) => state.form.formSchema,
      roles: (state) => state.user.roles,
    }),
  },
  methods: {
    handBtnAndTab() {
      let btnAndTab = {
        tabs: [
          {
            'key': 'detail',
            'tab': '详情',
            'type': 'normal',
            'editable': false
          }
        ],
        functions: [
          {
            title: '返回上页',
            type: 'goBack'
          }
        ]
      }

      this.tabList = btnAndTab.tabs
      this.updateFormSchema(deepCopy(btnAndTab))
    },
    getInitData() {
      httpGet(this.url.detail + this.$route.params.projectId)
        .then((res) => {
          if (res.resp_code === 0) {
            this.$refs.realForm.edit(res.datas)
          }
        })
        .catch((e) => {
          console.log(e)
        })
    },
    edit(record) {
      this.visible = true
      this.$nextTick(() => {
        this.$refs.realForm.edit(record)
      })
    },
    submit() {
      this.$refs.realForm.submit()
    },
    submitCallback() {
      console.log('保存成功。。。')
    },
    handleTabChange(key) {
      console.log('')
      this.tabActiveKey = key
    },
    handleTabStatusChange(tab, complete) {
      this.formSchema.tabs.find((item) => item.tabKey === tab)['complete'] = complete
      this.updateFormSchema(deepCopy(this.formSchema))
    },
    handleClick(item) {
      /* todo 这里添加所有流程按钮的点击事件逻辑 */
      console.log('触发按钮动作：' + item.type)

      /* todo 多tab页，需要判断保存所有标签页时，如此调用： this.checkAllTabs() */

      if (item.type === 'apply') {}

      if (item.type === 'complete') {}

      if (item.type === 'completegd') {}

      if (item.type === 'reback') {}

      if (item.type === 'end') {}

      if (item.type === 'goBack') {}

      if (item.type === 'cancel') {}

    },
    checkAllTabs() {
      if (this.formSchema.tabs.some((tab) => tab.type == 'normal' && tab.editable && !tab.complete)) {
        // 有tab页面没保存
        this.$message.error('未保存所有标签')
        return false
      }
      return true
    },
    ...mapActions(['updateFormSchema', 'getButtonAndTab']),
    ...mapMutations(['updateFormSchema']),
  },
  destroyed() {
    this.updateFormSchema({})
  },
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
