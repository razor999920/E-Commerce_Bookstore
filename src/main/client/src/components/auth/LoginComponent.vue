<template>
  <div>
    <form @submit.prevent>
      <div>
        <label for="login" class="sr-only">Username or email</label>
        <input id="login" name="login" type="text" required placeholder="Username or email"
               v-model="login">
      </div>
      <div>
        <label for="password" class="sr-only">Password</label>
        <input type="password" id="password" name="password" required placeholder="Password" v-model="password">
      </div>
      <div>
        <input type="checkbox" id="remember-me" name="remember-me">
        <label for="remember-me">Remember me</label>
      </div>
      <div>
        <a href="#">Forgot your password?</a>
      </div>
      <div>
        <button type="button" @click="handleLogin()">Sign in</button>
      </div>
    </form>
  </div>
</template>

<script>
export default {
  name: "LoginComponent",

  data() {
    return {
      login: "",
      password: "",
    }
  },

  methods: {
    async handleLogin() {
      this.$emit("login", {
        login: this.login,
        password: this.password,
      })
    },
  },
}
</script>

<style scoped>

</style>
