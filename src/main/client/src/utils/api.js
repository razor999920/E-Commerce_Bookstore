import Vue from "vue"
import axios from "axios"
import eventHub, {
  EVENT_AFTER_RESPONSE,
  EVENT_BEFORE_REQUEST,
  EVENT_REQUEST_ERROR, EVENT_RESPONSE_ERROR,
} from "@/utils/eventHub"

const api = {
  isLoggedIn: "/auth",
  register: "/auth/register",
  login: "/auth/login",
  logout: "/auth/logout",
  books: "/books",
  booksCategories: "/books/categories",
  booksSearch: "/books/search",
  bookIsbn: "/books/isbn",
  bookId: "/books/id",
  bestsellers: "/books/bestsellers",
  salesPerMonth: "/books/sales-per-month",
  purchases: "/purchases",
  reviews: "/reviews",
  users: "/users",
  usersStatistics: "/users/statistics",
}

const instance = axios.create({
  baseURL: "/api",
  withCredentials: true,
})

instance.interceptors.request.use(
  config => {
    if (api.isLoggedIn.includes(config.url)) {
      eventHub.$emit(EVENT_BEFORE_REQUEST)
    }
    return config
  },
  error => {
    eventHub.$emit(EVENT_REQUEST_ERROR)
    return Promise.reject(error)
  },
)

instance.interceptors.response.use(
  response => {
    eventHub.$emit(EVENT_AFTER_RESPONSE)
    return response
  },
  error => {
    eventHub.$emit(EVENT_RESPONSE_ERROR)
    return Promise.reject(error)
  },
)

Vue.prototype.$http = instance
export default api
