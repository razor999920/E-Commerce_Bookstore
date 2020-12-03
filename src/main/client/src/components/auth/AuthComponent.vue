<template>
  <div class="min-h-screen flex items-center justify-center">
    <div v-if="showLogin">
      <login-component @login="login" />
      <div>
        Don't have an account?
        <button @click="showLogin = false">Sign up</button>
      </div>
    </div>
    <div v-else>
      <register-component @register="register" />
      Already have an account?
      <button @click="showLogin = true">Sign in</button>
    </div>
  </div>
</template>

<script>
import LoginComponent from "@/components/auth/LoginComponent.vue"
import RegisterComponent from "@/components/auth/RegisterComponent.vue"
import localStore from "@/utils/localStore"
import moment from "moment"

export default {
  name: "AuthComponent",
  components: { RegisterComponent, LoginComponent },
  data() {
    return {
      showLogin: true,
    }
  },
  methods: {
    finalize() {
      this.$emit("loginSuccess")
    },
    async login(info) {
      try {
        await this.$store.dispatch("authStore/login", info)
        this.$emit("loginSuccess")

        localStore.setSessionTimeout(moment().format())
        this.$notify({
          group: "all",
          type: "success",
          text: "You have been logged in.",
        })
      } catch (e) {
        this.$notify({
          group: "all",
          type: "error",
          text: "Incorrect credentials. Please try again.",
        })
      }
    },
    async register(info) {
      try {
        await this.$store.dispatch("authStore/register", info)

        this.showLogin = true
        this.$notify({
          group: "all",
          type: "success",
          text: "Registration successful.",
        })
      } catch (e) {
        this.$notify({
          group: "all",
          type: "error",
          text: "User could not be created at this time. Please try again later.",
        })
      }
    },
  },
}
</script>

<style scoped>

</style>
