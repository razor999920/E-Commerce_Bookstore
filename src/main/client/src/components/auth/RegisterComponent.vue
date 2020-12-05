<template>
  <div class="">
    <form @submit.prevent>
      <t-input-group label="Username">
        <t-input id="username" name="username" v-model="username" />
        <span class="form_error" v-if="!$v.username.required && $v.username.$dirty">Field is required!</span>
        <span class="form_error"
              v-if="!$v.username.minLength && $v.username.$dirty">Username must have at least 6 letters!</span>
      </t-input-group>
      <t-input-group label="Email">
        <t-input id="email" name="email" v-model="email" />
        <span class="form_error" v-if="(!$v.email.required || !$v.email.email) && $v.email.$dirty">Valid Email is required!</span>
      </t-input-group>
      <t-input-group label="Password">
        <t-input type="password" id="password" name="password" v-model="password" />
        <span class="form_error" v-if="!$v.password.required && $v.password.$dirty">Password is required!</span>
        <span class="form_error" v-if="!$v.password.minLength && $v.password.$dirty">Password must have at least 6 characters!</span>
        <span class="form_error" v-if="!$v.password.maxLength && $v.password.$dirty">Password should not have more than 12 characters!</span>
      </t-input-group>
      <t-input-group label="Confirm Password">
        <t-input type="password" id="confirmPassword" name="confirmPassword" v-model="confirmPassword" />
         <span class="form_error" v-if="!$v.confirmPassword.sameAs && $v.confirmPassword.$dirty">Passwords must be identical.</span>
      </t-input-group>
      <div>
        <button
          class="w-full py-3 mt-6 font-medium tracking-widest text-white uppercase bg-black shadow-lg focus:outline-none hover:bg-gray-900 hover:shadow-none"
          type="button"
          @click="handleRegister()"
        >
          Sign up
        </button>
      </div>
    </form>
  </div>
</template>

<script>
import {
  required, minLength, email, maxLength, sameAs
} from "vuelidate/lib/validators"

export default {
  name: "RegisterComponent",
  data() {
    return {
      username: "",
      email: "",
      password: "",
      confirmPassword: "",
    }
  },

  validations: {
    username: {
      required,
      minLength: minLength(6),
    },
    email: {
      required,
      email,
    },
    password: {
      required,
      minLength: minLength(6),
      maxLength: maxLength(12),
    },
    confirmPassword: {
      sameAs: sameAs("password"),
    },
  },

  methods: {
    handleRegister() {
      this.$v.$touch()
      if (!this.$v.$invalid) {
        this.$emit("register", {
          username: this.username,
          email: this.email,
          password: this.password,
          confirmPassword: this.confirmPassword,
        })
      }
    },
  },
}
</script>

<style scoped>
</style>
