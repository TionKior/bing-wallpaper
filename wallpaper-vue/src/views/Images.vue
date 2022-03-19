<template>
  <!--导航栏-->
  <NavigationBar/>
  <div style="margin-top: 50px;">
    <!--轮播图-->
    <div class="marquee">
      <el-carousel :interval="4000" type="card" height="200px">
        <el-carousel-item v-for="image in imageList.records" :key="image">
          <router-link @click="getImageDetail(image.id)" to="/imageDetail">
            <img class="image-show" :src="image.imageUrl" alt="">
          </router-link>
        </el-carousel-item>
      </el-carousel>
    </div>

    <!--显示图片-->
    <div class="images">
      <el-row :gutter="10">
        <el-col :xs="24" :sm="12" :md="8" :lg="8" :xl="8" v-for="image in imageList.records">
          <div class="grid-content bg-purple-light">

            <el-popover
                class="tooltip"
                placement="top-start"
                :title="image.imageName"
                :width="300"
                trigger="hover"
                :content='image.address'>
              <template #reference>
                <router-link @click="getImageDetail(image.id)" to="/imageDetail">
                  <img class="image-show" :src="image.imageUrl" alt="">
                </router-link>
              </template>
            </el-popover>
          </div>
        </el-col>
      </el-row>
    </div>

    <!--分页-->
    <div class="page">
      <el-pagination class="el-page"
                     background
                     layout="prev, pager, next"
                     :current-page="imageList.current"
                     :page-size="imageList.size"
                     :total="imageList.total"
                     @current-change=getDataPage>
      </el-pagination>
    </div>

    <!--回到顶部-->
    <el-backtop/>

  </div>
  <Footer/>
</template>

<script setup>

import {
  getCurrentInstance,
  onMounted,
  reactive,
  watch
} from "vue";
import {useRoute, useRouter} from "vue-router";
import NavigationBar from "@/components/NavigationBar";
import Footer from "@/components/Footer"

// 获取代理对象,使用axios
const {proxy} = getCurrentInstance()
// 使用路由
const router = useRouter()
const route = useRoute()

let imageList = reactive({
  records: [
    {
      id: '',
      imageUrl: '',
      imageName: '',
      author: '',
      address: '',
      createTime: '',
      categoryId: ''
    }
  ],
  total: '',
  size: '',
  current: '',
  pages: ''
})

const getDataPage = (currentPage) => {
  proxy.$http.get('/wallPaperList', {
    params: {
      currentPage: currentPage,
      // 注意这里获取的是组别id,直接获取到的组别id,所以在调用时没有传参
      categoryId: route.query.categoryId
    }
  }).then((res) => {
    imageList.records = res.data.data.records
    imageList.total = res.data.data.total
    imageList.size = res.data.data.size
    imageList.current = res.data.data.current
    imageList.pages = res.data.data.pages
    document.body.scrollTop = document.documentElement.scrollTop = 0;
  })
}

// 获取单个图片详情
const getImageDetail = (imageId) => {
  router.push({
    path: '/imageDetail',
    query: {
      imageId: imageId
    }
  })
}

// 页面创建时加载数据
onMounted(() => {
  getDataPage()
})

// watch监听路由数据变化,刷新页面
watch(route,()=>{
  router.go(0)
})

</script>

<style scoped>
.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
  text-align: center;
}

.el-carousel__item:nth-child(2n) {
  background-color: #d3dce6;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}

.image-marquee {
  width: 100%;
  height: auto;
  position: absolute;
  top: 50%;
  left: 50%;
  display: block;
  min-width: 100%;
  min-height: 100%;
  transform: translate(-50%, -50%);
}

/*跑马灯END*/

/*图片响应式布局*/

.el-col {
  border-radius: 4px;
}

.bg-purple-dark {
  background: #99a9bf;
}

.bg-purple {
  background: #d3dce6;
}

.bg-purple-light {
  background: #FFFFFF;
}

.grid-content {
  margin-top: 5px;
  border-radius: 8px;
  min-height: 225px;
}

/*图片响应式布局END*/

/*图片等比例缩小*/
.image-show {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

/*图片等比例缩小END*/

/*分页按钮居中显示*/

.el-page {
  margin-bottom: 10px;
}

/*分页按钮居中显示END*/

/*点赞按钮*/


</style>