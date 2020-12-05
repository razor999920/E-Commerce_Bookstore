export const SET_ORDERS = "SET_ORDERS"

export default {
  namespaced: true,
  state: {
    orders: [],
  },
  actions: {
    async loadOrders({ commit }) {
      commit(SET_ORDERS, [])
    },
  },
  mutations: {
    [SET_ORDERS](state, orders) {
      state.orders = orders
    },
  },
  getters: {
    getOrders(state) {
      return state.orders
    },
  },
}
