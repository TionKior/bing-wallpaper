<template>
  <div class="admin">
    <el-container>
      <!--侧边栏-->
      <SideMenu/>
      <!--侧边栏END-->
      <el-container>
        <!--<el-header class="admin-header">

        </el-header>-->
        <el-main class="el-main">

          <!--子父级路由显示-->
          <router-view/>

        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import SideMenu from "@/components/SideMenu";

export default {
  name: "admin",
  components: {
    SideMenu
  },
  data() {
    return {}
  },
  created() {
    this.$router.push('/admin/home') //默认跳转到首页
  }
}
</script>

<style scoped>
.el-header, .el-footer {
  background-color: #B3C0D1;
  color: #333;
  text-align: center;
  line-height: 60px;
}

.el-aside {
  background-color: #D3DCE6;
  color: #333;
  text-align: center;
  line-height: 200px;
}

body > .el-container {
  margin-bottom: 40px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}

.admin-header {
  text-align: center;
}

.admin {
  text-align: center;
  margin: auto;
}
</style>
