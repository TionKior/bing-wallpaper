<template>
  <!--侧边栏-->
  <el-menu
      default-active="1"
      class="el-menu-vertical-demo"
      @open="handleOpen"
      @close="handleClose"
  >
    <router-link :to="{ path: '/' }">
      <el-menu-item index="2">
        <i class="el-icon-menu"></i>
        <span slot="title">TionKior</span>
      </el-menu-item>
    </router-link>

    <router-link to="/user/home">
      <el-menu-item index="1">
        <i class="el-icon-menu"></i>
        <span slot="title">个人主页</span>
      </el-menu-item>
    </router-link>

    <router-link to="/user/favorite">
      <el-menu-item index="2">
        <i class="el-icon-menu"></i>
        <span slot="title">喜爱壁纸</span>
      </el-menu-item>
    </router-link>

    <router-link to="/user/addImage">
      <el-menu-item index="3">
        <i class="el-icon-menu"></i>
        <span slot="title">上传壁纸</span>
      </el-menu-item>
    </router-link>

    <router-link to="/user/feedback">
      <el-menu-item index="4">
        <i class="el-icon-menu"></i>
        <span slot="title">反馈建议</span>
      </el-menu-item>
    </router-link>

    <router-link to="/user/feedbackList">
      <el-menu-item index="5">
        <i class="el-icon-menu"></i>
        <span slot="title">我的反馈</span>
      </el-menu-item>
    </router-link>


    <el-menu-item index="6" @click="logout()">
      <i class="el-icon-menu"></i>
      <span slot="title">退出登录</span>
    </el-menu-item>

  </el-menu>
</template>

<script>
export default {
  name: "SideMenuUser",
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },

    logout() {
      this.$http.get('/admin/logout').then(res => {
        if (res.data.code === '0000') {
          this.$message({
            message: '退出成功',
            type: 'success'
          });
          // 向localStorage中存储用户对象
          localStorage.clear();
          this.$router.push('/');
        }
      })
    }

  },
}
</script>

<style scoped>
a {
  text-decoration: none;
}

/* 侧边栏 */
.el-menu-vertical-demo {
  /* 让它占左侧100% */
  height: 100vh;
  width: 25vh;
}
</style>
