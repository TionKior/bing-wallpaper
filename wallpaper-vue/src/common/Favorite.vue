<template>
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
        width="250">
      <template #default="scope">
        <div class="demo-image__preview">
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
          取消收藏
        </a>
        <br/>
        <a @click.prevent="showThisRow(scope.row.id)">
          查看该壁纸
        </a>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
export default {
  name: "Favorite",
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
    }
  },
  methods: {
    //选中某条数据时
    handleSelectionChange(row) {
      this.selectList = row;
    },
    getUserFavoriteWallpaperList() {
      this.$http.get('/userFavoriteWallPaperListAndCategory', {
        params: {
          userId: this.$store.state.userId
        }
      }).then(res => {
        this.imagesList = res.data.data
        console.log(this.imagesList)
      })
    },
    // 删除壁纸
    deleteRow(index, rows, imageId) {
      this.$msgbox.confirm('确认取消收藏吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http.get('/notFavoriteImage', {
          params: {
            userId: this.$store.state.userId,
            imageId: imageId
          }
        }).then(res => {
          let codeAndMessage = res.data
          if (codeAndMessage.code === '0000') {
            rows.splice(index, 1);
            this.$message({
              type: 'success',
              message: '取消收藏成功!'
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
    this.getUserFavoriteWallpaperList()
  }
}
</script>

<style scoped>
a {
  text-decoration: none;
  cursor: pointer;
}
</style>
