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
  getCategories: "/books/categories",
  getBooks: "/books",
  addBook: "/books",
  getBooksByCategory: "/books/categories",
  searchBook: "/books/search",
  getBookByIsbn: "/books/isbn",
  getBookById: "/books/id",
  getBestsellers: "/books/bestsellers",
  getSalesPerMonth: "/books/sales-per-month",
  getOrders: "/purchases",
  createOrders: "/purchases",
  getReviews: "/reviews",
  getUserReviewForBook: "/users/reviews",
  addReviewForBook: "/reviews/book",
  addReview: "/reviews",
  getUsers: "/users",
  getUsersOrders: "/users/orders",
  getBuyStats: "/users/statistics",
  getCart: "carts/current",
  updateCart: "carts",
  getAddresses: "/users/addresses",
  getUserOrders: "/users/orders",
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
