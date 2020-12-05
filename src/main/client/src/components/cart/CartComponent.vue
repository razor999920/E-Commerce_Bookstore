<template>
  <div>
    <div class="flex flex-1 flex-wrap">
      <router-link v-for="item in items" :key="item.id" :to="{ query: { id: item.id } }">
        <div class="m-3 p-3 border-gray-500 border-2">
          <div>{{ item.title }}</div>
          <div>by {{ item.author }}</div>
          <div>{{ item.price }}</div>
          <div>{{ item.quantity }}</div>
        </div>
        <button @click="removeFromCart(item.id)">-</button>
        </router-link>
      <div>Subtotal: {{ subtotal }}</div>
      <router-link to="/products" tag="button">Continue Shopping
      </router-link>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex"

export default {
  methods: {
    removeFromCart(id) {
      this.$store.dispatch("cartStore/removeFromCart", id)
    },
  },
  computed: {
    ...mapGetters({
      items: "cartStore/getItems",
      subtotal: "cartStore/getTotal",
    }),
  },
}
</script>
