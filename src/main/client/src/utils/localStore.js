export default {
  getUsername() {
    return localStorage.getItem("username")
  },
  setUsername(username) {
    localStorage.setItem("username", username)
  },
  removeUsername() {
    localStorage.removeItem("username")
  },
  getEmail() {
    return localStorage.getItem("email")
  },
  setEmail(email) {
    localStorage.setItem("email", email)
  },
  removeEmail() {
    localStorage.removeItem("email")
  },
  isEmailConfirmed() {
    const val = localStorage.getItem("isEmailConfirmed")
    return val === true || val === "true"
  },
  setEmailConfirmed(emailConfirmed) {
    localStorage.setItem("isEmailConfirmed", emailConfirmed)
  },
  removeEmailConfirmed() {
    localStorage.removeItem("isEmailConfirmed")
  },
  getSessionTimeout() {
    return localStorage.getItem("sessionTimeout")
  },
  setSessionTimeout(timeout) {
    localStorage.setItem("sessionTimeout", timeout)
  },
  removeSessionTimeout() {
    localStorage.removeItem("sessionTimeout")
  },
}