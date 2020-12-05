<template>
  <div v-if="isSessionActive">
    <div><payment-component /></div>
  </div>
  <div v-else>
    <auth-component />
  </div>
</template>

<script>
import { mapGetters } from "vuex"
import PaymentComponent from "../components/payment/PaymentComponent.vue"
import AuthComponent from "../components/auth/AuthComponent.vue"

export default {
  components: { PaymentComponent, AuthComponent },
  name: "payment",

  data() {
    return {
      currentRoute: "",
    }
  },
  method: {},
  computed: {
    ...mapGetters({
      isSessionActive: "authStore/isSessionActive",
    }),
  },
}
</script>

<style scoped></style>
