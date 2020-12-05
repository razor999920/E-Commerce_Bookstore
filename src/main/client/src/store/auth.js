import Vue from "vue"

import api from "@/utils/api"
import {
  LOG_OUT,
  SET_EMAIL, SET_EMAIL_CONFIRMED, SET_ROLES, SET_SESSION_ACTIVE, SET_USERNAME,
} from "@/store/types"
import localStore from "@/utils/localStore"

export default {
  namespaced: true,
  state: {
    email: "",
    username: "",
    roles: [],
    isSessionActive: false,
    isEmailConfirmed: false,
  },
  actions: {
    async register(_, payload) {
      if (!payload) return null

      try {
        const { data } = await Vue.prototype.$http.post(api.register, payload)
        return data && data.status === 201
      } catch (e) {
        throw new Error(e)
      }
    },

    async login({ commit }, payload) {
      if (!payload) return null

      try {
        const { data } = await Vue.prototype.$http.post(api.login, payload)

        if (data) {
          commit(SET_EMAIL, data.email)
          commit(SET_USERNAME, data.username)
          commit(SET_EMAIL_CONFIRMED, data.isEmailConfirmed)
          commit(SET_SESSION_ACTIVE, true)

          const roleNames = data.roles.map(role => role.name)
          commit(SET_ROLES, roleNames)
        }
        return data
      } catch (e) {
        throw new Error(e)
      }
    },

    async logout({ commit }) {
      try {
        const r = await Vue.prototype.$http.post(api.logout)
        if (r.status === 200) commit(LOG_OUT)
      } catch (e) {
        throw new Error(e)
      }
    },

    async initializeAppSession({ commit }) {
      const r = await Vue.prototype.$http.get(api.isLoggedIn)

      if (!r || r.data === false) {
        commit(SET_SESSION_ACTIVE, false)
        return
      }

      commit(SET_EMAIL, localStore.getEmail())
      commit(SET_USERNAME, localStore.getUsername())
      commit(SET_SESSION_ACTIVE, true)
      commit(SET_EMAIL_CONFIRMED, localStore.isEmailConfirmed())
      commit(SET_ROLES, localStore.getRoles())
    },
  },
  mutations: {
    [SET_ROLES](state, roles) {
      state.roles = roles
      localStore.setRoles(roles)
    },
    [SET_EMAIL](state, email) {
      state.email = email
      localStore.setEmail(email)
    },
    [SET_USERNAME](state, username) {
      state.username = username
      localStore.setUsername(username)
    },
    [SET_EMAIL_CONFIRMED](state, isEmailConfirmed) {
      state.isEmailConfirmed = isEmailConfirmed === true || isEmailConfirmed === "true"
      localStore.setEmailConfirmed(isEmailConfirmed)
    },
    [SET_SESSION_ACTIVE](state, active) {
      state.isSessionActive = active

      if (!active) {
        localStore.removeEmail()
        localStore.removeUsername()
        localStore.removeEmailConfirmed()
        localStore.removeRoles()
      }
    },
    [LOG_OUT](state) {
      state.email = ""
      state.username = ""
      state.isSessionActive = false
      state.roles = []
      localStore.removeEmail()
      localStore.removeUsername()
      localStore.removeEmailConfirmed()
      localStore.removeSessionTimeout()
      localStore.removeRoles()
    },
  },
  getters: {
    getEmail(state) {
      return state.username
    },
    getUsername(state) {
      return state.username
    },
    getRoles(state) {
      return state.roles
    },
    isSessionActive(state) {
      return state.isSessionActive
    },
    isEmailConfirmed(state) {
      return state.isEmailConfirmed
    },
    isAdmin(state) {
      return state.roles.includes("ROLE_ADMIN")
    },
  },
}
