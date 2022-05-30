<template>
  <div>
    <div class="all-margin-top-true">
      <router-view/>
    </div>
  </div>
</template>

<script>
import NavigationBar from "@/components/NavigationBar";
import Footer from "@/components/Footer"

export default {
  components: {
    NavigationBar,
    Footer
  }
}

</script>

<style>
.all-margin-top-true {

}

</style>
