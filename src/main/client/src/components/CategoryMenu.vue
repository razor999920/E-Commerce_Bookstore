<template>
  <div class="flex flex-col pl-14 pt-12">
    <div class="text-lg py-3" v-for="category in categories" :key="category.slug">
      <router-link exact-active-class="bg-black text-gray-200"
                   class="hover:bg-black hover:text-gray-200 px-3 py-2 text-md font-medium"
                   :to="{path: '/products', query: { category: category.name}}">
        {{ category.name }}
      </router-link>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex"

export default {
  name: "CategoryMenu",
  data() {
    return {
      selected: false,
    }
  },
  created() {
    this.loadCategories()
  },
  mounted() {
    // this.selected = this.$route.query.category
  },
  methods: {
    loadCategories() {
      this.$store.dispatch("catalogStore/loadCategories")
    },
    onSelect() {
      this.selected = true
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
