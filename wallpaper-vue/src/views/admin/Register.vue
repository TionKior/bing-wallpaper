<template>
  <div class="register">
    <h1>新增用户信息</h1>
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm"
             hide-required-asterisk=true status-icon="true">
      <el-form-item label="用户名称" prop="username">
        <el-input v-model="ruleForm.userName"></el-input>
      </el-form-item>

      <el-form-item label="用户密码" prop="password">
        <el-input type="password" v-model="ruleForm.userPassword" autocomplete="off"></el-input>
      </el-form-item>

      <el-form-item label="确认密码" prop="checkPassword">
        <el-input type="password" v-model="ruleForm.checkPassword" autocomplete="off"></el-input>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name: "register",
  data() {
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.ruleForm.userPassword) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    return {
      ruleForm: {
        userName: '',
        userPassword: '',
        checkPassword: ''
      },
      rules: {
        userName: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
          {min: 3, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur'}
        ],
        userPassword: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {min: 3, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur'}
        ],
        checkPassword: [
          {validator: validatePass2, trigger: 'blur', required: true}
        ]
      }
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$http.post('/admin/register', this.ruleForm).then(res => {
            const codeAndMessage = res.data
            if (codeAndMessage.code === '0000') {
              this.$message({
                message: '注册成功',
                type: 'success'
              });
              this.ruleForm.userName = ''
              this.ruleForm.userPassword = ''
              this.ruleForm.checkPassword = ''
              this.$router.push('/admin/login')
            } else {
              this.$message({
                message: codeAndMessage.msg,
                type: 'error'
              });
            }
          })
        } else {
          this.$message({
            message: '请检查输入的信息',
            type: 'error'
          });
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },

  }

}
</script>

<style scoped>
.register {
  text-align: center;
  margin: auto;
}

.demo-ruleForm {
  margin: auto;
  max-width: 500px;
}

</style>
