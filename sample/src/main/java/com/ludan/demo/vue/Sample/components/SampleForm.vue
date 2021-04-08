
<template>
    <a-spin :spinning="loading">
        <a-form :form="form" v-bind="formLayout">
            <!-- 检查是否有 id 并且大于0，大于0是修改。其他是新增，新增不显示主键ID -->
                <a-form-item label="主键" >
                        <a-input v-decorator="['id',validatorRules.id ]"/>
                </a-form-item>
                <a-form-item label="文本框" >
                        <a-input v-decorator="['inputText',validatorRules.inputText ]"/>
                </a-form-item>
                <a-form-item label="数字" >
                        <a-input-number v-decorator="['number',validatorRules.number ]" :min="1" :max="10" style="width:100%"/>
                </a-form-item>
                <a-form-item label="下拉单选" >
                        <a-select v-decorator="['selectOne',validatorRules.selectOne ]">
                            <a-select-option value="">请选择</a-select-option>
                            <a-select-option v-for="(item, name) in pageDict.projectSource" :key="name" :value="item.code">
                                {{ item.value }}
                            </a-select-option>
                        </a-select>
                </a-form-item>
                <a-form-item label="下拉多选" >
                        <a-select v-decorator="['selectMany',validatorRules.selectMany ]" mode="multiple">
                            <a-select-option v-for="(item, name) in pageDict.mainType" :key="name" :value="item.code">
                                {{ item.value }}
                            </a-select-option>
                        </a-select>
                </a-form-item>
                <a-form-item label="多选" >
                        <a-checkbox-group
                                v-decorator="['checkBox']" style="width: 100%;">
                            <a-row>
                                <a-col :span="8" v-for="(item, name) in pageDict.custType" :key="name" >
                                    <a-checkbox :value="item.code">
                                        {{ item.value }}
                                    </a-checkbox>
                                </a-col>
                            </a-row>
                        </a-checkbox-group>
                </a-form-item>
                <a-form-item label="开关" >
                        <a-radio-group v-decorator="['radio',validatorRules.radio]">
                            <a-radio v-for="(item, name) in pageDict.stateSdndType" :key="name" :value="item.code">
                                {{ item.value }}
                            </a-radio>
                        </a-radio-group>
                </a-form-item>
                <a-form-item label="日期组件" >
                        <a-date-picker v-decorator="['fieldDate',validatorRules.fieldDate ]" style="width: 100%"/>
                </a-form-item>
                <a-form-item label="日期时间组件" >
                        <a-date-picker v-decorator="['fieldDateTime',validatorRules.fieldDateTime ]" format="YYYY-MM-DD HH:mm:ss" show-time style="width: 100%"/>
                </a-form-item>
                <a-form-item label="时间组件" >
                        <a-date-picker v-decorator="['fieldTime',validatorRules.fieldTime ]" style="width: 100%"/>
                </a-form-item>
                <a-form-item label="文件上传" >
                        <a-upload
                                v-decorator="['fieldFile',
                                  {
                                    valuePropName: 'fileList',
                                    getValueFromEvent: normFile,
                                  },
                                ]"
                                name="logo"
                                :action="uploadApi"
                                :headers="headers"
                                @change="(info) => handleFileUpload('type', info)"
                                :showUploadList="true"
                                list-type="picture">
                            <a-button> <a-icon type="upload" /> 上传附件 </a-button>
                        </a-upload>
                </a-form-item>
                <a-form-item label="图片上传" >
                        <a-upload
                                v-decorator="['fieldImage',
                                      {
                                        valuePropName: 'fileList',
                                        getValueFromEvent: normFile,
                                      },
                                    ]"
                                name="logo"
                                :action="uploadApi"
                                :headers="headers"
                                @change="(info) => handleFileUpload('type', info)"
                                :showUploadList="true"
                                list-type="picture">
                            <a-button> <a-icon type="upload" /> 上传图片 </a-button>
                        </a-upload>
                </a-form-item>
                <a-form-item label="多行文本" >
                        <a-textarea v-decorator="['textArea',validatorRules.textArea ]" auto-size />
                </a-form-item>
                <a-form-item label="用户选择组件" >
                        <a-input v-decorator="['userSelect',validatorRules.userSelect ]"/>
                </a-form-item>
                <a-form-item label="组织选择组件" >
                        <a-input v-decorator="['orgSelect',validatorRules.orgSelect ]"/>
                </a-form-item>
                <a-form-item label="富文本" >
                        <a-textarea v-decorator="['richText',validatorRules.richText ]" auto-size />
                </a-form-item>
                <a-form-item label="区域选择" >
                        <a-input v-decorator="['region',validatorRules.region ]"/>
                </a-form-item>
                <a-form-item label="树选择" >
                        <a-tree-select
                                v-decorator="['treeSelect',validatorRules.treeSelect ]"
                                tree-data-simple-mode
                                style="width: 100%"
                                :dropdown-style="{ maxHeight: '400px', overflow: 'auto' }"
                                :tree-data="treeData"
                                placeholder="Please select"
                                :load-data="onLoadData"
                        />
                </a-form-item>
                <a-form-item label="自定义弹框" >
                        <a-input v-decorator="['modal',validatorRules.modal ]"/>
                </a-form-item>
                <a-form-item label="创建者" >
                        <a-input v-decorator="['creator',validatorRules.creator ]"/>
                </a-form-item>
                <a-form-item label="创建时间" >
                        <a-date-picker v-decorator="['createTime',validatorRules.createTime ]" format="YYYY-MM-DD HH:mm:ss" show-time style="width: 100%"/>
                </a-form-item>
                <a-form-item label="创建者名称" >
                        <a-input v-decorator="['createName',validatorRules.createName ]"/>
                </a-form-item>
                <a-form-item label="更新者" >
                        <a-input v-decorator="['updator',validatorRules.updator ]"/>
                </a-form-item>
                <a-form-item label="更新时间" >
                        <a-date-picker v-decorator="['updateTime',validatorRules.updateTime ]" format="YYYY-MM-DD HH:mm:ss" show-time style="width: 100%"/>
                </a-form-item>
                <a-form-item label="更新者名称" >
                        <a-input v-decorator="['updateName',validatorRules.updateName ]"/>
                </a-form-item>
        </a-form>
    </a-spin>
