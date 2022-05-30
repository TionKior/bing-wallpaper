<template>
  <router-link to="/admin/register">
    <el-button type="primary">
      <i class="el-icon-plus"></i>
      新增用户
    </el-button>
  </router-link>
  <el-table
      :data="userList"
      border
      style="width: 100%">
    <el-table-column
        prop="userId"
        label="用户ID"
        width="180">
    </el-table-column>
    <el-table-column
        prop="userName"
        label="姓名"
        width="180">
    </el-table-column>
    <el-table-column
        prop="userPassword"
        label="密码(加密后)"
        width="320">
    </el-table-column>
    <el-table-column
        prop="userSalt"
        label="用户盐"
        width="180">
    </el-table-column>
    <el-table-column
        prop="userIntegral"
        label="用户积分"
        width="128">
    </el-table-column>
    <el-table-column
        prop="userLocked"
        label="是否锁定"
        width="150">
      <template #default="scope">
        <isLocked v-if="scope.row.userLocked === '0'">否</isLocked>
        <isLocked v-if="scope.row.userLocked === '1'">是</isLocked>
      </template>
    </el-table-column>
    <el-table-column
        fixed="right"
        label="操作"
        width="180">
      <template #default="scope">
        <a @click.prevent="deleteRow(scope.$index, userList,scope.row.userId)">
          删除用户
        </a>
        <br/>
        <a v-if="scope.row.userLocked === '0'" @click="lockUser(scope.row.userId)">
          锁定该用户
        </a>
        <a v-if="scope.row.userLocked === '1'" @click="unLockUser(scope.row.userId)">
          解锁该用户
        </a>
      </template>
    </el-table-column>
  </el-table>
</template>


<script>
export default {
  data() {
    return {
      userList: [
        {
          userId: '',
          userName: '',
          userPassword: '',
          userSalt: '',
          userIntegral: '',
          userLocked: ''
        }
      ]
    }
  },
  methods: {
    deleteRow(index, rows, userId) {
      this.$msgbox.confirm('确认删除吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http.get('/admin/deleteUserById', {
          params: {
            userId: userId
          }
        }).then(res => {
          let codeAndMessage = res.data
          if (codeAndMessage.code === '0000') {
            rows.splice(index, 1);
            this.$message({
              type: 'success',
              message: '删除成功!'
            });
            this.getUserList()
          } else {
            alert(codeAndMessage.msg)
          }
        })
      }).catch(() => {
      });
    },
    getUserList() {
      this.$http.get('/admin/userList').then(res => {
        // for (let i = 0; i < res.data.data.length; i++) {
        //   if (res.data.data[i].userLocked === '0') {
        //     res.data.data[i].userLocked = '否'
        //   } else {
        //     res.data.data[i].userLocked = '是'
        //   }
        // }
        this.userList = res.data.data;
      })
    },
    lockUser(userId) {
      this.$msgbox.confirm('确认解锁该用户吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http.post('/admin/lockUser', userId).then(res => {
              let codeAndMessage = res.data
              if (codeAndMessage.code === '0000') {
                this.$message({
                  type: 'success',
                  message: '锁定成功!'
                });
              } else {
                this.$message({
                  type: 'error',
                  message: codeAndMessage.msg
                });
              }
              this.getUserList()
            }
        )
      }).catch(() => {
      });
    },
    unLockUser(userId) {
      this.$msgbox.confirm('确认解锁该用户吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http.post('/admin/unLockUser', userId).then(res => {
              let codeAndMessage = res.data
              if (codeAndMessage.code === '0000') {
                this.$message({
                  type: 'success',
                  message: '解锁成功!'
                });
              } else {
                this.$message({
                  type: 'error',
                  message: codeAndMessage.msg
                });
              }
              this.getUserList()
            }
        )
      }).catch(() => {
      });
    }
  },
  created() {
    this.getUserList();
  }
}
</script>

<style scoped>
a {
  text-decoration: none;
  cursor: pointer;
}
</style>
