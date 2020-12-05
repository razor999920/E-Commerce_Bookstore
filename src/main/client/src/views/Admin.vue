<template>
  <div>
    <admin-nav />
    <main>
      <div class="max-w-7xl mx-auto py-6 sm:px-6 lg:px-8">
        <router-view></router-view>
      </div>
    </main>
  </div>
</template>

<script>

import AdminNav from "@/components/admin/AdminNav.vue"

export default {
  name: "Admin",
  components: { AdminNav },
}
</script>

<style scoped>

</style>
