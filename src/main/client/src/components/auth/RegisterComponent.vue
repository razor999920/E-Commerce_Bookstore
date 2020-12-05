<template>
  <div class="w-80">
    <form @submit.prevent>
      <t-input-group label="Username">
        <t-input id="username" name="username" v-model="username" />
      </t-input-group>
      <t-input-group label="Email">
        <t-input id="email" name="email" v-model="email" />
      </t-input-group>
      <t-input-group label="Password">
        <t-input id="password" name="password" v-model="password" />
      </t-input-group>
      <t-input-group label="Confirm Password">
        <t-input id="confirmPassword" name="confirmPassword" v-model="confirmPassword" />
      </t-input-group>
      <div>
        <button
          class="w-full py-3 mt-6 font-medium tracking-widest text-white uppercase bg-black shadow-lg focus:outline-none hover:bg-gray-900 hover:shadow-none"
          type="button" @click="handleRegister()">Sign up
        </button>
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
        confirmPassword: this.confirmPassword,
      })
    },
  },
}
</script>

<style scoped>

</style>
