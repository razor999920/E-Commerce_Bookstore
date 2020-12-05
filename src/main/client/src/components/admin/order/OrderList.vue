<template>
  <div>
    <div>
      orders
      <t-table
        variant="demo"
        :headers="headers"
        :data="orders"
      ></t-table>
    </div>
  </div>
</template>

<script>
export default {
  name: "OrderList",
  data() {
    return {
      headers: [],
      orders: [],
    }
  },
}
</script>

<style scoped>

</style>
