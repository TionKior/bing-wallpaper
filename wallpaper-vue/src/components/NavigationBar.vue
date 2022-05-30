<template>
  <div class="Navigation">

    <nav class="nav navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <!--在移动端的时候导航条折叠起来，三横的样式出现，点击该样式可以显示或隐藏导航条上的内容-->
          <button class="navbar-toggle" data-toggle="collapse" data-target="#menu">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <router-link to="/admin/login" class="navbar-brand" style="color: white">
            Bing
          </router-link>
        </div>
        <div id="menu" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li :class="{active: data.active===index}" v-for="(classify,index) in data.classifications"
                @click="setIndex(index)">
              <a class="li-a" @click="pushCategoryId(classify.id)">{{ classify.category }}</a>
            </li>

            <router-link to="/user"
                         v-if="data.username !== data.ADMIN_USERNAME || data.username === null || data.username === ''">
              <el-button @click="toUserHome()"
                         style="margin-left: 300px;margin-top: 10px">用户中心
              </el-button>
            </router-link>

            <router-link to="/admin" v-else>
              <el-button
                  style="margin-left: 300px;margin-top: 10px">管理员中心
              </el-button>
            </router-link>

            <router-link to="/admin/login">
              <el-button style="margin-top: 10px;">用户登录
              </el-button>
            </router-link>
          </ul>
        </div>
      </div>
    </nav>

  </div>
</template>

<script setup>
import {
  getCurrentInstance,
  onMounted, provide,
  reactive, ref,
} from 'vue'
import {useRouter} from "vue-router";
import {useStore} from "vuex";
import Constants from "@/constant/Constants";

let {proxy} = getCurrentInstance();
const store = useStore()
let data = reactive({
  active: '',
  classifications: [{
    id: '',
    category: ''
  }],
  username: '',
  ADMIN_USERNAME: Constants.ADMIN_NAME,
})

// 实现点击样式改变
const setIndex = (index) => {
  proxy.active = index;
}

// 请求分类数据
const getClassify = () => {
  proxy.$http.get('/classify').then((res) => {
    console.log(res.data.data)
    data.classifications = res.data.data
  })
}

// 使用路由
const router = useRouter()
// 查找分类图片
const pushCategoryId = (categoryId) => {
  router.push({
    path: '/',
    query: {
      categoryId: categoryId
    }
  })
}

const getUsername = () => {
  if (store.state.username !== '') {
    data.username = store.state.userName
  }
}

const toUserHome = () => {
  if (store.state.userName === null) {
    proxy.$message.error('请先登录')
    router.push('/admin/login')
  } else {
    if (data.ADMIN_USERNAME === data.username) {
      router.push({
        path: '/admin'
      })
    } else {
      router.push({
        path: '/user/home'
      })
    }
  }
}

onMounted(() => {
  getClassify()
  getUsername()
})

</script>

<style scoped>
body {
  position: relative;
}

.li-a {
  /*a标签小手*/
  cursor: pointer;
}

</style>
