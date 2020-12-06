import Vue from "vue"
import api from "@/utils/api"

export const SET_USERS = "SET_USERS"
export const SET_BEST_SELLERS = "SET_BEST_SELLERS"
export const SET_SALES_PER_MONTH = "SET_SALES_PER_MONTH"
export const SET_BUY_STATS = "SET_BUY_STATS"
export const SET_SALES = "SET_SALES"

export default {
  namespaced: true,
  state: {
    bestsellers: [],
    buyStats: [],
    sales: [[]],
    users: [],
    page: 0,
    last: false,
  },
  getters: {
    getSales(state) {
      return state.sales
    },
    getBestSellers(state) {
      return state.bestsellers
    },
    getBuyStats(state) {
      return state.buyStats
    }
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

    async loadBuyStats({ commit }) {
      try {
        const response = await Vue.prototype.$http.get(api.getBuyStats)
        commit(SET_BUY_STATS, response.data)
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

    async loadSales({ commit }) {
      try {
        const response = await Vue.prototype.$http.get(api.getSalesPerMonth)
        if (response && response.data) {
          const data = Object.keys(response.data)
            .map((k) => [k, response.data[k]])
          commit(SET_SALES, data)
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
    [SET_SALES](state, sales) {
      state.sales = sales
    },
    [SET_BUY_STATS](state, buyStats) {
      state.buyStats = buyStats
    },
  },
}
