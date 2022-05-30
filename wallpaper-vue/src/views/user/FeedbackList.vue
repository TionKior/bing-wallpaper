<template>
  <el-table
      id="out-table"
      :data="feedbackList"
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
        prop="userName"
        label="反馈人"
        width="100">
    </el-table-column>
    <el-table-column
        prop="feedbackTime"
        label="反馈时间"
        width="200">
    </el-table-column>
    <el-table-column
        prop="title"
        label="标题"
        width="150">
    </el-table-column>
    <el-table-column
        prop="content"
        label="内容"
        width="600">
    </el-table-column>
    <el-table-column
        prop="state"
        label="状态"
        width="95">
      <template #default="scope">
        <isState v-if="scope.row.state === '0'">
          未处理
        </isState>
        <isState v-if="scope.row.state === '1'">
          已处理
        </isState>
      </template>
    </el-table-column>

    <el-table-column
        fixed="right"
        label="操作"
        width="120">
      <template #default="scope">
        <a @click.prevent="deleteRow(scope.$index, feedbackList,scope.row.id)">
          点击删除反馈
        </a>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
export default {
  name: "FeedbackList",
  data() {
    return {
      feedbackList: [
        {
          id: '',
          userName: '',
          title: '',
          content: '',
          feedbackTime: '',
          state: ''
        }
      ],
    }
  },
  methods: {
    //选中某条数据时
    handleSelectionChange(row) {
      this.selectList = row;
    },

    // 获取该用户所有的反馈
    feedbackListByUserId() {
      let userId = this.$store.state.userId
      this.$http.post('/feedback/feedbackListByUserId', userId).then(res => {
        this.feedbackList = res.data.data
      })
    },

    // 删除该反馈
    deleteRow(index, rows, feedbackId) {
      this.$msgbox.confirm('该操作将彻底删除该反馈信息,是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http.get('/feedback/deleteById', {
          params: {
            feedbackId: feedbackId
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
            this.$message.error(codeAndMessage.msg)
          }
        })
      }).catch(() => {
      });
    },

  },
  created() {
    this.feedbackListByUserId();
  }
}
</script>

<style scoped>
a {
  text-decoration: none;
  cursor: pointer;
}
</style>
