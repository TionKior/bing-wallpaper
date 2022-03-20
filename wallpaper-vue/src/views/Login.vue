<template>
  <div class="login">
    <div class="login-background">
      <!--autoplay 自动播放 muted 静音 loop 循环播放-->
      <video autoplay="autoplay" loop="loop" style="width: 100%">
        <source src="../assets/WeAreNASA-WeA7edXsU40.mp4" type="video/mp4">
      </video>
    </div>
    <div class="login-form">
      <div class="login-form-img">
        <div class="demo-type">
          <el-avatar :size="80" src="https://empty" @error="errorHandler">
            <img
                src="https://cube.elemecdn.com/e/fd/0fc7d20532fdaf769a25683617711png.png"
                alt=""/>
          </el-avatar>
        </div>
      </div>
      <div class="login-form-form">
        <el-form :model="form" label-width="0px">
          <el-form-item label="">
            <el-input v-model="form.username"/>
          </el-form-item>
          <el-form-item label="">
            <el-input v-model="form.password"/>
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
        <router-link to="/register">
          &nbsp;JOIN US&nbsp;
        </router-link>
      </div>

    </div>

  </div>
</template>

<script setup>
import {reactive} from 'vue'

const errorHandler = () => true
// do not use same name with ref
const form = reactive({
  username: '',
  password: '',
  hint: 'INPUT YOU INFORMATION'
})

const onSubmit = () => {
  console.log('submit!')
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