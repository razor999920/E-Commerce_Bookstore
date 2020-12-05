<template>
  <div>
    <form @submit.prevent>
      <t-input-group label="Username">
        <t-input id="login" name="login" v-model="login" />
      </t-input-group>
      <t-input-group label="Password">
        <t-input type="password" id="password" name="password" v-model="password" />
      </t-input-group>
      <button
        class="w-full py-3 mt-6 font-medium tracking-widest text-white uppercase bg-black shadow-lg focus:outline-none hover:bg-gray-900 hover:shadow-none"
        type="button" @click="handleLogin()">
        Sign in
      </button>
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
