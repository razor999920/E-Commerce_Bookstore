import Vue from "vue"
import api from "@/utils/api"

export const SET_USERS = "SET_USERS"
export const SET_BEST_SELLERS = "SET_BEST_SELLERS"
export const SET_SALES_PER_MONTH = "SET_SALES_PER_MONTH"

export default {
  namespaced: true,
  states: {
    bestsellers: [],
    buyStats: [],
    salesPerMonth: [],
    users: [],
    page: 0,
    last: false,
  },
  getters: {
    getSales(state) {
      return state.salesPerMonth
    },
  },
  actions: {
    async addBook(_, book) {
      try {
        const response = await Vue.prototype.$http.post(api.addBook, book)

        if (response && response.status !== 200) {
          throw response
        }
      } catch (e) {
        throw new Error(e.response.data.detail)
      }
    },

    async loadBestSellers({ commit }) {
      try {
        const response = await Vue.prototype.$http.get(api.getBestsellers)
        commit(SET_BEST_SELLERS, response.data)
      } catch (e) {
        throw new Error(e.response.data.detail)
      }
    },

    async loadSalesPerMonth({ commit }) {
      try {
        const response = await Vue.prototype.$http.get(api.getSalesPerMonth)
        if (response && response.data) {
          const data = Object.keys(response.data)
            .map((k) => [k, response.data[k]])
          commit(SET_SALES_PER_MONTH, data)
        }
      } catch (e) {
        throw new Error(e.response.data.detail)
      }
    },
  },
  mutations: {
    [SET_USERS](state, users) {
      state.users = users
    },
    [SET_BEST_SELLERS](state, bestsellers) {
      state.bestsellers = bestsellers
    },
    [SET_SALES_PER_MONTH](state, salesPerMonth) {
      state.salesPerMonth = salesPerMonth
    },
  },
}
