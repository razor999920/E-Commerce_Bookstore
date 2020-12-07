<template>
  <div>
    <form @submit.prevent>
      <div>
        <label for="login" class="sr-only">Username or email</label>
        <input id="login" name="login" type="text" placeholder="Username or email"
               v-model="login">
        <span class="text-danger" v-if="!$v.login.required && $v.login.$dirty">Username or email required</span>
      </div>
      <div>
        <label for="password" class="sr-only">Password</label>
        <input type="password" id="password" name="password" placeholder="Password" v-model="password">
        <span class="text-danger" v-if="!$v.password.required && $v.password.$dirty">Password required!</span>
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
import { required } from 'vuelidate/lib/validators'

export default {
  name: "LoginComponent",

  data() {
    return {
      login: "",
      password: "",
    }
  },

  validations: {
    login: {
      required
    },
    password: {
      required
    }
  },

  methods: {
    async handleLogin() {
      this.$v.$touch()
      if (!this.$v.$invalid) {
        this.$emit("login", {
          login: this.login,
          password: this.password,
        })
      }
    },
  },
}
</script>

<style scoped>

</style>
