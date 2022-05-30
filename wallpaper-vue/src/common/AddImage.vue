<template>
  <div>
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

      <el-form-item label="上传图片" ref="uploadElement" prop="imageUrl">
        <el-input v-model="ruleForm.file" v-if="false"></el-input>
        <el-upload
            class="file-uploader"
            ref="upload"
            :show-file-list="false"
            :action="uploadUrl"
            :before-upload="beforeUpload"
            :on-change="handleChange"
            :auto-upload="false"
            :data="ruleForm">
          <img v-if="ruleForm.file" :src="ruleForm.file" class="file" alt="">
          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
      </el-form-item>
    </el-form>

    <el-button type="primary" @click="submitForm('ruleForm')">提交壁纸</el-button>
  </div>
</template>

<script>
export default {
  name: "AddImage",
  data() {
    return {
      uploadUrl: 'http://localhost:8080/addWallPaper',
      ruleForm: {
        file: '',
        imageName: '',
        author: '',
        address: '',
        categoryId: '1',
        userId: this.$store.state.userId
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
      this.$http.get('/classify').then(response => {
        this.categoryList = response.data.data
      })
    },

    // 提交表单
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (this.ruleForm.file === '') {
          this.$message({
            message: '请上传头像',
            type: 'warning'
          });
          return false
        }
        if (valid) {
          this.$refs.upload.submit()
          this.imageUrl = ''
          this.$message({
            message: '提交成功',
            type: 'success'
          });
          // 定时器
          setTimeout(() => {
            this.ruleForm = {}
            this.ruleForm.file = ''
          }, 300)
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

</style>
