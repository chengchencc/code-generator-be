
<template>
    <a-spin :spinning="loading">
        <a-form :form="form" v-bind="formLayout">
          <a-row :gutter="26" :style="rowStyle">
            <!-- 检查是否有 id 并且大于0，大于0是修改。其他是新增，新增不显示主键ID -->



                        <a-col :span="12" :style="colStyle">
                            <a-form-item label="id" v-bind="colInfo12">
                                    <a-input-number v-decorator="['id',validatorRules.id ]" style="width:100%" :disabled="unEditable"/>
                            </a-form-item>
                        </a-col>
                        <a-col :span="12" :style="colStyle">
                            <a-form-item label="fieldInt" v-bind="colInfo12">
                                    <a-input-number v-decorator="['fieldInt',validatorRules.fieldInt ]" style="width:100%" :disabled="unEditable"/>
                            </a-form-item>
                        </a-col>
                        <a-col :span="12" :style="colStyle">
                            <a-form-item label="fieldString" v-bind="colInfo12">
                                    <a-input v-decorator="['fieldString',validatorRules.fieldString ]" :disabled="unEditable"/>
                            </a-form-item>
                        </a-col>
                        <a-col :span="12" :style="colStyle">
                            <a-form-item label="fieldBool" v-bind="colInfo12">
                                    <a-checkbox-group v-decorator="['fieldBool']" style="width: 100%;" :disabled="unEditable">
                                        <a-row>
                                            <a-col :span="8" >
                                            未设置字典编码
                                            </a-col>
                                        </a-row>
                                    </a-checkbox-group>
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
        'fieldInt',
        'fieldString',
        'fieldBool',
    ]

    const StringToArrFields = [
    
    
    
     'fieldBool', 
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
                    id:{rules:[{required:true,message:"id不能为空"},{ validator: this.validateId }]},
                    fieldInt:{rules:[{required:true,message:"fieldInt不能为空"},{ validator: this.validateFieldInt }]},
                    fieldString:{rules:[{required:false,message:"fieldString不能为空"},{ validator: this.validateFieldString }]},
                    fieldBool:{rules:[{required:false,message:"fieldBool不能为空"},{ validator: this.validateFieldBool }]},
                },
                urls: {
                    add: '/api-sample/ChildA/add',
                    edit: '/api-sample/ChildA/edit'
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
                // callback('请输入正确的格式的id')
            },
            validateFieldInt (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的fieldInt')
            },
            validateFieldString (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的fieldString')
            },
            validateFieldBool (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的fieldBool')
            },

        }
    }
</script>
