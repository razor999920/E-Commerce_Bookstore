<template>
  <div>
    <form @submit.prevent>
      <div>
        <label for="username" class="sr-only">Username</label>
        <input type="text" name="username" id="username" placeholder="Username" v-model="username" />
        <span class="text-danger" v-if="!$v.username.required && $v.username.$dirty">Field is required</span>
        <span class="text-danger" v-if="!$v.username.minLength && $v.username.$dirty">Username must have at least {{$v.username.$params.minLength.min}} letters.</span>
      </div>
      <div>
        <label for="email" class="sr-only">Email</label>
        <input type="text" name="email" id="email" placeholder="Email" v-model="email" />
        <span class="text-danger" v-if="(!$v.email.required || !$v.email.email) && $v.email.$dirty">Valid Email is required</span>
      </div>
      <div>
        <label for="password" class="sr-only">Password</label>
        <input type="password" name="password" id="password" placeholder="Password" v-model="password" />
        <span class="text-danger" v-if="!$v.password.required && $v.password.$dirty">Password is required!</span>
        <span class="text-danger" v-if="!$v.password.minLength && $v.password.$dirty">Password must have at least {{$v.password.$params.minLength.min}} letters.</span>
        <span class="text-danger" v-if="!$v.password.maxLength && $v.password.$dirty">Password should not have more than {{$v.password.$params.maxLength.max}} letters.</span>
      </div>
      <div>
        <label for="confirm-password" class="sr-only">Password</label>
        <input type="password" name="confirm-password" id="confirm-password" placeholder="Confirm password"
               v-model="confirmPassword" />
        <span class="text-danger" v-if="!$v.confirmPassword.sameAs && $v.confirmPassword.$dirty">Passwords must be identical.</span>
      </div>
      <div>
        <button type="button" @click="handleRegister()">Sign up</button>
      </div>
    </form>
  </div>
</template>

<script>
import {
  required,
  minLength,
  email,
  maxLength,
  sameAs
} from 'vuelidate/lib/validators'

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
      minLength: minLength(6)
    },
    email: {
      required,
      email
    },
    password: {
      required,
      minLength: minLength(6),
      maxLength: maxLength(12)
    },
    confirmPassword: {
      sameAs: sameAs('password')
    }
  },

  methods: {
    handleRegister() {
      this.$v.$touch()
      if (!this.$v.$invalid) {
        this.$emit("register", {
          username: this.username,
          email: this.email,
          password: this.password,
          confirmPassword: this.confirmPassword
        })
      }
    },
    submitForm() {
      this.$v.$touch()
      if (!this.$v.invalid) {
        this.handleRegister()
      }
    },
  }
}
</script>

<style scoped>
</style>
