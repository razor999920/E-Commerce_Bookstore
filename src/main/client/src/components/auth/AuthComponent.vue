<template>
  <div class="min-h-screen flex items-center justify-center">
    <div v-if="showLogin">
      <login-component @login="login" />
      <div class="mt-3">
        Don't have an account?
        <button class="underline focus:outline-none" @click="showLogin = false">Sign up</button>
      </div>
    </div>
    <div v-else>
      <register-component @register="register" />
      <div class="mt-3">
        Already have an account?
        <button class="underline focus:outline-none" @click="showLogin = true">Sign in</button>
      </div>
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
  components: {
    RegisterComponent,
    LoginComponent,
  },
  data() {
    return {
      showLogin: false,
    }
  },
  methods: {
    finalize() {
      this.$emit("loginSuccess")
    },
    async login(info) {
      try {
        await this.$store.dispatch("authStore/login", info)
        await this.$store.dispatch("cartStore/initializeCart")
        this.$emit("loginSuccess")

        localStore.setSessionTimeout(moment()
          .format())
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

<style>
.form_error {
  color: red;
  font-size: 0.75em;
  padding-left: 10px;
}
</style>
