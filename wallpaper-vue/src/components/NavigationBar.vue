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
          <a href="https://cn.bing.com/" class="navbar-brand" style="color: white">
            Bing
          </a>
        </div>
        <div id="menu" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li :class="{active: data.active===index}" v-for="(classify,index) in data.classifications"
                @click="setIndex(index)">
              <a class="li-a" @click="pushCategoryId(classify.id)">{{ classify.category }}</a>
            </li>
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
import images from '../views/Images'
import {useRouter} from "vue-router";

let {proxy} = getCurrentInstance();

let data = reactive({
  active: '',
  classifications: [{
    id: '',
    category: ''
  }]
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
    path: '/images',
    query: {
      categoryId: categoryId
    }
  })
}

onMounted(() => {
  getClassify()
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