import { rejects } from "assert"
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
      state.error = { message: "user already exists" }
      rejects(state.error)
      return
    }

    const user = { ...userInfo }
    delete user.password
    state.currentUser = { ...user, id: "fake-1" }
    resolve(true)
  })
}

export const signIn: Action<{ login: string; password: string }, any> = async (
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
  )

  if (!result) {
    state.error = { message: "Invalid username or password" }
    return state.error
  }

  const currentUser = await effects.api.getCurrentUser()
  state.currentUser = currentUser
  state.isLoggedIn = true
  return state.currentUser
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
