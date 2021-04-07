
<template>
    <a-spin :spinning="loading">
        <a-form :form="form" v-bind="formLayout">
            <!-- 检查是否有 id 并且大于0，大于0是修改。其他是新增，新增不显示主键ID -->
                <a-form-item label="主键" >
                        <a-input-number v-decorator="['id',validatorRules.id ]" :min="1" :max="10" style="width:100%"/>
                </a-form-item>
                <a-form-item label="租户Id" >
                        <a-input v-decorator="['tenantId',validatorRules.tenantId ]"/>
                </a-form-item>
                <a-form-item label="创建时间" >
                        <a-date-picker v-decorator="['creationTime',validatorRules.creationTime ]" style="width: 100%"/>
                </a-form-item>
                <a-form-item label="创建者" >
                        <a-input v-decorator="['creationUserId',validatorRules.creationUserId ]"/>
                </a-form-item>
                <a-form-item label="删除时间" >
                        <a-date-picker v-decorator="['deletionTime',validatorRules.deletionTime ]" style="width: 100%"/>
                </a-form-item>
                <a-form-item label="删除人" >
                        <a-input v-decorator="['deletionUserId',validatorRules.deletionUserId ]"/>
                </a-form-item>
                <a-form-item label="最后修改时间" >
                        <a-date-picker v-decorator="['lastModifyTime',validatorRules.lastModifyTime ]" style="width: 100%"/>
                </a-form-item>
                <a-form-item label="最后修改人" >
                        <a-input v-decorator="['lastModifyUserId',validatorRules.lastModifyUserId ]"/>
                </a-form-item>
                <a-form-item label="作者" >
                        <a-input v-decorator="['authorName',validatorRules.authorName ]"/>
                </a-form-item>
                <a-form-item label="作者邮箱" >
                        <a-input v-decorator="['email',validatorRules.email ]"/>
                </a-form-item>
                <a-form-item label="模块名" >
                        <a-input v-decorator="['moduleName',validatorRules.moduleName ]"/>
                </a-form-item>
                <a-form-item label="包名" >
                        <a-input v-decorator="['packageName',validatorRules.packageName ]"/>
                </a-form-item>
                <a-form-item label="模板" >
                        <a-select v-decorator="['uiTemplate',validatorRules.uiTemplate ]">
                            <a-select-option value="">请选择</a-select-option>
                            <a-select-option v-for="(item, name) in pageDict.projectSource" :key="name" :value="item.code">
                                {{ item.value }}
                            </a-select-option>
                        </a-select>
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
        'tenantId',
        'creationTime',
        'creationUserId',
        'deletionTime',
        'deletionUserId',
        'lastModifyTime',
        'lastModifyUserId',
        'authorName',
        'email',
        'moduleName',
        'packageName',
        'uiTemplate',
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
                    tenantId:{rules:[{required:false},{ validator: this.validateTenantId }]},
                    creationTime:{rules:[{required:false},{ validator: this.validateCreationTime }]},
                    creationUserId:{rules:[{required:false},{ validator: this.validateCreationUserId }]},
                    deletionTime:{rules:[{required:false},{ validator: this.validateDeletionTime }]},
                    deletionUserId:{rules:[{required:false},{ validator: this.validateDeletionUserId }]},
                    lastModifyTime:{rules:[{required:false},{ validator: this.validateLastModifyTime }]},
                    lastModifyUserId:{rules:[{required:false},{ validator: this.validateLastModifyUserId }]},
                    authorName:{rules:[{required:false},{ validator: this.validateAuthorName }]},
                    email:{rules:[{required:false},{ validator: this.validateEmail }]},
                    moduleName:{rules:[{required:false},{ validator: this.validateModuleName }]},
                    packageName:{rules:[{required:false},{ validator: this.validatePackageName }]},
                    uiTemplate:{rules:[{required:false},{ validator: this.validateUiTemplate }]},
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
                console.log('edit::', this.record)
                this.model = Object.assign({}, record)
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
                        const formData = Object.assign(this.model, values)

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
                                    if (res.code === 0) {
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
            validateTenantId (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的租户Id')
            },
            validateCreationTime (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的创建时间')
            },
            validateCreationUserId (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的创建者')
            },
            validateDeletionTime (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的删除时间')
            },
            validateDeletionUserId (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的删除人')
            },
            validateLastModifyTime (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的最后修改时间')
            },
            validateLastModifyUserId (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的最后修改人')
            },
            validateAuthorName (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的作者')
            },
            validateEmail (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的作者邮箱')
            },
            validateModuleName (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的模块名')
            },
            validatePackageName (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的包名')
            },
            validateUiTemplate (rule, value, callback) {
                callback()
                // callback('请输入正确的格式的模板')
            },

        }
    }
</script>
