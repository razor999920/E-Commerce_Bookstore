<template>
  <div>
    <div v-if="!isSessionActive">
      <div v-if="showLogin">
        <div>Login</div>
        <payment-login />
        <div>
          Don't have an account?
          <button @click="showLogin = false">Sign up</button>
        </div>
      </div>
      <div v-else>
          <payment-register />
      </div>
    </div>
    <div v-else>Fuck Marc</div>
  </div>
</template>

<script>
import { mapGetters } from "vuex"
import moment from "moment"
import PaymentLogin from "./PaymentLogin.vue"
import PaymentRegister from "./PaymentRegister.vue"
import localStore from "../../utils/localStore"

export default {
  components: { PaymentLogin, PaymentRegister },

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
          text: e.message,
        })
      }
    },
  },
  computed: {
    ...mapGetters({
      isSessionActive: "authStore/isSessionActive",
    }),
  },
}
</script>
