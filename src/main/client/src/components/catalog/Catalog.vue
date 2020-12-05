<template>
  <div>
    <div class="flex flex-wrap">
      <router-link v-for="item in items" :key="item.id" :to="{path: '/products', query:{ id: item.id }}">
        <div class="max-w-sm p-4">
          <div class="card flex flex-col justify-center p-10 bg-white rounded-lg shadow-lg">
            <div class="prod-img">
              <img v-bind:src="item.imageUrl" class="w-full object-cover object-center" />
            </div>
            <div class="prod-title mt-4 h-20">
              <p class="text-base uppercase text-gray-900 font-bold">{{ item.title }}</p>
              <p class="uppercase text-sm text-gray-400">
                by {{ item.author }}
              </p>
            </div>
            <div class="prod-info grid gap-10">
              <div class="flex flex-col md:flex-row justify-between items-center text-gray-900">
                <p class="font-bold text-xl">${{ item.price }}</p>
                <button @click="addToCart(item.id, item.title, item.author, item.price)"
                        class="px-6 py-2 transition ease-in duration-200 uppercase rounded-full hover:bg-gray-800 hover:text-white border-2 border-gray-900 focus:outline-none">
                  Add to cart
                </button>
              </div>
            </div>
          </div>
        </div>
      </router-link>
    </div>
    <div class="flex items-center justify-center p-5">
      <t-button @click="loadMore()">{{ isLast ? "No more" : "Load more" }}</t-button>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex"

export default {
  name: "Catalog",
  data() {
    return {
      currentCategory: "",
    }
  },
  created() {
    this.loadItems()
  },
  methods: {
    loadItems() {
      const { category } = this.$route.query
      if (this.currentCategory !== category) {
        this.currentCategory = category
        this.$store.dispatch("catalogStore/resetCategory")
      }
      this.$store.dispatch("catalogStore/loadBooks", category)
    },
    loadMore() {
      const { category } = this.$route.query
      this.$store.dispatch("catalogStore/loadBooks", category)
    },
    addToCart(idComponent, titleComponent, authorComponent, priceComponent) {
      this.$store.dispatch("cartStore/addToCart", {
        id: idComponent,
        title: titleComponent,
        author: authorComponent,
        price: priceComponent,
        quantity: 1,
      })
    },
  },
  computed: {
    ...mapGetters({
      items: "catalogStore/getItems",
      isLast: "catalogStore/getLast",
    }),
  },
  watch: {
    $route() {
      this.loadItems()
    },
  },
}
</script>

<style scoped>

</style>
