import localStore from "@/utils/localStore"

export const SET_DATA = "SET_DATA"
export const ADD_ITEM = "ADD_ITEM"

export default {
  namespaced: true,
  state: {
    items: [],
  },

  actions: {
    loadCart({ commit }) {
      const data = []
      commit(SET_DATA, data)
    },

    addToCart({ commit }, item) {
      const data = []
      data.push(item)
      commit(ADD_ITEM, data)
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
      state.numberOfItems += 1

      localStore.setCart(state.items)
      localStore.setCartNumberOfItems(state.numberOfItems)
    },
  },

  getters: {
    getItems(state) {
      return state.items
    },
  },
}
