import Vue from "vue"
import { debounce } from "lodash"

import localStore from "@/utils/localStore"
import api from "@/utils/api"

export const SET_DATA = "SET_DATA"
export const ADD_ITEM = "ADD_ITEM"
export const REMOVE_ITEM = "REMOVE_ITEM"
export const SET_ADDRESSES = "SET_ADDRESSES"
export const SET_PAY_COUNT = "SET_PAY_COUNT"

export default {
  namespaced: true,
  state: {
    items: [],
    addresses: [],
    payCount: 0,
  },

  actions: {
    async initializeCart({ commit }) {
      commit(SET_DATA, localStore.getCart())
    },

    updateRemote({ state, rootState }) {
      if (rootState.authStore.isSessionActive) {
        const items = JSON.stringify(state.items)
        Vue.prototype.$http.put(api.updateCart, { blob: items })
          .catch(err => console.log(err))
      }
    },

    updateRemoteDebounce: debounce(({ dispatch }) => {
      dispatch("updateRemote")
    }, 1000 * 10),

    addToCart({ commit, dispatch }, item) {
      const data = []
      data.push(item)
      commit(ADD_ITEM, data)
      dispatch("updateRemoteDebounce")
    },

    removeFromCart({ commit, dispatch }, item) {
      commit(REMOVE_ITEM, item)
      dispatch("updateRemote")
    },

    async clearCart({ commit, dispatch }, clearRemote) {
      commit(SET_DATA, [])

      if (clearRemote) {
        dispatch("updateRemote")
      }
    },
    async submitCart({ commit, state }, order) {
      commit(SET_PAY_COUNT)

      if (state.payCount === 3) {
        throw new Error("Payment failed.")
      }

      try {
        await Vue.prototype.$http.post(api.createOrders, order)
        commit(SET_ADDRESSES, [])
      } catch (e) {
        console.log(e.response.data)
        throw new Error(e.response.data.message)
      }
    },
    async loadAddresses({ commit, rootState }) {
      if (!rootState.authStore.isSessionActive) {
        return
      }

      await Vue.prototype.$http.get(api.getAddresses)
        .then(res => {
          commit(SET_ADDRESSES, res.data)
        })
        .catch(err => console.log(err))
    },
  },
  mutations: {
    [SET_DATA](state, item) {
      state.items = item
      localStore.setCart(item)
    },
    [ADD_ITEM](state, item) {
      let itemExists = false
      for (let i = 0; i < state.items.length; i += 1) {
        if (state.items[i].id === item[0].id) {
          state.items[i].quantity += 1
          itemExists = true
        }
      }
      if (!itemExists) {
        state.items.push(...item)
      }
      localStore.setCart(state.items)
    },
    [REMOVE_ITEM](state, id) {
      for (let i = 0; i < state.items.length; i += 1) {
        if (state.items[i].id === id) {
          state.items[i].quantity -= 1
          if (state.items[i].quantity === 0) {
            state.items.splice(i, 1)
          }
        }
      }
      localStore.setCart(state.items)
    },
    [SET_ADDRESSES](state, addresses) {
      state.addresses = addresses
    },
    [SET_PAY_COUNT](state) {
      state.payCount = state.payCount > 3 ? 1 : state.payCount + 1
    },
  },

  getters: {
    getItems(state) {
      return state.items
    },
    getNumberOfItems(state) {
      const quantity = _.sumBy(state.items, "quantity")
      const subtotal = _.sumBy(state.items, "price") * quantity
      return {
        quantity,
        subtotal,
      }
    },
    getAddresses(state) {
      return state.addresses
    },
  },
}
