<template>
  <div>
    <div class="flex flex-1 flex-wrap">
      <router-link v-for="item in items" :key="item.id" :to="{ path: '/products', query: { id: item.id, title: item.title, author: item.author, price: item.price } }">
        <div class="m-3 p-3 border-gray-500 border-2">
          <div>{{ item.title }}</div>
          <div>by {{ item.author }}</div>
          <div>{{ item.price }}</div>
        </div>
        <button @click="addtoCart(item.id, item.title, item.author, item.price)">+</button>
      </router-link>
    </div>
    <div>
      <button @click="loadMore()">load more</button>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex"

export default {
  name: "Catalog",
  data() {
    return {
      currentRoute: "",
    }
  },
  created() {
    this.loadItems()
  },
  methods: {
    loadItems() {
      const { category } = this.$route.query
      this.$store.dispatch("catalogStore/loadItems", category)
    },
    loadMore() {
      const { category } = this.$route.query
      this.$store.dispatch("catalogStore/loadMore", category)
    },
    addtoCart(idComponent, titleComponent, authorComponent, priceComponent) {
      this.$store.dispatch("cartStore/addToCart", {
        id: idComponent,
        title: titleComponent,
        author: authorComponent,
        price: priceComponent,
        quantity: 1,
      })
    },
    addQuantity() {
      this.$store.dispatch("cartStore/addQuantity", 1)
    },
  },
  computed: {
    ...mapGetters({
      items: "catalogStore/getItems",
      cartItems: "cartStore/getItems",
    }),
  },
  watch: {
    $route() {
      this.loadItems()
    },
  },
}
</script>

<style scoped></style>
