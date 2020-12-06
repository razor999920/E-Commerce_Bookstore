import Vue from "vue"

import api from "@/utils/api"

export const SET_ORDERS = "SET_ORDERS"

export default {
  namespaced: true,
  state: {
    orders: [],
  },
  actions: {
    async loadOrders({ commit }) {
      try {
        const response = await Vue.prototype.$http.get(api.getOrders)
        commit(SET_ORDERS, response.data)
      } catch (e) {
        throw new Error(e.response.data.detail)
      }
    },
  },
  mutations: {
    [SET_ORDERS](state, orders) {
      state.orders = orders
    },
  },
  getters: {
    getOrders(state) {
      return state.orders
    },
  },
}
