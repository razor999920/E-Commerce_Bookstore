<template>
  <div id="home">
    <navigation />
    <main id="main" class="pt-16">
      <div class="bg-grey-darkest relative h-full min-h-screen">
        <category-menu />
      </div>
      <div class="bg-white h-full pt-8">
        <router-view></router-view>
      </div>
    </main>
  </div>
</template>
<script>
import Navigation from "@/components/MainNav.vue"
import CategoryMenu from "@/components/CategoryMenu.vue"

export default {
  name: "home",
  components: {
    CategoryMenu,
    Navigation,
  },
}
</script>

<style scoped>
#main {
  display: grid;
  grid-template-columns: 19rem auto;
}
</style>
