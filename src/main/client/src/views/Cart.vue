<template>
  <div>
    <div>
      <cart-component />
      <router-link to="/cart/payment" tag="button">Checkout</router-link>
    </div>
  </div>
</template>

<script>
import CartComponent from "@/components/cart/CartComponent.vue"

export default {
  name: "cart",

  components: { CartComponent },

  data() {
    return {
      currentRoute: "",
    }
  },
  method: {},
}
</script>

<style scoped></style>