</template>

<script>
    import pick from 'lodash.pick'
    import { httpGet, httpPost, httpDelete, httpPut, downFile } from '@/utils/httpClient'
    import { getDictionaryByCodes } from '@/utils/dictUtil'
    import FileUploadMixin from '@/core/mixins/FileUploadMixin'
    import FormMixin from '@/core/mixins/FormMixin'



    // 表单字段
    // const fields = ['id', 'dictCode', 'code', 'value', 'parentDictValueCode', 'orderBy', 'extA', 'extB', 'enable']
    const fields = [
        'id',
        'inputText',
        'number',
        'selectOne',
        'selectMany',
        'checkBox',
        'radio',
        'fieldDate',
        'fieldDateTime',
        'fieldTime',
        'fieldFile',
        'fieldImage',
        'textArea',
        'userSelect',
        'orgSelect',
        'richText',
        'region',
        'treeSelect',
        'modal',
        'creator',
        'createTime',
        'createName',
        'updator',
        'updateTime',
        'updateName',
    ]

    const StringToArrFields = [
    
    
    
    
     'selectMany', 
     'checkBox', 
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    ]


    export default {
        props: {

        },
        mixins: {
            FileUploadMixin,
            FormMixin
        },
        data () {
            return {
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
                //页面级字典
                pageDict: {},
                validatorRules: {
                    id:{rules:[{required:false},{ validator: this.validateId }]},
                    inputText:{rules:[{required:false},{ validator: this.validateInputText }]},
                    number:{rules:[{required:false},{ validator: this.validateNumber }]},
                    selectOne:{rules:[{required:false},{ validator: this.validateSelectOne }]},
                    selectMany:{rules:[{required:false},{ validator: this.validateSelectMany }]},
                    checkBox:{rules:[{required:false},{ validator: this.validateCheckBox }]},
                    radio:{rules:[{required:false},{ validator: this.validateRadio }]},
                    fieldDate:{rules:[{required:false},{ validator: this.validateFieldDate }]},
                    fieldDateTime:{rules:[{required:false},{ validator: this.validateFieldDateTime }]},
                    fieldTime:{rules:[{required:false},{ validator: this.validateFieldTime }]},
                    fieldFile:{rules:[{required:false},{ validator: this.validateFieldFile }]},
                    fieldImage:{rules:[{required:false},{ validator: this.validateFieldImage }]},
                    textArea:{rules:[{required:false},{ validator: this.validateTextArea }]},
                    userSelect:{rules:[{required:false},{ validator: this.validateUserSelect }]},
                    orgSelect:{rules:[{required:false},{ validator: this.validateOrgSelect }]},
                    richText:{rules:[{required:false},{ validator: this.validateRichText }]},
                    region:{rules:[{required:false},{ validator: this.validateRegion }]},
                    treeSelect:{rules:[{required:false},{ validator: this.validateTreeSelect }]},
                    modal:{rules:[{required:false},{ validator: this.validateModal }]},
                    creator:{rules:[{required:false},{ validator: this.validateCreator }]},
                    createTime:{rules:[{required:false},{ validator: this.validateCreateTime }]},
                    createName:{rules:[{required:false},{ validator: this.validateCreateName }]},
                    updator:{rules:[{required:false},{ validator: this.validateUpdator }]},
                    updateTime:{rules:[{required:false},{ validator: this.validateUpdateTime }]},
                    updateName:{rules:[{required:false},{ validator: this.validateUpdateName }]},
                },
                urls: {
                    add: '/api-sample/GeneratorRule/add',
                    edit: '/api-sample/GeneratorRule/edit'
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
            initDictConfig(){
                console.log('初始化页面级字典项')
                const dictCodes = [
                    'projectSource',
                    'mainType',
                    'custType',
                    'stateSdndType',
                ]
                getDictionaryByCodes(dictCodes).then((res) => {
                    this.pageDict = res
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
                this.form.validateFields((err, values) => {
                    if (!err) {
                        this.loading = true
                        let formData = Object.assign(this.model, values)
                        for(let key in formData) {
                          if(Array.isArray(formData[key])){
                            formData[key] = JSON.stringify(formData[key])
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
                                        this.$message.success(res.message)
                                        this.$emit('ok')
                                    } else {
                                        this.$message.warning(res.message)
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
            validateId (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的主键')
            },
            validateInputText (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的文本框')
            },
            validateNumber (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的数字')
            },
            validateSelectOne (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的下拉单选')
            },
            validateSelectMany (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的下拉多选')
            },
            validateCheckBox (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的多选')
            },
            validateRadio (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的开关')
            },
            validateFieldDate (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的日期组件')
            },
            validateFieldDateTime (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的日期时间组件')
            },
            validateFieldTime (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的时间组件')
            },
            validateFieldFile (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的文件上传')
            },
            validateFieldImage (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的图片上传')
            },
            validateTextArea (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的多行文本')
            },
            validateUserSelect (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的用户选择组件')
            },
            validateOrgSelect (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的组织选择组件')
            },
            validateRichText (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的富文本')
            },
            validateRegion (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的区域选择')
            },
            validateTreeSelect (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的树选择')
            },
            validateModal (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的自定义弹框')
            },
            validateCreator (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的创建者')
            },
            validateCreateTime (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的创建时间')
            },
            validateCreateName (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的创建者名称')
            },
            validateUpdator (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的更新者')
            },
            validateUpdateTime (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的更新时间')
            },
            validateUpdateName (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的更新者名称')
            },

        }
    }
</script>
