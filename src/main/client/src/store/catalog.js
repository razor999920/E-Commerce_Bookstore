export const SET_LOADING = "SET_LOADING"
export const SET_CATEGORIES = "SET_CATEGORIES"
export const SET_FILTERS = "SET_FILTERS"
export const SET_ITEMS = "SET_ITEMS"
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
  },
  actions: {
    initialize() {
      // todo
    },

    loadItems({ commit }, category) {
      const data = []
      for (let i = 0; i < 10; i += 1) {
        data.push(
          {
            id: `${category}_${i}`,
            title: `${category} book ${i}`,
            author: `author${i}`,
            price: `${i}.99`,
          },
        )
      }
      commit(SET_ITEMS, data)
    },

    loadMore({ commit }, category) {
      const data = []
      for (let i = 0; i < 10; i += 1) {
        const id = `${category}_${Math.floor(Math.random() * 1000)}`
        data.push(
          {
            id,
            title: `${category} book ${i}`,
            author: `author${id}`,
            price: `${i}.99`,
          },
        )
      }
      commit(PUSH_TO_ITEMS, data)
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
  },
}
