<template>
  <div id="main-nav" class="flex justify-between">
    <div>
      Logo
    </div>
    <div class="flex">
      <div v-if="!isSessionActive">
        <router-link to="/login" tag="button">Login</router-link>
      </div>
      <div v-else>
        <button @click="handleLogout()">Hello, {{ username }}! Logout</button>
      </div>
      <div class="ml-2">
        Cart (0)
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex"

export default {
  name: "navigation",

  methods: {
    async handleLogout() {
      try {
        await this.$store.dispatch("authStore/logout")
        this.$notify({
          group: "all",
          type: "success",
          text: "You have been logged out.",
        })
      } catch (e) {
        this.$notify({
          group: "all",
          type: "error",
          text: "We could not log you out at the moment.",
        })
      }
    },
  },

  computed: {
    ...mapGetters({
      isSessionActive: "authStore/isSessionActive",
      username: "authStore/getUsername",
    }),
  },
}
</script>
