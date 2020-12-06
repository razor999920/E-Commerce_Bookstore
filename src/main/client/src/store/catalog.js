import Vue from "vue"

import api from "@/utils/api"

export const SET_LOADING = "SET_LOADING"
export const SET_CATEGORIES = "SET_CATEGORIES"
export const SET_ITEMS = "SET_ITEMS"
export const SET_PAGE = "SET_PAGE"
export const SET_LAST = "SET_LAST"
export const PUSH_TO_ITEMS = "PUSH_TO_ITEMS"
export const SET_CURRENT_URL = "SET_BOOK_URL"
export const SET_BOOK_REVIEW = "SET_BOOK_REVIEW"

export default {
  namespaced: true,
  state: {
    categories: [],
    items: [],
    loading: false,
    reviews: [],
    page: 0,
    last: false,
    currentUrl: api.getBooks,
  },
  actions: {
    async resetCategory({ commit }) {
      commit(SET_PAGE, 0)
      commit(SET_LAST, false)
    },
    async loadBooks({ commit, state }) {
      try {
        const params = {
          size: 12,
          page: state.page + 1,
        }
        const response = await Vue.prototype.$http.get(state.currentUrl, { params })
          .catch(err => console.log(err))

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

    async addReview({ commit }, review) {
      try {
        if (review.id) {
          await Vue.prototype.$http.put(`${api.addReviewForBook}/${review.bookId}`, review)
        } else {
          await Vue.prototype.$http.post(api.addReview, review)
        }
        commit(SET_BOOK_REVIEW, review)
      } catch (e) {
        throw new Error(e.response.data.detail)
      }
    },

    async loadCategories({ commit }) {
      const response = await Vue.prototype.$http.get(api.getCategories)
      commit(SET_CATEGORIES, response.data)
    },

    searchBooks({ commit, dispatch }, searchTerm) {
      commit(SET_CURRENT_URL, `${api.searchBook}/${searchTerm}`)
      dispatch("resetCategory")
      dispatch("loadBooks")
    },

    loadBookByCategories({ commit, dispatch }, category) {
      if (category && category !== "") {
        commit(SET_CURRENT_URL, `${api.getBooksByCategory}/${category}`)
      } else {
        commit(SET_CURRENT_URL, `${api.getBooks}`)
      }
      dispatch("resetCategory")
      dispatch("loadBooks")
    },
    async loadUserReviewForBook(_, bookId) {
      return Vue.prototype.$http.get(`${api.getUserReviewForBook}/${bookId}`)
        .then(res => res.data)
        .catch(err => console.log(err))
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
    [SET_CURRENT_URL](state, url) {
      state.currentUrl = url
    },
    [SET_PAGE](state, page) {
      state.page = page
    },
    [SET_LAST](state, last) {
      state.last = last
    },
    [SET_BOOK_REVIEW](state, review) {
      if (review && review.bookId && review.id) {
        const bookIndex = _.findIndex(state.items, ["id", review.bookId])
        if (bookIndex) {
          const reviewIndex = _.findIndex(state.items[bookIndex].reviews, ["id", review.id])
          if (reviewIndex > -1) {
            state.items[bookIndex].reviews[reviewIndex].rating = review.rating
          }
        }
      }
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
    getReviews(state) {
      let avgReview = 0
      for (let i = 0; i < state.reviews.length; i += 1) {
        avgReview += state.reviews[i]
      }
      avgReview /= state.reviews.length
      return avgReview
    },
  },
}
