<template>
  <nav class="fixed z-40 w-full bg-white">
    <div class="">
      <div class="grid grid-cols-12 border-b-2 border-gray-100 py-4 px-8">
        <div class="col-span-2 flex items-center justify-center">
          <router-link to="/" class="flex-shrink-0 focus:outline-none">
            <img class="h-10 w-22" src="@/assets/logoMomPop.png" alt="Workflow">
          </router-link>
        </div>
        <div class="col-span-10 flex items-center justify-between">
          <div class="flex-1">
            <t-input @keyup.enter="handleSearch()" v-model="searchTerm"
                     class="w-full py-3 px-6 border-1 border-gray-300 rounded-md" variant="demo"
                     placeholder="Title, ISBN, Author,..." />
          </div>
          <div class="">
            <div class="ml-4 flex items-center md:ml-6">
              <div class="ml-3 relative">
                <div class="flex items-center justify-center">
                  <router-link class="flex" to="/cart">
                    <svg class="w-8 h-8" fill="none" stroke="currentColor" viewBox="0 0 24 24"
                         xmlns="http://www.w3.org/2000/svg">
                      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                            d="M3 3h2l.4 2M7 13h10l4-8H5.4M7 13L5.4 5M7 13l-2.293 2.293c-.63.63-.184 1.707.707 1.707H17m0 0a2 2 0 100 4 2 2 0 000-4zm-8 2a2 2 0 11-4 0 2 2 0 014 0z"></path>
                    </svg>
                    {{ cartSummary.quantity }}
                  </router-link>

                  <div class="flex ml-3" v-if="isSessionActive">
                    <router-link v-if="isAdmin" to="/admin/dashboard"
                                 class="ml-3 w-full flex items-center justify-center px-4 py-2 border border-transparent shadow-sm text-base font-medium text-white bg-black hover:bg-gray-800 hover:text-gray-200">
                      Admin
                    </router-link>
                    <router-link to="/orders"
                                 class="ml-3 w-full flex items-center justify-center px-4 py-2 border border-transparent shadow-sm text-base font-medium text-white bg-black hover:bg-gray-800 hover:text-gray-200">
                      Orders
                    </router-link>
                    <button @click="handleLogout()"
                            class="ml-3 w-full flex items-center justify-center px-4 py-2 border border-transparent shadow-sm text-base font-medium text-white bg-black hover:bg-gray-800 hover:text-gray-200">
                      Logout
                    </button>

                  </div>
                  <div class="ml-3" v-else>
                    <router-link to="/login"
                                 class="w-full flex items-center justify-center px-4 py-2 border border-transparent shadow-sm text-base font-medium text-white bg-black hover:bg-gray-800 hover:text-gray-200">
                      Join
                    </router-link>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </nav>
</template>

<script>
import { mapGetters } from "vuex"

export default {
  name: "navigation",
  data() {
    return {
      searchTerm: "",
    }
  },
  methods: {
    handleSearch() {
      if (this.searchTerm && this.searchTerm !== "") {
        this.$store.dispatch("catalogStore/searchBooks", this.searchTerm)
      }
    },
    handleOrders() {
      this.$router.push({ path: "/orders" })
    },
    async handleLogout() {
      try {
        await this.$store.dispatch("authStore/logout")
        await this.$store.dispatch("cartStore/clearCart", false)
        this.$notify({
          group: "all",
          type: "success",
          text: "You have been logged out.",
        })
      } catch (e) {
        this.$notify({
          group: "all",
          type: "error",
          text: "We could not log you out at the moment.",
        })
      }
    },
  },

  computed: {
    ...mapGetters({
      isSessionActive: "authStore/isSessionActive",
      username: "authStore/getUsername",
      cartSummary: "cartStore/getNumberOfItems",
      isAdmin: "authStore/isAdmin",
    }),
  },
}
</script>
