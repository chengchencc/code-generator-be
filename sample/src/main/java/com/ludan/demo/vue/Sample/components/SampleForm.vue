
<template>
    <a-spin :spinning="loading">
        <a-form :form="form" v-bind="formLayout">
          <a-row :gutter="26" :style="rowStyle">
            <!-- 检查是否有 id 并且大于0，大于0是修改。其他是新增，新增不显示主键ID -->



                        <a-col :span="12" :style="colStyle">
                            <a-form-item label="主键" v-bind="colInfo12">
                                    <a-input v-decorator="['id',validatorRules.id ]" :disabled="unEditable"/>
                            </a-form-item>
                        </a-col>
                        <a-col :span="12" :style="colStyle">
                            <a-form-item label="文本框" v-bind="colInfo12">
                                    <a-input v-decorator="['inputText',validatorRules.inputText ]" :disabled="unEditable"/>
                            </a-form-item>
                        </a-col>
                        <a-col :span="12" :style="colStyle">
                            <a-form-item label="数字" v-bind="colInfo12">
                                    <a-input-number v-decorator="['number',validatorRules.number ]" style="width:100%" :disabled="unEditable"/>
                            </a-form-item>
                        </a-col>
                        <a-col :span="12" :style="colStyle">
                            <a-form-item label="下拉单选" v-bind="colInfo12">
                                    <a-select v-decorator="['selectOne',validatorRules.selectOne ]" :disabled="unEditable">
                                        <a-select-option v-for="(item, name) in pageDict.projectSource" :key="name" :value="item.code">
                                            {{ item.value }}
                                        </a-select-option>
                                    </a-select>
                            </a-form-item>
                        </a-col>
                        <a-col :span="12" :style="colStyle">
                            <a-form-item label="下拉多选" v-bind="colInfo12">
                                    <a-select v-decorator="['selectMany',validatorRules.selectMany ]" mode="multiple" :disabled="unEditable">
                                        <a-select-option v-for="(item, name) in pageDict.mainType" :key="name" :value="item.code">
                                            {{ item.value }}
                                        </a-select-option>
                                    </a-select>
                            </a-form-item>
                        </a-col>
                        <a-col :span="12" :style="colStyle">
                            <a-form-item label="多选" v-bind="colInfo12">
                                    <a-checkbox-group v-decorator="['checkBox']" style="width: 100%;" :disabled="unEditable">
                                        <a-row>
                                            <a-col :span="8" v-for="(item, name) in pageDict.custType" :key="name" >
                                                <a-checkbox :value="item.code">
                                                    {{ item.value }}
                                                </a-checkbox>
                                            </a-col>
                                        </a-row>
                                    </a-checkbox-group>
                            </a-form-item>
                        </a-col>
                        <a-col :span="12" :style="colStyle">
                            <a-form-item label="开关" v-bind="colInfo12">
                                    <a-radio-group v-decorator="['radio',validatorRules.radio]" :disabled="unEditable">
                                        <a-radio v-for="(item, name) in pageDict.stateSdndType" :key="name" :value="item.code">
                                            {{ item.value }}
                                        </a-radio>
                                    </a-radio-group>
                            </a-form-item>
                        </a-col>
                        <a-col :span="12" :style="colStyle">
                            <a-form-item label="日期组件" v-bind="colInfo12">
                                    <a-date-picker v-decorator="['fieldDate',validatorRules.fieldDate ]" style="width: 100%" :disabled="unEditable"/>
                            </a-form-item>
                        </a-col>
                        <a-col :span="12" :style="colStyle">
                            <a-form-item label="日期时间组件" v-bind="colInfo12">
                                    <a-date-picker v-decorator="['fieldDateTime',validatorRules.fieldDateTime ]" format="YYYY-MM-DD HH:mm:ss" show-time style="width: 100%" :disabled="unEditable"/>
                            </a-form-item>
                        </a-col>
                        <a-col :span="12" :style="colStyle">
                            <a-form-item label="时间组件" v-bind="colInfo12">
                                    <a-date-picker v-decorator="['fieldTime',validatorRules.fieldTime ]" style="width: 100%" :disabled="unEditable"/>
                            </a-form-item>
                        </a-col>
                        <a-col :span="12" :style="colStyle">
                            <a-form-item label="文件上传" v-bind="colInfo12">
                                    <a-upload
                                        :disabled="unEditable"
                                        v-decorator="['fieldFile',
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
                            </a-form-item>
                        </a-col>
                        <a-col :span="12" :style="colStyle">
                            <a-form-item label="图片上传" v-bind="colInfo12">
                                    <a-upload
                                        :disabled="unEditable"
                                        v-decorator="['fieldImage',
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
                            </a-form-item>
                        </a-col>
                        <a-col :span="12" :style="colStyle">
                            <a-form-item label="多行文本" v-bind="colInfo12">
                                    <a-textarea v-decorator="['textArea',validatorRules.textArea ]" auto-size :disabled="unEditable"/>
                            </a-form-item>
                        </a-col>
                        <a-col :span="12" :style="colStyle">
                            <a-form-item label="用户选择组件" v-bind="colInfo12">
                                                            <span>暂不支持类型</span>
                            </a-form-item>
                        </a-col>
                        <a-col :span="12" :style="colStyle">
                            <a-form-item label="组织选择组件" v-bind="colInfo12">
                                                            <span>暂不支持类型</span>
                            </a-form-item>
                        </a-col>
                        <a-col :span="12" :style="colStyle">
                            <a-form-item label="富文本" v-bind="colInfo12">
                                                            <span>暂不支持类型</span>
                            </a-form-item>
                        </a-col>
                        <a-col :span="12" :style="colStyle">
                            <a-form-item label="区域选择" v-bind="colInfo12">
                                                            <span>暂不支持类型</span>
                            </a-form-item>
                        </a-col>
                        <a-col :span="12" :style="colStyle">
                            <a-form-item label="树选择" v-bind="colInfo12">
                                                            <span>暂不支持类型</span>
                            </a-form-item>
                        </a-col>
                        <a-col :span="12" :style="colStyle">
                            <a-form-item label="自定义弹框" v-bind="colInfo12">
                                        <span>暂不支持类型</span>
                            </a-form-item>
                        </a-col>
                        <a-col :span="12" :style="colStyle">
                            <a-form-item label="创建者" v-bind="colInfo12">
                                    <a-input v-decorator="['creator',validatorRules.creator ]" :disabled="unEditable"/>
                            </a-form-item>
                        </a-col>
                        <a-col :span="12" :style="colStyle">
                            <a-form-item label="创建时间" v-bind="colInfo12">
                                    <a-date-picker v-decorator="['createTime',validatorRules.createTime ]" format="YYYY-MM-DD HH:mm:ss" show-time style="width: 100%" :disabled="unEditable"/>
                            </a-form-item>
                        </a-col>
                        <a-col :span="12" :style="colStyle">
                            <a-form-item label="创建者名称" v-bind="colInfo12">
                                    <a-input v-decorator="['createName',validatorRules.createName ]" :disabled="unEditable"/>
                            </a-form-item>
                        </a-col>
                        <a-col :span="12" :style="colStyle">
                            <a-form-item label="更新者" v-bind="colInfo12">
                                    <a-input v-decorator="['updator',validatorRules.updator ]" :disabled="unEditable"/>
                            </a-form-item>
                        </a-col>
                        <a-col :span="12" :style="colStyle">
                            <a-form-item label="更新时间" v-bind="colInfo12">
                                    <a-date-picker v-decorator="['updateTime',validatorRules.updateTime ]" format="YYYY-MM-DD HH:mm:ss" show-time style="width: 100%" :disabled="unEditable"/>
                            </a-form-item>
                        </a-col>
                        <a-col :span="12" :style="colStyle">
                            <a-form-item label="更新者名称" v-bind="colInfo12">
                                    <a-input v-decorator="['updateName',validatorRules.updateName ]" :disabled="unEditable"/>
                            </a-form-item>
                        </a-col>


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
        props: ['unEditable'],
        mixins: [FileUploadMixin, FormMixin, dictMixin],
        data () {
            return {
                district: null,
                uploadApi: '/api-file/files/upload',
                headers: {
                  Authorization: `Bearer ${storage.get('Access-Token')}`,
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
                    id:{rules:[{required:true,message:"主键不能为空"},{ validator: this.validateId }]},
                    inputText:{rules:[{required:false,message:"文本框不能为空"},{ validator: this.validateInputText }]},
                    number:{rules:[{required:false,message:"数字不能为空"},{ validator: this.validateNumber }]},
                    selectOne:{rules:[{required:false,message:"下拉单选不能为空"},{ validator: this.validateSelectOne }]},
                    selectMany:{rules:[{required:false,message:"下拉多选不能为空"},{ validator: this.validateSelectMany }]},
                    checkBox:{rules:[{required:false,message:"多选不能为空"},{ validator: this.validateCheckBox }]},
                    radio:{rules:[{required:false,message:"开关不能为空"},{ validator: this.validateRadio }]},
                    fieldDate:{rules:[{required:false,message:"日期组件不能为空"},{ validator: this.validateFieldDate }]},
                    fieldDateTime:{rules:[{required:false,message:"日期时间组件不能为空"},{ validator: this.validateFieldDateTime }]},
                    fieldTime:{rules:[{required:false,message:"时间组件不能为空"},{ validator: this.validateFieldTime }]},
                    fieldFile:{rules:[{required:false,message:"文件上传不能为空"},{ validator: this.validateFieldFile }]},
                    fieldImage:{rules:[{required:false,message:"图片上传不能为空"},{ validator: this.validateFieldImage }]},
                    textArea:{rules:[{required:false,message:"多行文本不能为空"},{ validator: this.validateTextArea }]},
                    userSelect:{rules:[{required:false,message:"用户选择组件不能为空"},{ validator: this.validateUserSelect }]},
                    orgSelect:{rules:[{required:false,message:"组织选择组件不能为空"},{ validator: this.validateOrgSelect }]},
                    richText:{rules:[{required:false,message:"富文本不能为空"},{ validator: this.validateRichText }]},
                    region:{rules:[{required:false,message:"区域选择不能为空"},{ validator: this.validateRegion }]},
                    treeSelect:{rules:[{required:false,message:"树选择不能为空"},{ validator: this.validateTreeSelect }]},
                    modal:{rules:[{required:false,message:"自定义弹框不能为空"},{ validator: this.validateModal }]},
                    creator:{rules:[{required:false,message:"创建者不能为空"},{ validator: this.validateCreator }]},
                    createTime:{rules:[{required:false,message:"创建时间不能为空"},{ validator: this.validateCreateTime }]},
                    createName:{rules:[{required:false,message:"创建者名称不能为空"},{ validator: this.validateCreateName }]},
                    updator:{rules:[{required:false,message:"更新者不能为空"},{ validator: this.validateUpdator }]},
                    updateTime:{rules:[{required:false,message:"更新时间不能为空"},{ validator: this.validateUpdateTime }]},
                    updateName:{rules:[{required:false,message:"更新者名称不能为空"},{ validator: this.validateUpdateName }]},
                },
                urls: {
                    add: '/api-sample/Sample/add',
                    edit: '/api-sample/Sample/edit'
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
            // 需要手动绑定该方法： @change="onCityChange"
            // 区县for循环取值改为： v-for="(item, name) in district ? district : pageDict.district"
            onCityChange (cityCode) {
                this.district = []
                this.form.setFieldsValue({countyName: ''})
                for (let item of this.pageDict.district) {
                    if (item.code.startsWith(cityCode.slice(0, 4))) {
                        this.district.push(item)
                    }
                }
            },
            initDictConfig(){
                console.log('初始化页面级字典项')
                const dictCodes = [
                    'projectSource',
                    'mainType',
                    'custType',
                    'stateSdndType',
                ]
                try{
                  this.pageDict = this.$store.state.common.dict.dictsList.list || {}
                }catch(e){}
                getDictionaryByCodes(dictCodes).then((res) => {
                    this.pageDict = Object.assign(this.pageDict || {}, res || {})
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
