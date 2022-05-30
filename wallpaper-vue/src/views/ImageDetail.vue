<template>
  <div class="image-detail" :style="{backgroundImage: 'url(' + data.image.imageUrl + ')'}">

    <div class="image-detail-navigation-bar">
      <!--Back-->
      <div class="image-detail-navigation-bar-left">
        <el-button color="#696969" style="" plain @click="$router.back()">Back</el-button>
      </div>

      <!--换一张-->
      <div class="image-detail-favorite-image" v-if="data.isFavorite === false">
        <el-button type="info" @click="favoriteImage()">收藏图片</el-button>
      </div>

      <!--换一张-->
      <div class="image-detail-favorite-image" v-if="data.isFavorite !== false">
        <el-button type="info" @click="notFavoriteImage()">取消收藏</el-button>
      </div>

      <!--换一张-->
      <div class="image-detail-random-image">
        <el-button type="info" @click="getRandomWallPaper()">换一张</el-button>
      </div>

      <!--GO Search-->
      <div class="image-detail-navigation-bar-right">
        <el-button color="#696969" plain @click="goSearch">GO Search</el-button>
      </div>
    </div>

    <div class="image-detail-text">
      <div class="image-detail-text-li">
        <h3 style="color: #FFFFFF">{{ data.image.imageName + ',' + data.image.address }}</h3>
        <h4 style="color: #FFFFFF">{{ '© ' + data.image.author }}</h4>
        <h4 style="color: #FFFFFF">{{ data.image.createTime }}</h4>
      </div>
    </div>
  </div>
</template>

<script setup>
import {
  getCurrentInstance,
  onMounted,
  reactive, watch,
} from "vue";
import {useRoute, useRouter} from "vue-router";
import store from "@/store";

// 获取代理对象,使用axios
const {proxy} = getCurrentInstance()
// 定义路由
const router = useRouter()
const route = useRoute()

const data = reactive({
  image: {
    // 获取到数据 请求图片
    id: route.query.imageId,
    imageUrl: '',
    imageName: '',
    author: '',
    address: '',
    createTime: '',
  },
  fit: 'scale-down',
  isFavorite: false,
  userId: '',
})

// 获取图片详情
const getWallPaperDetail = () => {
  proxy.$http.get("/wallPaperDetail", {
    params: {
      imageId: data.image.id
    }
  }).then((res) => {
    data.image = res.data.data
  })
}

// 跳转Search
const goSearch = () => {
  router.push({
    path: '/search',
    query: {
      image: encodeURIComponent(JSON.stringify(data.image))
    }
  })
}

// 获取随机壁纸
const getRandomWallPaper = () => {
  proxy.$http.get("/wallPaperRandom").then((res) => {
    console.log(data.image)
    data.image = res.data.data
  })
}

// 图片是否被喜爱
const isFavorite = () => {
  proxy.$http.get("/isFavoriteImage", {
    params: {
      userId: store.state.userId,
      imageId: data.image.id
    }
  }).then((res) => {
    const codeAndMessage = res.data
    console.log(codeAndMessage.code)
    // 用户已经收藏该图片
    if (codeAndMessage.code === '0006') {
      data.isFavorite = true
    }

  })
}

// 喜爱该图片
const favoriteImage = () => {
  proxy.$http.get("/favoriteImage", {
    params: {
      userId: store.state.userId,
      imageId: data.image.id
    }
  }).then((res) => {
    const codeAndMessage = res.data
    if (codeAndMessage.code === '0000') {
      proxy.$message.success('收藏成功')
      getWallPaperDetail()
    } else {
      proxy.$message({
        message: codeAndMessage.msg,
        type: 'error'
      });
    }
  })
}

// 取消喜爱该图片
const notFavoriteImage = () => {
  proxy.$http.get("/notFavoriteImage", {
    params: {
      userId: store.state.userId,
      imageId: data.image.id
    }
  }).then((res) => {
    const codeAndMessage = res.data
    if (codeAndMessage.code === '0000') {
      proxy.$message.success('取消成功')
      getWallPaperDetail()
    } else {
      proxy.$message({
        message: codeAndMessage.msg,
        type: 'error'
      });
    }
  })
}

onMounted(() => {
  getWallPaperDetail()
  isFavorite()
})

watch(route, () => {
  router.go(0)
})
</script>

<style scoped>
.image-detail {
  width: 100%;
  height: 100%;
  position: fixed;
  /*防止图片因缩小被挤压*/
  background-size: cover
}

.image-detail-text {
  background: rgba(0, 0, 0, 0.5);
  height: 17%;
  width: 100%;
  position: fixed;
  bottom: 0;
}

.image-detail-text-li {
  margin-left: 40px;
}

.image-detail-navigation-bar {
  background: rgba(0, 0, 0, 0.5);
  height: 10%;
  width: 100%;
  display: flex;

  align-items: center;
}

/*顶部栏左*/
.image-detail-navigation-bar-left {
  margin-left: 40px;
}

/*顶部栏右*/
.image-detail-navigation-bar-right {
  margin-left: 40px;
}

.image-detail-random-image {
  margin-left: 40px;
}

.image-detail-favorite-image {
  margin-left: 1000px;
}

</style>
