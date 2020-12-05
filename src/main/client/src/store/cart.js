export const SET_DATA = "SET_DATA"
export const ADD_ITEM = "ADD_ITEM"
export const REMOVE_ITEM = "REMOVE_ITEM"
export const ADD_QUANTITY = "ADD_QUANTITY"
export const REMOVE_QUANTITY = "REMOVE_QUANTITY"

export default {
  namespaced: true,
  state: {
    items: [],
    numberOfItems: 0,
    total: 0,
  },

  actions: {
    initialize() {
      // todo
    },

    loadCart({ commit }) {
      const data = []

      commit("SET_DATA", data)
    },

    addToCart({ commit }, item) {
      const data = []
      data.push(item)
      commit("ADD_ITEM", data)
    },

    removeFromCart({ commit }, item) {
      commit("REMOVE_ITEM", item)
    },

    addQuantity({ commit }, quantity) {
      commit("ADD_QUANTITY", quantity)
    },
    removeQuantity({ commit }, quantity) {
      commit("REMOVE_QUANTITY", quantity)
    },
  },
  mutations: {
    [SET_DATA](state, item) {
      state.items = item
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
      state.numberOfItems -= 1
    },
    [ADD_QUANTITY](state, quantity) {
      state.numberOfItems += quantity
    },
    [REMOVE_QUANTITY](state, quantity) {
      state.numberOfItems -= quantity
    },
  },

  getters: {
    getItems(state) {
      return state.items
    },

    getNumberOfItems(state) {
      return state.numberOfItems
    },

    getTotal(state) {
      state.total = 0
      // New Price
      for (let i = 0; i < state.items.length; i += 1) {
        if (state.items[i].quantity > 1) {
          state.total += (parseFloat(state.items[i].price) * parseFloat(state.items[i].quantity))
        } else {
          state.total += parseFloat(state.items[i].price)
        }
      }
      return state.total
    },
  },
}
