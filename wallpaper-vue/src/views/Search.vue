<template>

  <div>
    <!--底部壁纸-->
    <div class="search" :style="{backgroundImage: 'url(' + data.image.imageUrl + ')'}">

      <div class="search-random-image">
        <el-button type="info" @click="getRandomWallPaper()">换一张</el-button>
      </div>

      <!--搜索框-->
      <div class="search-bar">
        <input :value="data.searchValue" class="search-bar-input"
               @keyup.enter="search(data.searchValue)"/>
      </div>

      <!--信息文本-->
      <div class="search-text">
        <div class="search-text-li">
          <h3 style="color: #FFFFFF">{{ data.image.imageName + ',' + data.image.address }}</h3>
          <h4 style="color: #FFFFFF">{{ '© ' + data.image.author }}</h4>
          <h4 style="color: #FFFFFF">{{ data.image.createTime }}</h4>
        </div>
      </div>

    </div>
  </div>
</template>

<script setup>
import {
  getCurrentInstance,
  onMounted,
  reactive,
} from "vue";
import {useRoute, useRouter} from 'vue-router'

// 获取代理对象,this
const {proxy} = getCurrentInstance()
// 使用路由
const route = useRoute()
const router = useRouter()

const data = reactive({
  image: {
    // 获取到数据 请求图片
    id: '0',
    imageUrl: '',
    imageName: '',
    author: '',
    address: '',
    createTime: '',
  },
  searchValue: ''
})

const search = (searchValue) => {
  proxy.$http.get('http://api.bing.com/qsonhs.aspx?type=cb&q=' + searchValue)
}

// 获取随机壁纸
const getRandomWallPaper = () => {
  proxy.$http.get("/wallPaperRandom").then((res) => {
    data.image = res.data.data
  })
}

// 封装数据
onMounted(() => {
  let imageRouter = decodeURIComponent(route.query.image)
  data.image = JSON.parse(imageRouter)
})
</script>

<style scoped>
.search {
  width: 100%;
  height: 100%;
  position: fixed;
  /*防止图片因缩小被挤压*/
  background-size: cover;
  z-index: -1;
}

/*下方文本*/
.search-text {
  background: rgba(0, 0, 0, 0.5);
  height: 17%;
  width: 100%;
  position: fixed;
  bottom: 0;
}

.search-text-li {
  margin-left: 40px;
}

.search-bar {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}

.search-bar-input {
  height: 50px;
  width: 450px;
  font-size: 20px;
  border-radius: 25px;
  background-color: transparent;
  color: #FFFFFF;
}

.search-random-image {
  margin-left: 1400px;
  margin-top: 30px;
}
</style>