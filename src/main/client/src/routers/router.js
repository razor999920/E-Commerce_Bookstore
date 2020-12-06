import Vue from "vue"
import VueRouter from "vue-router"
import Home from "@/views/Home.vue"
import Login from "@/views/Login.vue"
import NotFound from "@/views/NotFound.vue"
import Catalog from "@/components/catalog/Catalog.vue"
import Admin from "@/views/Admin.vue"
import localStore from "@/utils/localStore"
import BookList from "@/components/admin/catalog/BookList.vue"
import AddBook from "@/components/admin/catalog/AddBook.vue"
import BookDetail from "@/components/admin/catalog/BookDetail.vue"
import UserList from "@/components/admin/user/UserList.vue"
import OrderList from "@/components/admin/order/OrderList.vue"
import AdminDashboard from "@/components/admin/dashboard/AdminDashboard.vue"
import Cart from "@/views/Cart.vue"
import Payment from "@/views/Payment.vue"

Vue.use(VueRouter)

const router = new VueRouter({
  routes: [
    {
      path: "/",
      component: Home,
      children: [
        {
          path: "",
          component: Catalog,
        },
        {
          path: "/products",
          component: Catalog,
        },
        {
          path: "/products/:id",
          component: Catalog,
        },
        {
          path: "checkout",
          component: Home,
        },
        {
          path: "orders",
          component: OrderList,
          meta: {
            requiresAuth: true,
          },
        },
        {
          path: "/cart",
          component: Cart,
        },
        {
          path: "/cart/payment",
          component: Payment,
        },
      ],
    },
    {
      path: "/login",
      component: Login,
    },
    {
      path: "/admin",
      component: Admin,
      meta: {
        requiresAuth: true,
        requiresAdmin: true,
      },
      children: [
        {
          path: "/dashboard",
          component: AdminDashboard,
          name: "Dashboard",
        },
        {
          path: "books",
          component: BookList,
          name: "Books",
        },
        {
          path: "books/add",
          component: AddBook,
          name: "Add Book",
        },
        {
          path: "books/:id",
          component: BookDetail,
          name: "Book",
        },
        {
          path: "users",
          component: UserList,
          name: "Users",
        },
        {
          path: "orders",
          component: OrderList,
          name: "Orders",
        },
        {
          path: "*",
          component: AdminDashboard,
        },
      ],
    },
    {
      path: "/not-found",
      component: NotFound,
    },
    {
      path: "*",
      name: "PageNotFound",
      component: NotFound,
    },
  ],
})

router.beforeEach((to, from, next) => {
  // doesn't require auth, continue
  if (!to.matched.some(r => r.meta.requiresAuth)) {
    next()
    return
  }

  // require admin role
  if (to.matched.some(r => r.meta.requiresAdmin)) {
    if (localStore.getEmail() === null) {
      next({ path: "/not-found" })
      return
    }

    if (localStore.getRoles()
      .includes("ROLE_ADMIN")) {
      next()
      return
    }

    next({ path: "/not-found" })
  }

  // require auth and is authenticated, continue
  if (localStore.getEmail() !== null) {
    next()
    return
  }

  // require auth and not authenticated, go to login
  next({
    path: "/login",
  })
})

export default router
