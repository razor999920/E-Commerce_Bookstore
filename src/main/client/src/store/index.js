import Vue from "vue"
import Vuex from "vuex"

import AuthStore from "@/store/auth"
import CatalogStore from "@/store/catalog"
import AdminStore from "@/store/admin"

Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    authStore: AuthStore,
    catalogStore: CatalogStore,
    adminStore: AdminStore,
  },
})
