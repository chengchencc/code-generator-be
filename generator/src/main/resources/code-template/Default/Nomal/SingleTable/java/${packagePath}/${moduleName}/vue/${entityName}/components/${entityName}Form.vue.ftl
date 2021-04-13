<#include "/common/utils.ftl">
<#include "/common/dataEntityUtils.ftl">
<template>
    <a-spin :spinning="loading">
        <a-form :form="form" v-bind="formLayout">
          <a-row :gutter="26" :style="rowStyle">
            <!-- 检查是否有 id 并且大于0，大于0是修改。其他是新增，新增不显示主键ID -->
            <#-- <a-form-item v-show="model" label="主键ID">-->

            <#list groupList?keys as key >
                 <#if key=="foot" || key=="base" >

                 <#else>
                  <a-divider>${key}</a-divider>
                 </#if>

                <#list groupList[key] as field >
                    <#assign fieldui = field.dataFieldUI>
                    <#if fieldui.formDisplay?c == 'true' >
                        <a-col :span="12" :style="colStyle">
                            <a-form-item label="${field.description}" v-bind="colInfo12">
                                <#switch fieldui.controlType>
                                <#case "PlainText">
                                    <a-input v-decorator="['${field.name}']" disabled/>
                                    <#break>
                                <#case "InputText">
                                    <a-input v-decorator="['${field.name}',validatorRules.${field.name} ]" :disabled="unEditable"/>
                                    <#break>
                                <#case "Number">
                                    <a-input-number v-decorator="['${field.name}',validatorRules.${field.name} ]" style="width:100%" :disabled="unEditable"/>
                                    <#break>
                                <#case "Password">
                                    <a-input v-decorator="['${field.name}',validatorRules.${field.name} ]" :disabled="unEditable"/>
                                    <#break>
                                <#case "SelectOne">
                                    <a-select v-decorator="['${field.name}',validatorRules.${field.name} ]" :disabled="unEditable">
                                        <#if field.dataFieldUI.dictCode??>
                                        <a-select-option v-for="(item, name) in pageDict.${fieldui.dictCode}" :key="name" :value="item.code">
                                            {{ item.value }}
                                        </a-select-option>
                                        <#else>
                                            <a-select-option value="">未设置字典编码</a-select-option>
                                        </#if>
                                    </a-select>
                                    <#break>
                                <#case "SelectMany">
                                    <a-select v-decorator="['${field.name}',validatorRules.${field.name} ]" mode="multiple" :disabled="unEditable">
                                        <#if field.dataFieldUI.dictCode??>
                                        <a-select-option v-for="(item, name) in pageDict.${field.dataFieldUI.dictCode}" :key="name" :value="item.code">
                                            {{ item.value }}
                                        </a-select-option>
                                        <#else>
                                            <a-select-option value="">未设置字典编码</a-select-option>
                                        </#if>
                                    </a-select>
                                    <#break>
                                <#case "Checkbox">
                                    <a-checkbox-group v-decorator="['${field.name}']" style="width: 100%;" :disabled="unEditable">
                                        <a-row>
                                            <#if field.dataFieldUI.dictCode??>
                                            <a-col :span="8" v-for="(item, name) in pageDict.${field.dataFieldUI.dictCode}" :key="name" >
                                                <a-checkbox :value="item.code">
                                                    {{ item.value }}
                                                </a-checkbox>
                                            </a-col>
                                                <#else >
                                            <a-col :span="8" >
                                            未设置字典编码
                                            </a-col>
                                            </#if>
                                        </a-row>
                                    </a-checkbox-group>
                                    <#break>
                                <#case "Radio">
                                    <a-radio-group v-decorator="['${field.name}',validatorRules.${field.name}]" :disabled="unEditable">
                                        <#if field.dataFieldUI.dictCode??>
                                        <a-radio v-for="(item, name) in pageDict.${field.dataFieldUI.dictCode}" :key="name" :value="item.code">
                                            {{ item.value }}
                                        </a-radio>
                                            <#else >
                                            <a-radio>
                                                未设置字典编码
                                            </a-radio>
                                        </#if>
                                    </a-radio-group>
                                    <#break>
                                <#case "Date">
                                    <a-date-picker v-decorator="['${field.name}',validatorRules.${field.name} ]" style="width: 100%" :disabled="unEditable"/>
                                    <#break>
                                <#case "DateTime">
                                    <a-date-picker v-decorator="['${field.name}',validatorRules.${field.name} ]" format="YYYY-MM-DD HH:mm:ss" show-time style="width: 100%" :disabled="unEditable"/>
                                    <#break>
                                <#case "Time">
                                    <a-date-picker v-decorator="['${field.name}',validatorRules.${field.name} ]" style="width: 100%" :disabled="unEditable"/>
                                    <#break>
                                <#case "File">
                                    <a-upload
                                        :disabled="unEditable"
                                        v-decorator="['${field.name}',
                                          {
                                            valuePropName: 'fileList',
                                            getValueFromEvent: normFile,
                                          },
                                        ]"
                                        :before-upload="beforeUpload"
                                        :action="uploadApi"
                                        :headers="headers"
                                        @change="(info) => handleFileUpload('type', info)"
                                        :showUploadList="true"
                                        list-type="picture"
                                    >
                                        <a-button> <a-icon type="upload" /> 上传附件 </a-button>
                                    </a-upload>
                                    <#break>
                                <#case "Image">
                                    <a-upload
                                        :disabled="unEditable"
                                        v-decorator="['${field.name}',
                                              {
                                                valuePropName: 'fileList',
                                                getValueFromEvent: normFile,
                                              },
                                            ]"
                                        :before-upload="beforeUpload"
                                        :action="uploadApi"
                                        :headers="headers"
                                        @change="(info) => handleFileUpload('type', info)"
                                        :showUploadList="true"
                                        list-type="picture"
                                    >
                                        <a-button> <a-icon type="upload" /> 上传图片 </a-button>
                                    </a-upload>
                                    <#break>
                                <#case "TextArea">
                                    <a-textarea v-decorator="['${field.name}',validatorRules.${field.name} ]" auto-size :disabled="unEditable"/>
                                    <#break>
                                                        <#case "RichText">
                                                            <span>暂不支持类型</span>
                                                    <#--                        <a-textarea v-decorator="['${field.name}',validatorRules.${field.name} ]" auto-size />-->
                                                            <#break>
                                                        <#case "UserSelect">
                                                            <span>暂不支持类型</span>
                                    <#--                    <a-input v-decorator="['${field.name}',validatorRules.${field.name} ]"/>-->
                                                            <#break>
                                                        <#case "OrgSelect">
                                                            <span>暂不支持类型</span>
                                    <#--                        <a-input v-decorator="['${field.name}',validatorRules.${field.name} ]"/>-->
                                                            <#break>
                                                        <#case "Region">
                                                            <span>暂不支持类型</span>
                                    <#--                    <a-input v-decorator="['${field.name}',validatorRules.${field.name} ]"/>-->
                                                            <#break>
                                                        <#case "TreeSelect">
                                                            <span>暂不支持类型</span>
                                    <#--                        <a-tree-select-->
                                    <#--                                v-decorator="['${field.name}',validatorRules.${field.name} ]"-->
                                    <#--                                tree-data-simple-mode-->
                                    <#--                                style="width: 100%"-->
                                    <#--                                :dropdown-style="{ maxHeight: '400px', overflow: 'auto' }"-->
                                    <#--                                :tree-data="treeData"-->
                                    <#--                                placeholder="Please select"-->
                                    <#--                                :load-data="onLoadData"-->
                                    <#--                        />-->
                                        <#break>
                                    <#case "Modal">
                                        <span>暂不支持类型</span>
                                        <#--   <a-input v-decorator="['${field.name}',validatorRules.${field.name} ]"/>-->
                                        <#break>
                                    <#default>
                                        <span>暂不支持类型</span>
                                </#switch>
                            </a-form-item>
                        </a-col>
                    </#if>
                </#list>

            </#list>

           </a-row>
        </a-form>
    </a-spin>
