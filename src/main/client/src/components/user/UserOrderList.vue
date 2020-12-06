<template>
  <order-list :orders="orders" />
</template>

<script>
import OrderList from "@/components/user/OrderList.vue"
import { mapGetters } from "vuex"

export default {
  name: "UserOrderList",
  components: {
    OrderList,
  },
  created() {
    this.$store.dispatch("ordersStore/loadUserOrders")
  },
  computed: {
    ...mapGetters({
      orders: "ordersStore/getUserOrders",
    }),
  },
}
</script>

<style scoped>

</style>
