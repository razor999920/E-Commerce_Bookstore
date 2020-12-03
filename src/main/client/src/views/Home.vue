<template>
  <div id="home">
    <navigation />
    <div class="flex mt-4">
      <category-menu />
      <catalog />
    </div>
  </div>
</template>
<script>
import Navigation from "@/components/MainNav.vue"
import CategoryMenu from "@/components/CategoryMenu.vue"
import Catalog from "@/components/catalog/Catalog.vue"

export default {
  name: "home",
  components: {
    Catalog,
    CategoryMenu,
    Navigation,
  },
}
</script>
