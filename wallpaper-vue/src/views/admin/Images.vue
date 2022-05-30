<template>
  <router-link to="/admin/addImage">
    <el-button type="primary">点此添加壁纸</el-button>
  </router-link>
  <el-table
      id="out-table"
      :data="imagesList"
      style="width: 100%"
      border
      stripe
      highlight-current-row
      max-height="700"
      element-loading-text="拼命加载中"
      @selection-change="handleSelectionChange"
  >
    <el-table-column
        fixed
        prop="id"
        label="ID"
        width="50">
    </el-table-column>
    <el-table-column
        prop="imageUrl"
        label="壁纸"
        width="250"
    >
      <template #default="scope">
        <div class="demo-image__preview" @click="showThisRow(scope.row.id)">
          <el-image style="width: 250px; height: 150px;"
                    :src="scope.row.imageUrl"
                    :preview-src-list="scope.row.imageUrl"
          ></el-image>
        </div>
      </template>
    </el-table-column>
    <el-table-column
        prop="imageName"
        label="壁纸名称"
        width="200">
    </el-table-column>
    <el-table-column
        prop="author"
        label="作者"
        width="150">
    </el-table-column>
    <el-table-column
        prop="address"
        label="地址"
        width="200">
    </el-table-column>
    <el-table-column
        prop="createTime"
        label="拍摄时间"
        width="180">
    </el-table-column>

    <el-table-column
        prop="category"
        label="分类"
        width="100">
    </el-table-column>

    <el-table-column
        fixed="right"
        label="操作"
        width="120">
      <template #default="scope">
        <a @click.prevent="deleteRow(scope.$index, imagesList,scope.row.id)">
          删除该壁纸
        </a>
        <br/>
        <a @click.prevent="showThisRow(scope.row.id)">
          查看该壁纸
        </a>
        <br/>
        <a>
          <a @click.prevent="editRow(scope.row.id)">
            编辑该壁纸
          </a>
        </a>
        <!--<el-dropdown>
          <span class="el-dropdown-link">
            修改分类
            <el-icon class="el-icon&#45;&#45;right">
              <arrow-down/>
            </el-icon>
          </span>
          <template #dropdown>
            <el-dropdown-menu v-for="category in categorys">
              <el-dropdown-item @click="changeCategory(scope.row.id,category.id)">
                {{ category.category }}
              </el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>-->
      </template>
    </el-table-column>

  </el-table>
</template>

<script>

import router from "@/router";

export default {
  name: "AdminImages",
  data() {
    return {
      imagesList: [
        {
          id: '',
          imageUrl: '',
          imageName: '',
          author: '',
          address: '',
          createTime: '',
          category: ''
        }
      ],
      categorys: [
        {
          id: '',
          category: ''
        }
      ],
    }
  },
  methods: {
    // 编辑该壁纸
    editRow(imageId) {
      this.$router.push({
        path: '/admin/edit',
        query: {
          imageId: imageId
        }
      })
    },
    //选中某条数据时
    handleSelectionChange(row) {
      this.selectList = row;
    },
    getWallpaperList() {
      this.$http.get('/wallPaperListAndCategory').then(res => {
        this.imagesList = res.data.data
        console.log(this.imagesList)
      })
    },
    // 获取分类
    getCategory() {
      this.$http.get('/classify').then(response => {
        this.categorys = response.data.data
      })
    },
    // 修改壁纸分类
    changeCategory(imageId, categoryId) {
      console.log(imageId)
      console.log(categoryId)
    },

    // 删除壁纸
    deleteRow(index, rows, imagesId) {
      this.$msgbox.confirm('确认删除吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http.get('/deleteWallpaperById', {
          params: {
            wallpaperId: imagesId
          }
        }).then(res => {
          let codeAndMessage = res.data
          if (codeAndMessage.code === '0000') {
            rows.splice(index, 1);
            this.$message({
              type: 'success',
              message: '删除成功!'
            });
            this.getWallpaperList()
          } else {
            alert(codeAndMessage.msg)
          }
        })
      }).catch(() => {
      });
    },
    showThisRow(imageId) {
      this.$router.push({
        path: '/imageDetail',
        query: {
          imageId: imageId
        }
      })
    }
  },
  created() {
    this.getWallpaperList()
    this.getCategory()
  }
}
</script>

<style scoped>
a {
  text-decoration: none;
  cursor: pointer;
}
</style>
