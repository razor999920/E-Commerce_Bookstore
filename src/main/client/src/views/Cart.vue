<template>
  <div>
    <div>
      <cart-component />
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
