<template>
  <div>
    <h1>个人信息</h1>
    <h3>用户ID:{{ user.userId }}</h3>
    <h3>用户名:{{ user.userName }}</h3>
    <!--<h3>用户密码:{{ user.userPassword }}</h3>-->
    <!--<h3>用户盐:{{ user.userSalt }}</h3>-->
    <h3 style="color: red">加密方式:MD5盐 盐值:ByteSource.Util.bytes(UserName + salt) 二次散列 Hex密码加密编码</h3>
  </div>
</template>

<script>
export default {
  name: "Home",
  data() {
    return {
      user: {
        userId: '',
        userName: '',
        userPassword: '',
        userSalt: '',
        userLocked: ''
      }
    }
  },
  methods: {
    findUserById() {
      this.userId = this.$store.state.userId
      this.$http.get('/admin/findUserById', {
        params: {
          userId: this.userId
        }
      }).then(res => {
        this.user = res.data.data
      })
    }
  },
  created() {
    this.findUserById()
  }

}
</script>

<style scoped>

</style>
