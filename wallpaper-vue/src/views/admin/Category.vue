<template>
  <el-table
      id="out-table"
      :data="categoryList"
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
        label="分类ID"
        width="200">
    </el-table-column>
    <el-table-column
        prop="category"
        label="分类名称"
        width="200">
    </el-table-column>

    <el-table-column
        fixed="right"
        label="操作"
        width="120">
      <template #default="scope">
        <a @click.prevent="deleteRow(scope.$index, categoryList,scope.row.id)">
          删除该分类
        </a>
      </template>
    </el-table-column>
  </el-table>
  <h3>新增分类</h3>
  <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm"
           hide-required-asterisk status-icon>
    <el-form-item label="分类名称" prop="category">
      <el-input v-model="ruleForm.category"></el-input>
    </el-form-item>
    <el-button type="primary" @click="submitForm('ruleForm')">新增分类</el-button>
  </el-form>
</template>

<script>
export default {
  name: "Category",
  data() {
    return {
      categoryList: [
        {
          id: '',
          category: ''
        }
      ],
      ruleForm: {
        category: '',
      },
      rules: {
        category: [
          {required: true, message: '请输入分类名称', trigger: 'blur'},
          {min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur'}
        ],
      }
    }
  },
  methods: {
    //选中某条数据时
    handleSelectionChange(row) {
      this.selectList = row;
    },
    // 获取分类
    getCategory() {
      this.$http.get('/classify').then(res => {
        this.categoryList = res.data.data
      })
    },

    // 处理该反馈
    deleteRow(index, rows, categoryId) {
      this.$msgbox.confirm('该操作将彻底删除该分类,是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http.post('/category/deleteById', categoryId).then(res => {
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

    // 提交表单
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$http.post('/category/add', this.ruleForm).then(res => {
            let codeAndMessage = res.data
            if (codeAndMessage.code === '0000') {
              this.$message({
                type: 'success',
                message: '新增成功!'
              });
              this.getCategory()
              this.ruleForm = {
                category: ''
              }
            } else {
              this.$message.error(codeAndMessage.msg)
            }
          })
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    }

  },
  created() {
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
