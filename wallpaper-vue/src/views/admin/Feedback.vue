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
        width="698">
    </el-table-column>

    <el-table-column
        fixed="right"
        label="操作"
        width="120">
      <template #default="scope">
        <a @click.prevent="deleteRow(scope.$index, feedbackList,scope.row.id)">
          点击已处理
        </a>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
export default {
  name: "Feedback",
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

    // 获取所有未处理的反馈
    getFeedbackListStateIsZero() {
      this.$http.get('/feedback/feedbackListStateIsZero').then(res => {
        this.feedbackList = res.data.data
        console.log(this.feedbackList)
      })
    },

    // 处理该反馈
    deleteRow(index, rows, feedbackId) {
      this.$msgbox.confirm('确认处理吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http.post('/feedback/updateFeedbackStateById', feedbackId
        ).then(res => {
          let codeAndMessage = res.data
          if (codeAndMessage.code === '0000') {
            rows.splice(index, 1);
            this.$message({
              type: 'success',
              message: '处理成功!'
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
    this.getFeedbackListStateIsZero()
  },
}
</script>

<style scoped>
a {
  text-decoration: none;
  cursor: pointer;
}
</style>
