<template>
  <div>
    <div><payment-component /></div>
  </div>
</template>

<script>
import { mapGetters } from "vuex"
import PaymentComponent from "../components/payment/PaymentComponent.vue"

export default {
  components: { PaymentComponent },
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
