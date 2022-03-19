<template>
  <div class="footer">
    <a href="http://www.tionkior.com">Make www.tionkior.com your homepage</a><br/>
    <a href="http://www.tionkior.com">Join the promotion</a>
    <span>|</span><router-link to="/search">TionKior Search</router-link>
    <span>|</span><a href="http://www.tionkior.com">About TionKior</a><br/>
    <p class="bottom">2022 TionKior<br/>
      <a href="#">TionKior subdomain description</a><br/>
      <a href="https://beian.miit.gov.cn/">   晋ICP备2022001922号</a>
    </p>
  </div>
</template>

<script setup>

</script>

<style scoped>
p {
  margin: 0;
  padding: 0;
  list-style: none;
  border: 0;
}

.footer {
  width: 100%;
  height: 150px;
  text-align: center;
  line-height: 30px;
  font-size: 12px;
  background-color: #222222;
}

.footer span {
  margin: 0 5px;
}

.footer .bottom {
  color: #FFFFFF;
}

.footer .bottom a {
  text-decoration: none;
}
</style>