<template>
  <div>
    <nav class="bg-gray-800">
      <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div class="flex items-center justify-between h-16">
          <div class="flex items-center">
            <router-link to="/" class="col-span-2 flex items-center">
              <div class="flex-shrink-0">
                <img class="h-8 w-8" src="https://tailwindui.com/img/logos/workflow-mark-indigo-500.svg" alt="Workflow" />
              </div>
            </router-link>
            <div class="hidden md:block">
              <div class="ml-10 flex items-baseline space-x-4">
                <router-link to="/admin/dashboard" class="text-gray-300 hover:bg-gray-700 hover:text-white px-3 py-2 rounded-md text-sm font-medium"> Dashboard </router-link>
                <router-link to="/admin/books" class="text-gray-300 hover:bg-gray-700 hover:text-white px-3 py-2 rounded-md text-sm font-medium"> Books </router-link>
                <router-link to="/admin/orders" class="text-gray-300 hover:bg-gray-700 hover:text-white px-3 py-2 rounded-md text-sm font-medium"> Orders </router-link>
                <router-link to="/admin/users" class="text-gray-300 hover:bg-gray-700 hover:text-white px-3 py-2 rounded-md text-sm font-medium"> Users </router-link>
              </div>
            </div>
          </div>
          <div class="hidden md:block">
            <div class="ml-4 flex items-center md:ml-6">
              <!-- Profile dropdown -->
              <div class="ml-3 relative">
                <t-dropdown>
                  <div slot="trigger" slot-scope="{ mousedownHandler, focusHandler, blurHandler, keydownHandler }">
                    <button
                      id="user-menu"
                      class="max-w-xs bg-gray-800 rounded-full flex items-center text-sm focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-offset-gray-800 focus:ring-white"
                      aria-label="User menu"
                      aria-haspopup="true"
                      @mousedown="mousedownHandler"
                      @focus="focusHandler"
                      @blur="blurHandler"
                      @keydown="keydownHandler"
                    >
                      <img
                        class="h-8 w-8 rounded-full mr-2"
                        src="https://images.unsplash.com/photo-1472099645785-5658abf4ff4e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=facearea&facepad=2&w=256&h=256&q=80"
                        alt=""
                      />
                    </button>
                  </div>
                  <div slot-scope="{ hide }" class="py-1 rounded-md shadow-xs">
                    <a
                      href="#"
                      @click="
                        handleLogout()
                        hide()
                      "
                      class="block px-4 py-2 text-sm leading-5 text-gray-700 hover:bg-gray-100 focus:outline-none focus:bg-gray-100 transition duration-150 ease-in-out"
                      role="menuitem"
                    >
                      Sign out
                    </a>
                  </div>
                </t-dropdown>
              </div>
            </div>
          </div>
        </div>
      </div>
    </nav>
    <header class="bg-white shadow">
      <div class="max-w-7xl mx-auto py-6 px-4 sm:px-6 lg:px-8">
        <h1 class="text-3xl font-bold leading-tight text-gray-900">
          {{ currentPage }}
        </h1>
      </div>
    </header>
  </div>
</template>

<script>
export default {
  name: "AdminNav",
  data() {
    return {
      currentPage: "Dashboard",
    }
  },
  methods: {
    async handleLogout() {
      try {
        await this.$store.dispatch("authStore/logout")
        this.$notify({
          group: "all",
          type: "success",
          text: "You have been logged out.",
        })
        await this.$router.push({ path: "/" })
      } catch (e) {
        this.$notify({
          group: "all",
          type: "error",
          text: "We could not log you out at the moment.",
        })
      }
    },
  },
  watch: {
    $route() {
      this.currentPage = this.$route.name
    },
  },
}
</script>

<style scoped>
</style>