</template>

<script>
    import pick from 'lodash.pick'
    import { httpGet, httpPost, httpDelete, httpPut, downFile } from '@/utils/httpClient'
    import { getDictionaryByCodes } from '@/utils/dictUtil'
    import FileUploadMixin from '@/core/mixins/FileUploadMixin'
    import FormMixin from '@/core/mixins/FormMixin'
    import { dictMixin } from '@/store/dict-mixin'
    import storage from 'store'


    // 表单字段
    // const fields = ['id', 'dictCode', 'code', 'value', 'parentDictValueCode', 'orderBy', 'extA', 'extB', 'enable']
    const fields = [
    <#list entity.fields as field >
    <#assign fieldui = field.dataFieldUI>
        '${field.name}',
    </#list>
    ]

    const StringToArrFields = [
    <#list entity.fields as field >
    <#assign fieldui = field.dataFieldUI>
    <#if fieldui.controlType == "Checkbox" || fieldui.controlType == "SelectMany"> '${field.name}', </#if>
    </#list>
    ]


    export default {
        props: ['unEditable'],
        mixins: [FileUploadMixin, FormMixin, dictMixin],
        data () {
            return {
                district: null,
                uploadApi: '/api-file/files/upload',
                headers: {
                  Authorization: `Bearer ${'$'}{storage.get('Access-Token')}`,
                },
                model: {},
                loading: false,
                // 布局
                formLayout: {
                    labelCol: {
                        xs: { span: 24 },
                        sm: { span: 5 },
                        md: { span: 5 },
                        lg: { span: 5 },
                        xl: { span: 5 },
                        xxl: { span: 5 }
                    },
                    wrapperCol: {
                        xs: { span: 24 },
                        sm: { span: 16 },
                        md: { span: 16 },
                        lg: { span: 16 },
                        xl: { span: 16 },
                        xxl: { span: 16 }
                    }
                },
                colInfo6: {
                  labelCol: { span: 12 },
                  wrapperCol: { span: 9 }
                },
                colInfo8: {
                  labelCol: { span: 9 },
                  wrapperCol: { span: 12 }
                },
                colInfo12: {
                  labelCol: { span: 6 },
                  wrapperCol: { span: 16 }
                },
                colInfo24: {
                  labelCol: { span: 3 },
                  wrapperCol: { span: 20 }
                },
                colStyle: 'padding: 0 !important',
                rowStyle: 'padding: 0 12px 0 12px !important',

                //页面级字典
                pageDict: {},
                validatorRules: {
                    <#list entity.fields as field >
                    <#assign fieldui = field.dataFieldUI>
                    ${field.name}:{rules:[{required:${field.isRequired?c},message:"${field.description}不能为空"},{ validator: this.validate${field.name?cap_first} }]},
                    </#list>
                },
                urls: {
                    add: '/api-sample/${entityName}/add',
                    edit: '/api-sample/${entityName}/edit'
                }
            }
        },
        beforeCreate () {},
        created () {
            console.log('custom modal created')
            // 初始化字典配置 在自己页面定义
            this.initDictConfig()
            this.form = this.$form.createForm(this)

            // 防止表单未注册
            fields.forEach((v) => this.form.getFieldDecorator(v))

            // 当 model 发生改变时，为表单设置值
            // this.$watch('model', () => {
            //   console.log('model change', this.model)
            //   this.model && this.form.setFieldsValue(pick(this.model, fields))
            // })
        },
        mounted () {
            // vue 生命周期钩子，已完成模板渲染，此处可以进行dom操作
        },
        methods: {
            onCityChange (cityCode) {
                this.district = {}
                this.form.setFieldsValue({countyName: ''})
                for (const key in this.dictCodeVaue.district) {
                    if (key.startsWith(cityCode.slice(0, 4))) {
                        this.district[key] = this.dictCodeVaue.district[key]
                    }
                }
            },
            initDictConfig(){
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
            },
            beforeUpload() {
              // 上传前 的图片校验等操作
              return true
            },
            normFile(e) {
              console.log('Upload event:', e);
              if (Array.isArray(e)) {
                return e;
              }
              return e && e.fileList;
            },
            handleFileUpload(type, info) {
              console.log('上传组件状态改变', type, info)
              let fileList = [...info.fileList]
              // 2. read from response and show file link
              fileList = fileList
                .filter((item) => {
                  if (item.response && item.error) {
                    this.$message.error('文件上传失败')
                    return false
                  }
                  return true
                })
                .map((file) => {
                  if (file.response) {
                    // Component will show file.url as link
                    file.url = file.response.url
                    file.attFileType = type
                    file.attFileId = file.response.id
                    file.attFileName = file.name
                  }
                  return file
                })
            },
            handleChange () {
                console.log(arguments)
            },
            add () {
                this.edit({})
            },
            edit (record) {
                console.log('edit::', record)
                this.model = Object.assign({}, record)

                let newModel = JSON.parse(JSON.stringify(this.model));
                for(let i = 0 ; i<StringToArrFields.length ; i++) {
                  if( newModel.hasOwnProperty(StringToArrFields[i]) ){
                    newModel[StringToArrFields[i]] = newModel[StringToArrFields[i]] ? JSON.parse(newModel[StringToArrFields[i]]) : []
                  }
                }

                this.model = newModel;

                this.form.resetFields()
                this.$nextTick(() => {
                    this.model && this.form.setFieldsValue(pick(this.model, fields))
                })
            },

            submit () {
                // 触发表单验证
                this.form.validateFieldsAndScroll()
                this.form.validateFields((err, values) => {
                    if (!err) {
                        this.loading = true
                        let formData = Object.assign(this.model, values)
                        for (let i = 0; i < StringToArrFields.length; i++) {
                          if (formData.hasOwnProperty(StringToArrFields[i]) && formData[StringToArrFields[i]]) {
                            formData[StringToArrFields[i]] = JSON.stringify(formData[StringToArrFields[i]])
                          }
                        }

                        let httpRequest = null
                        if (!this.model.id) {
                            httpRequest = httpPost(this.urls.add, formData)
                        } else {
                            httpRequest = httpPut(this.urls.edit, formData)
                        }
                        console.log('表单提交数据', formData)
                        httpRequest
                            .then(
                                (res) => {
                                    if (res.resp_code === 0) {
                                        this.$message.success(res.resp_msg)
                                        this.$emit('ok')
                                    } else {
                                        this.$message.warning(res.resp_msg)
                                    }
                                },
                                (err, con) => {
                                    this.$message.warning('保存失败！')
                                    console.log(err, con)
                                }
                            )
                            .finally(() => {
                                this.loading = false
                            })
                    }
                })
            },
            // 自定义校验逻辑代码写在这里
            // 若校验成功，回调callback，参数为空 ： callback()
            // 若校验失败，回调callback ： callback('校验失败提示信息')
            // 示例如下：
            // 示例1：正则校验
            // eslint-disable-next-line standard/no-callback-literal
            // if (!value || new RegExp(/^1[3|4|5|7|8][0-9]\d{8}$/).test(value)) {
            //   callback()
            // } else {
            //   // eslint-disable-next-line standard/no-callback-literal
            //   callback('请输入正确格式的手机号码!')
            // }
            // 示例2：获取form中一个值，与当前输入值尽心比较
            // const form = this.form
            // const confirmPassword = form.getFieldValue('confirmPassword')
            // if (value && confirmPassword && value !== confirmPassword) {
            //   // eslint-disable-next-line standard/no-callback-literal
            //   callback('两次输入的密码不一样！')
            // }
            // if (value && this.confirmDirty) {
            //   form.validateFields(['confirm'], { force: true })
            // }
            // callback()
            <#list entity.fields as field >
            <#assign fieldui = field.dataFieldUI>
            validate${field.name?cap_first} (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的${field.description}')
            },
            </#list>

        }
    }
</script>
