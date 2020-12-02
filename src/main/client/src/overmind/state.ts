import { CurrentUser } from "./effects/api/models"

type State = {
  error: any
  isLoggedIn: boolean
  currentUser: CurrentUser | null
  signInModalOpen: boolean
}

export const state: State = {
  error: null,
  isLoggedIn: false,
  currentUser: null,
  signInModalOpen: false
}
