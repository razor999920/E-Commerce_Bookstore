import { Action, AsyncAction } from "."
import * as internalActions from "./internalActions"

export const internal = internalActions

export const signInClicked: Action<string | void> = ({ state }) => {
  state.signInModalOpen = true
}

export const toggleSignInModal: Action = ({ state }) => {
  state.signInModalOpen = !state.signInModalOpen
}

export const createAccount: AsyncAction<any, boolean> = async (
  { state },
  userInfo: { email: string; username: string; password: string },
) => {
  return new Promise((resolve, reject) => {
    state.error = null
    state.currentUser = null
    state.isLoggedIn = false
    if (userInfo.email === "a@gmail.com") {
      state.error = "user already exists"
      return true
    }

    const user = { ...userInfo }
    delete user.password
    state.currentUser = { ...user, id: "fake-1", isAdmin: true }
    resolve(true)
  })
}

export const signIn: AsyncAction<{ login: string; password: string }, boolean> = async (
  { state, effects },
  credentials,
) => {
  state.error = null
  state.currentUser = null
  state.isLoggedIn = false

  const result = await effects.api.login(
    credentials.login,
    credentials.password,
    false,
  ).catch(error => {
    state.error = error.message
  })

  if (!result) {
    state.error = "Invalid username or password"
    return false
  }

  const currentUser = await effects.api.getCurrentUser()
  state.currentUser = currentUser
  state.isLoggedIn = true
  return true
}

export const signOut: AsyncAction<void, boolean> = async ({
  state,
  effects,
}) => {
  state.error = null
  state.currentUser = null
  state.isLoggedIn = false
  await effects.api.logout()
  return true
}

export const toggleTheme: Action = ({state}) => {
  state.isDark = !state.isDark
}
