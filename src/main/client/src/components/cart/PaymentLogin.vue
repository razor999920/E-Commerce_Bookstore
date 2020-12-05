<template>
  <div>
    <form @submit.prevent>
      <t-input-group label="Username">
        <t-input id="login" name="login" v-model="login" />
        <span class="form_error" v-if="!$v.login.required && $v.login.$dirty">Username or email required!</span>
      </t-input-group>
      <t-input-group label="Password">
        <t-input type="password" id="password" name="password" v-model="password" />
        <span class="form_error" v-if="!$v.password.required && $v.password.$dirty">Password required!</span>
      </t-input-group>
      <button class="w-full py-3 mt-6 font-medium tracking-widest text-white uppercase bg-black shadow-lg focus:outline-none hover:bg-gray-900 hover:shadow-none" type="button" @click="handleLogin()">
        Sign in
      </button>
    </form>
  </div>
</template>

<script>
import { required } from "vuelidate/lib/validators"

export default {
  name: "PaymentLoginComponent",

  data() {
    return {
      login: "",
      password: "",
    }
  },

  validations: {
    login: {
      required,
    },
    password: {
      required,
    },
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

<style>

</style>
