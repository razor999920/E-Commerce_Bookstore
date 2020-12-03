<template>
  <div>
    <form @submit.prevent>
      <div>
        <label for="username" class="sr-only">Username</label>
        <input type="text" name="username" id="username" placeholder="Username" v-model="username" />
      </div>
      <div>
        <label for="email" class="sr-only">Email</label>
        <input type="text" name="email" id="email" placeholder="Email" v-model="email" />
      </div>
      <div>
        <label for="password" class="sr-only">Password</label>
        <input type="password" name="password" id="password" placeholder="Password" v-model="password" />
      </div>
      <div>
        <label for="confirm-password" class="sr-only">Password</label>
        <input type="password" name="confirm-password" id="confirm-password" placeholder="Confirm password"
               v-model="confirmPassword" />
      </div>
      <div>
        <button type="button" @click="handleRegister()">Sign up</button>
      </div>
    </form>
  </div>
</template>

<script>
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
  methods: {
    handleRegister() {
      if (this.password !== this.confirmPassword) return

      this.$emit("register", {
        username: this.username,
        email: this.email,
        password: this.password,
        confirmPassword: this.confirmPassword
      })
    },
  },
}
</script>

<style scoped>

</style>
