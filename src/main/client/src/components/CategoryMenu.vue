<template>
  <div class="flex flex-col">
    <div v-for="category in categories" :key="category.id">
      <router-link :to="{path: '/products', query: { category: category.slug}}">
        {{ category.name }}
      </router-link>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex"

export default {
  name: "CategoryMenu",
  created() {
    this.loadCategories()
  },
  mounted() {
    this.latestRoute = this.$route.query.category
  },
  methods: {
    loadCategories() {
      this.$store.dispatch("catalogStore/loadCategories")
    },
  },
  computed: {
    ...mapGetters({
      categories: "catalogStore/getCategories",
    }),
  },
}
</script>

<style scoped>

</style>
