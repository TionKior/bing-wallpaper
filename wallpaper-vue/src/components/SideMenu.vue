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

    <router-link to="/admin/home">
      <el-menu-item index="1">
        <i class="el-icon-menu"></i>
        <span slot="title">主页</span>
      </el-menu-item>
    </router-link>

    <router-link to="/admin/user">
      <el-menu-item index="2">
        <i class="el-icon-menu"></i>
        <span slot="title">用户管理</span>
      </el-menu-item>
    </router-link>

    <router-link to="/admin/images">
      <el-menu-item index="3">
        <i class="el-icon-menu"></i>
        <span slot="title">壁纸管理</span>
      </el-menu-item>
    </router-link>

    <router-link to="/admin/category">
      <el-menu-item index="4">
        <i class="el-icon-menu"></i>
        <span slot="title">壁纸分类</span>
      </el-menu-item>
    </router-link>

    <router-link to="/admin/favorite">
      <el-menu-item index="5">
        <i class="el-icon-menu"></i>
        <span slot="title">收藏壁纸</span>
      </el-menu-item>
    </router-link>

    <router-link to="/admin/addImage">
      <el-menu-item index="6">
        <i class="el-icon-menu"></i>
        <span slot="title">新增壁纸</span>
      </el-menu-item>
    </router-link>


    <router-link to="/admin/feedback">
      <el-menu-item index="7">
        <i class="el-icon-menu"></i>
        <span slot="title">未解决反馈</span>
      </el-menu-item>
    </router-link>

    <router-link to="/admin/feedbackResolved">
      <el-menu-item index="8">
        <i class="el-icon-menu"></i>
        <span slot="title">已解决反馈</span>
      </el-menu-item>
    </router-link>

    <a href="http://localhost:8080/swagger-ui/index.html">
      <el-menu-item index="9">
        <i class="el-icon-menu"></i>
        <span slot="title">后台接口</span>
      </el-menu-item>
    </a>

    <el-menu-item index="10" @click="logout()">
      <i class="el-icon-menu"></i>
      <span slot="title">退出登录</span>
    </el-menu-item>

  </el-menu>
</template>

<script>
export default {
  name: "SideMenu",
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
};
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
