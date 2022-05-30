<template>
  <div class="login">
    <div class="login-background">
      <!--autoplay 自动播放 muted 静音 loop 循环播放-->
      <img src="../../assets/background.jpg">
    </div>
    <div class="login-form">
      <div class="login-form-img">
        <div class="demo-type">
          <el-avatar :size="80" src="https://empty" @error="errorHandler">
            <img
                src="../../assets/avatar.jpg"
                alt=""/>
          </el-avatar>
        </div>
      </div>
      <div class="login-form-form">
        <el-form :model="form" label-width="0px">
          <el-form-item label="">
            <el-input v-model="form.userName"/>
          </el-form-item>
          <el-form-item label="">
            <el-input v-model="form.userPassword" type="password"/>
          </el-form-item>

          <el-form-item>
            <h6 style="color: red">{{ form.hint }}</h6>
            <el-button class="login-form-submit" type="primary" @click="onSubmit">LOGIN</el-button>
          </el-form-item>
        </el-form>

      </div>
      <div class="login-form-footer">
        <a href="http://www.tionkior.com">
          &nbsp;ABOUT US&nbsp;
        </a>
        |
        <router-link to="/admin/register">
          &nbsp;JOIN US&nbsp;
        </router-link>
      </div>

    </div>

  </div>
</template>

<script setup>
import {
  getCurrentInstance,
  onMounted,
  reactive,
  watch
} from "vue";
import Constants from "@/constant/Constants";

// 获取代理对象,使用axios
const {proxy} = getCurrentInstance()

const errorHandler = () => true
// do not use same name with ref
const form = reactive({
  userName: '',
  userPassword: '',
  hint: '请输入你的账号密码'
})

const onSubmit = () => {
  if (form.userName === '' || form.userPassword === '') {
    form.hint = '请输入你的账号密码'
    return
  }
  form.hint = '登陆中...'
  proxy.$http.post('/admin/login', {
    userName: form.userName,
    userPassword: form.userPassword
  }).then(res => {
    const codeAndMessage = res.data
    if (codeAndMessage.code === '0000') {
      proxy.$message.success('登陆成功')
      form.hint = '登录成功'
      setTimeout(() => {
        if (res.data.data.userName === Constants.ADMIN_NAME) {
          window.location.href = '/admin'
        } else {
          window.location.href = '/'
        }
      }, 1000)
      // 向localStorage中存储用户对象
      localStorage.setItem("userName", res.data.data.userName);
      localStorage.setItem("userId", res.data.data.userId);
    } else {
      form.hint = res.data.msg
      proxy.$message({
        message: codeAndMessage.msg,
        type: 'error'
      });
    }
  }).catch(errorHandler)
}
</script>

<style scoped>
video {
  position: fixed;
  right: 0;
  bottom: 0;
  min-width: 100%;
  min-height: 100%;
  width: auto;
  height: auto;
  z-index: -9999;
  /*灰色调*/
  /*-webkit-filter:grayscale(100%)*/
}

.login-form {
  text-align: center; /*让div内部文字居中*/
  background: white;
  border-radius: 20px;
  width: 300px;
  height: 350px;
  margin: auto;
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
}

.login-form-img {
  margin-top: 30px;
}

.login-form-form {
  margin-top: 50px;
}

.login-form-submit {
  position: absolute;
  margin-left: 200px;
}

.login-form-footer {
  margin-top: 50px;
}

h6 {
  position: absolute;
  margin-left: 10px;
}
</style>
