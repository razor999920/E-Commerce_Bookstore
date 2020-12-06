<template>
  <order-list :orders="orders" />
</template>

<script>
import OrderList from "@/components/user/OrderList.vue"
import { mapGetters } from "vuex"

export default {
  name: "ManageOrder",
  components: { OrderList },
  created() {
    this.$store.dispatch("ordersStore/loadOrders")
  },
  computed: {
    ...mapGetters({
      orders: "ordersStore/getOrders",
    }),
  },
}
</script>

<style scoped>

</style>
