<template>
  <div class="login">
    <div class="login-background">
      <!--autoplay 自动播放 muted 静音 loop 循环播放-->
      <video autoplay="autoplay" loop="loop" style="width: 100%">
        <source src="../assets/WeAreNASA-WeA7edXsU40.mp4" type="video/mp4">
      </video>
    </div>
    <div class="login-form">

    </div>

  </div>
</template>

<script setup>

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
  background: rgba(0, 0, 0, 0.5);;
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
</style>