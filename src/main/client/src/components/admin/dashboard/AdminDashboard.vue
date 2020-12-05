<template>
  <div>
    <div>
      <h2 class="text-xl font-bold leading-7 mb-3">
        Sales per month
      </h2>
      <t-table
        variant="tail"
        :headers="['month', 'sales']"
        :data="sales"
      ></t-table>
    </div>
    <div>
      <h2 class="text-xl font-bold leading-7 mb-3 mt-3">
        Bestsellers
      </h2>
      <t-table
        variant="tail"
        :headers="['title', 'author']"
        :data="[['book a', 'author a']]"
      ></t-table>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex"

export default {
  name: "AdminDashboard",
  async created() {
    await this.loadSalesPerMonth()
  },
  data() {
    return {}
  },
  methods: {
    async loadSalesPerMonth() {
      await this.$store.dispatch("adminStore/loadSalesPerMonth")
    },
  },
  computed: {
    ...mapGetters({
      sales: "adminStore/getSales",
    }),
  },
}
</script>

<style scoped>

</style>
