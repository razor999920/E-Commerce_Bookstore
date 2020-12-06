import Vue from "vue"

import api from "@/utils/api"

export const SET_ORDERS = "SET_ORDERS"
export const SET_USER_ORDERS = "SET_USER_ORDERS"

export default {
  namespaced: true,
  state: {
    orders: [],
    userOrders: [],
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

    async loadUserOrders({ commit }) {
      await Vue.prototype.$http.get(api.getUserOrders)
        .then(res => {
          commit(SET_USER_ORDERS, res.data)
        })
        .catch(err => {
          console.log(err)
        })
    },
  },
  mutations: {
    [SET_ORDERS](state, orders) {
      state.orders = orders
    },
    [SET_USER_ORDERS](state, orders) {
      state.userOrders = orders
    },
  },
  getters: {
    getOrders(state) {
      return state.orders
    },
    getUserOrders(state) {
      return state.userOrders
    },
  },
}
