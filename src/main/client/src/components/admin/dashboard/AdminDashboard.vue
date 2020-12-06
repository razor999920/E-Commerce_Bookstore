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
        :headers="best"
        :data="bestsellers"
      ></t-table>
    </div>
    <div>
      <h2 class="text-xl font-bold leading-7 mb-3 mt-3">
        Buy Stats
      </h2>
      <t-table
        variant="tail"
        :headers="['username', 'spending']"
        :data="buystats"
      ></t-table>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex"

export default {
  name: "AdminDashboard",
  async created() {
    await this.loadBestSeller()
    await this.loadBuyStats()
    await this.loadSales()
  },
  data() {
    return {
      best: [
        {
          value: "author",
          text: "author",
        },
        {
          value: "title",
          text: "title",
        },
        {
          value: "isbn",
          text: "isbn",
        },
        {
          value: "quantity",
          text: "quantity",
        },
        {
          value: "sale",
          text: "sale",
        },
      ]
    }
  },
  methods: {
    loadSales() {
      this.$store.dispatch("adminStore/loadSales")
    },
    async loadBestSeller() {
      await this.$store.dispatch("adminStore/loadBestSellers")
    },
    async loadBuyStats() {
      await this.$store.dispatch("adminStore/loadBuyStats")
    },
  },
  computed: {
    ...mapGetters({
      sales: "adminStore/getSales",
      bestsellers: "adminStore/getBestSellers",
      buystats: "adminStore/getBuyStats"
    }),
  },
}
</script>

<style scoped>

</style>
