import Vue from "vue"
import Vuelidate from 'vuelidate'
import Notifications from "vue-notification"

import store from "./store"
import App from "./App.vue"
import router from "./routers/router"
import "@/assets/styles/index.css"

Vue.config.productionTip = false

Vue.use(Notifications)
Vue.use(Vuelidate)

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app")
