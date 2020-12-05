import Vue from "vue"
import api from "@/utils/api"

export const SET_BOOKS = "SET_BOOKS"
export const SET_USERS = "SET_USERS"
export const SET_ORDERS = "SET_ORDERS"

export default {
  namespaced: true,
  states: {
    users: [],
    books: [],
    orders: [],
    page: 0,
    last: false,
  },
  actions: {
    async addBook(_, book) {
      try {
        await Vue.prototype.$http.post(api.addBook, book)
      } catch (e) {
        throw new Error(e.response.data.detail)
      }
    },
  },
  mutations: {
    [SET_USERS](state, users) {
      state.users = users
    },
    [SET_ORDERS](state, orders) {
      state.orders = orders
    },
  },
  getters: {
    getBooks(state) {
      return state.books
    },
    getOrders(state) {
      return state.orders
    },
    getUsers(state) {
      return state.users
    },
  },
}
