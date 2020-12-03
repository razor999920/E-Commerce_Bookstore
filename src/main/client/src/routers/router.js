import Vue from "vue"
import VueRouter from "vue-router"
import Home from "@/views/Home.vue"
import Login from "@/views/Login.vue"
import NotFound from "@/views/NotFound.vue"
import Catalog from "@/components/catalog/Catalog.vue"
import Admin from "@/views/Admin.vue"

Vue.use(VueRouter)

const router = new VueRouter({
  routes: [
    {
      path: "/",
      component: Home,
      children: [
        {
          path: "",
          component: Home,
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
          name: "Orders",
          component: Home,
          meta: {
            requiresAuth: true,
          },
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
      },
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

  // require auth and is authenticated, continue
  if (localStorage.getItem("email") !== null) {
    next()
    return
  }

  // require auth and not authenticated, go to login
  next({
    path: "/login",
  })
})

export default router
