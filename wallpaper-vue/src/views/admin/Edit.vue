<template>
  <div>
    <el-button @click="backImages()">返回上一级</el-button>
    <h3>壁纸ID：{{ ruleForm.id }}</h3>
    <el-form :model="ruleForm" ref="ruleForm" label-width="100px" class="demo-ruleForm"
             hide-required-asterisk status-icon>
      <el-form-item label="图片名称" prop="name">
        <el-input v-model="ruleForm.imageName"></el-input>
      </el-form-item>

      <el-form-item label="摄影师名称" prop="posts">
        <el-input type="textarea" :autosize="{ minRows: 2, maxRows: 4}" v-model="ruleForm.author"
                  autocomplete="off"></el-input>
      </el-form-item>

      <el-form-item label="地址" prop="work">
        <el-input type="textarea" :autosize="{ minRows: 2, maxRows: 4}" v-model="ruleForm.address"
                  autocomplete="off"></el-input>
      </el-form-item>

      <el-form-item label="分类" prop="work">
        <el-radio-group v-model="ruleForm.categoryId">
          <el-radio v-for="(category,index) in categoryList" :key="index" :label="category.id">
            {{ category.category }}
          </el-radio>

        </el-radio-group>
      </el-form-item>
    </el-form>

    <el-button type="primary" @click="submitForm('ruleForm')">提交修改</el-button>
  </div>
</template>

<script>
export default {
  name: "Edit",
  data() {
    return {
      ruleForm: {
        id: this.$route.query.imageId,
        imageName: '',
        author: '',
        address: '',
        categoryId: ''
      },
      categoryList: [
        {
          id: '',
          category: ''
        }
      ],

    }
  },
  methods: {
    handleChange(file, fileList) {
      this.ruleForm.file = URL.createObjectURL(file.raw);
    },

    beforeUpload(file) {
      return true;
    },

    // 获取分类
    getCategory() {
      this.$http.get('/classify').then(res => {
        this.categoryList = res.data.data
      })
    },
    getOneImage() {
      this.$http.post('/wallPaperDetailPost', this.ruleForm.id).then(res => {
        this.ruleForm = res.data.data
      })
    },
    // 提交表单
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$http.post('/changeWallPaper', this.ruleForm).then(res => {
            const codeAndMessage = res.data
            if (codeAndMessage.code === '0000') {
              this.$message({
                message: '修改成功',
                type: 'success'
              });
              this.$router.push('/admin/images')
            }
          })
          // 定时器
          setTimeout(() => {
            this.ruleForm = {}
          }, 300)
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    // 返回壁纸列表
    backImages() {
      this.$router.push('/admin/images')
    }

  },
  created() {
    this.getOneImage()
    this.getCategory()
  }
}
</script>

<style scoped>

</style>
