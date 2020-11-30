import { CurrentUser } from "./effects/api/models"

type State = {
  error: string | null
  isLoggedIn: boolean
  currentUser: CurrentUser | null
  signInModalOpen: boolean
  isDark: boolean
}

export const state: State = {
  error: null,
  isLoggedIn: false,
  currentUser: null,
  signInModalOpen: false,
  isDark: false,
}
