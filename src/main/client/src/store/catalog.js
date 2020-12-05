import Vue from "vue"

import api from "@/utils/api"

export const SET_LOADING = "SET_LOADING"
export const SET_CATEGORIES = "SET_CATEGORIES"
export const SET_FILTERS = "SET_FILTERS"
export const SET_ITEMS = "SET_ITEMS"
export const SET_PAGE = "SET_PAGE"
export const SET_LAST = "SET_LAST"
export const PUSH_TO_ITEMS = "PUSH_TO_ITEMS"

export default {
  namespaced: true,
  state: {
    categories: [],
    items: [],
    filters: [],
    currentFilters: [],
    currentCategory: "",
    searchTerm: "",
    loading: false,
    reviews: [],
    page: 0,
    last: false,
  },
  actions: {
    async loadBooks({ commit, state }) {
      try {
        if (state.last) return

        const params = {
          size: 25,
          page: state.page + 1,
        }

        const response = await Vue.prototype.$http.get(api.getBooks, { params })
        if (response && response.status === 200) {
          if (response.data.first) {
            commit(SET_ITEMS, response.data.content)
          } else {
            commit(PUSH_TO_ITEMS, response.data.content)
          }
          commit(SET_PAGE, state.page + 1)
          commit(SET_LAST, response.data.last)
        }
      } catch (e) {
        throw new Error(e.response.data.detail)
      }
    },

    loadCategories({ commit }) {
      const categories = [
        {
          id: 1,
          name: "Horror",
          slug: "horror",
        },
        {
          id: 2,
          name: "Sci-Fi",
          slug: "sci-fi",
        },
        {
          id: 3,
          name: "Biography",
          slug: "biography",
        },
        {
          id: 4,
          name: "Fantasy",
          slug: "fantasy",
        },
        {
          id: 5,
          name: "Children literature",
          slug: "children-literature",
        },
      ]
      commit(SET_CATEGORIES, categories)
    },

    loadFilters({ commit }) {
      const filters = ["Filter 1", "Filter 2", "Filter 3"]
      commit(SET_FILTERS, filters)
    },
  },
  mutations: {
    [SET_CATEGORIES](state, categories) {
      state.categories = categories
    },
    [SET_ITEMS](state, products) {
      state.items = products
    },
    [PUSH_TO_ITEMS](state, items) {
      state.items.push(...items)
    },
    [SET_FILTERS](state, filters) {
      state.filters = filters
    },
    [SET_PAGE](state, page) {
      state.page = page
    },
    [SET_LAST](state, last) {
      state.last = last
    },
  },
  getters: {
    getCategories(state) {
      return state.categories
    },
    getItems(state) {
      return state.items
    },
    getFilters(state) {
      return state.filters
    },
    getLast(state) {
      return state.last
    },
  },
}
