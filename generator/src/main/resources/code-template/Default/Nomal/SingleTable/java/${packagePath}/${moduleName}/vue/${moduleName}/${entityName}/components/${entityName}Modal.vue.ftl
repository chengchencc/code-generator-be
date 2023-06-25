<template>
    <a-modal
            :title="title"
            :width="width"
            :visible="visible"
            :confirmLoading="loading"
            @ok="handleOk"
            @cancel="handleCancel">
        <real-form ref="realForm" @ok="submitCallback"></real-form>
    </a-modal>
</template>

<script>
    import RealForm from './${entityName}Form'

    export default {
        components: {
            RealForm
        },
        props: {

        },
        data () {
            return {
                title: 'title',
                width: 800,
                visible: false,
                loading: false,
                model: {}
            }
        },
        beforeCreate () {},
        created () {
            console.log('custom modal created')
        },
        mounted () {
            // vue 生命周期钩子，已完成模板渲染，此处可以进行dom操作
        },
        methods: {
            add () {
                this.visible = true
                this.$nextTick(() => {
                    this.$refs.realForm.add()
                })
            },
            edit (record) {
                this.visible = true
                this.$nextTick(() => {
                    this.$refs.realForm.edit(record)
                })
            },
            close () {
                this.$emit('cancel')
                this.visible = false
            },
            handleOk () {
                this.$refs.realForm.submit()
            },
            handleCancel () {
                this.close()
            },
            submitCallback (model) {
                this.$emit('ok',model)
                this.visible = false
            }
        }
    }
</script>
