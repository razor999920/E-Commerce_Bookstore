import { Action } from "."
import { ApiError } from "./effects/api"

export const handleError: Action<{
  message: string
  error: ApiError | Error
  hideError?: boolean
}> = ({ actions, effects, state }, { message, error, hideError = false }) => {

  if (hideError) return

  console.error(message)
  console.error(error)
}
