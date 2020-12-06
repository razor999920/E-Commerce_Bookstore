<template>
  <div>
    <div>
      <t-table
        variant="demo"
        :headers="headers"
        :data="orders"
      ></t-table>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex"

export default {
  name: "OrderList",
  data() {
    return {
      headers: [
        {
          value: "firstname",
          text: "First Name",
        },
        {
          value: "lastname",
          text: "Last Name",
        },
        {
          value: "purchasedDate",
          text: "Purchase Date",
        },
        {
          value: "total",
          text: "total",
        },
      ],
    }
  },
  created() {
    this.loadOrders()
  },
  methods: {
    loadOrders() {
      this.$store.dispatch("ordersStore/loadOrders")
    },
  },
  computed: {
    ...mapGetters({
      orders: "ordersStore/getOrders"
    })
  }
}
</script>

<style scoped>

</style>
