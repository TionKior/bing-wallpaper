<template>
  <div class="feedback"></div>
  <h1>新增反馈信息</h1>
  <el-form :model="feedbackForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm"
           hide-required-asterisk=true status-icon="true">
    <el-form-item label="反馈标题" prop="title">
      <el-input v-model="feedbackForm.title"></el-input>
    </el-form-item>

    <el-form-item label="问题描述" prop="content">
      <el-input type="textarea" v-model="feedbackForm.content" autocomplete="off"></el-input>
    </el-form-item>

    <el-form-item>
      <el-button type="primary" @click="submitForm('ruleForm')">立即反馈</el-button>
      <el-button @click="resetForm('ruleForm')">重置</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
export default {
  name: "Feedback",
  data() {
    return {
      feedbackForm: {
        title: '',
        content: '',
      },
      userId: this.$store.state.userId,
      rules: {
        title: [
          {required: true, message: '请输入反馈标题', trigger: 'blur'},
          {min: 0, max: 20, message: '长度在 0 到 20 个字符', trigger: 'blur'}
        ],
        content: [
          {required: true, message: '请输入反馈正文', trigger: 'blur'},
          {min: 0, max: 200, message: '长度在 0 到 200 个字符', trigger: 'blur'}
        ],
      }
    }
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
            if (valid) {
              this.$http.post('/feedback/add',
                  {
                    'feedback': this.feedbackForm,
                    'userId': this.userId
                  }
              ).then(res => {
                    const codeAndMessage = res.data
                    if (codeAndMessage.code === '0000') {
                      this.$message({
                        message: '反馈成功',
                        type: 'success'
                      });
                      this.feedbackForm.title = ''
                      this.feedbackForm.content = ''
                    } else {
                      this.$message({
                        message: codeAndMessage.msg,
                        type: 'error'
                      });
                    }
                  }
              )
            } else {
              console.log('error submit!!')
              return false;
            }
          }
      )
      ;
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
  }
}
</script>

<style scoped>

</style>
