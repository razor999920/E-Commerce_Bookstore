<template>
  <div>
    <auth-component @loginSuccess="onLoginSuccess()" />
  </div>
</template>

<script>
import AuthComponent from "@/components/auth/AuthComponent.vue"

export default {
  name: "login",

  components: { AuthComponent },

  data() {
    return {
      loggedIn: false,
    }
  },

  methods: {
    onLoginSuccess() {
      this.$router.push("/")
    },
  },
}
</script>
