import Vue from "vue"
import VueTailwind from "vue-tailwind"
import Notifications from "vue-notification"
import Vuelidate from "vuelidate/src"

import store from "./store"
import App from "./App.vue"
import router from "./routers/router"
import "@/assets/styles/index.css"

Vue.config.productionTip = false

const tailwindSettings = {
  TModal: {
    fixedClasses: {
      overlay: "z-40 overflow-auto left-0 top-0 bottom-0 right-0 w-full h-full fixed bg-opacity-50",
      wrapper: "z-50 relative mx-auto my-0 max-w-lg mt-12",
      modal: "bg-white shadow overflow-hidden relative",
      body: "p-4",
      header: "p-4  text-sm font-semibold uppercase text-gray-700",
      footer: "p-4 text-sm font-semibold uppercase text-gray-700",
      close: "absolute right-0 top-0 m-3 text-gray-700 hover:text-gray-600",
      closeIcon: "h-5 w-5 fill-current",
    },
    classes: {
      overlay: "bg-black ",
      wrapper: "",
      modal: "",
      body: "p-4",
      header: "text-gray-700 border-b",
      footer: "border-t ",
      close: "text-gray-700 hover:text-gray-600",
      closeIcon: "",
    },
    variants: {
      danger: {
        overlay: "bg-red-500",
        header: "border-red-100 text-red-700 border-b",
        footer: "border-red-100 bg-red-100 border-t  ",
        close: "text-red-700 hover:text-red-600",
      },
      clean: {
        modal: "rounded shadow-lg",
        footer: "bg-gray-100 ",
        body: "p-4 text-sm text-gray-700",
        close: "bg-blue-100 p-1 flex items-center justify-center leading-normal rounded-full text-blue-80 hover:bg-blue-200",
      },
    },
  },
  TTable: {
    classes: {
      table: "shadow min-w-full divide-y divide-gray-200",
      tbody: "bg-white divide-y divide-gray-200",
      td: "px-6 py-4 whitespace-no-wrap text-sm leading-5 text-gray-700",
      theadTh: "px-6 py-3 border-b border-gray-200 bg-gray-100 text-left text-xs leading-4 font-medium text-gray-700 uppercase tracking-wider",
    },
    variants: {
      thin: {
        td: "p-1 whitespace-no-wrap text-sm leading-4 text-gray-700",
        theadTh: "p-1 border-b border-gray-200 bg-gray-100 text-left text-xs leading-4 font-medium text-gray-700 uppercase tracking-wider",
      },
      tail: {
        table: "min-w-full divide-y divide-gray-200",
        thead: "bg-gray-50",
        theadTh: "px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider",
        tbody: "bg-white divide-y divide-gray-200",
        td: "px-6 py-4 whitespace-nowrap",
      },
    },
    fixedClasses: {
      table: "min-w-full divide-y divide-gray-200",
      tbody: "bg-white divide-y divide-gray-200",
      tr: "px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider",
      td: "px-6 py-4 whitespace-nowrap",
      thead: "bg-gray-50",
      theadTh: "px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider",
    },
  },
  TButton: {
    fixedClasses: "focus:outline-none focus:shadow-outline inline-flex items-center transition ease-in-out duration-150",
    classes: "text-white bg-blue-600 hover:bg-blue-500 focus:border-blue-700 active:bg-blue-700 text-sm font-medium border border-transparent px-3 py-2 rounded-md",
    variants: {
      error: "text-white bg-red-600 hover:bg-red-500 focus:border-red-700  active:bg-red-700 text-sm font-medium border border-transparent px-3 py-2 rounded-md",
      success: "text-white bg-green-600 hover:bg-green-500 focus:border-green-700 active:bg-green-700 text-sm font-medium border border-transparent px-3 py-2 rounded-md",
      funny: "text-white bg-orange-600 hover:bg-orange-500 focus:border-orange-700 active:bg-orange-700 text-sm font-medium uppercase border-orange-200 px-4 py-2 border-2 rounded-full shadow",
      link: "underline text-orange-500 px-3 py-2 hover:bg-orange-100 rounded",
    },
  },
  TInput: {
    classes: "bg-white focus:outline-none focus:shadow-outline border border-gray-300 rounded py-2 px-4 block w-full leading-normal",
    variants: {
      error: "text-red-600 bg-red-100 focus:outline-none focus:shadow-outline border border-red-300 rounded py-2 px-4 block w-full leading-normal placeholder-red-300",
      success: "text-green-600 bg-green-100 focus:outline-none focus:shadow-outline border border-green-300 rounded py-2 px-4 block w-full leading-normal placeholder-green-300",
      fun: "bg-orange-100 block border-2 border-orange-300 focus:border-orange-500 focus:outline-none leading-normal px-4 py-3 rounded-full shadow-inner w-full placeholder-orange-300",
    },
  },
  TCard: {
    fixedClasses: {
      wrapper: "rounded max-w-lg mx-auto shadow",
      body: "p-4",
      header: "p-4 border-b",
      footer: "p-4 border-t",
    },
    classes: {
      wrapper: "bg-white",
      body: "",
      header: "",
      footer: "",
    },
    variants: {
      danger: {
        wrapper: "bg-red-100 text-red-700",
        header: "border-red-200 text-red-700",
        footer: "border-red-200 bg-red-100 text-red-700",
      },
      clean: {
        wrapper: "rounded",
        footer: "bg-gray-100 ",
        body: "p-4 text-sm text-gray-700",
      },
    },
  },
  TInputGroup: {
    fixedClasses: {
      wrapper: "mb-4",
      label: "block uppercase tracking-wide text-xs font-bold mb-1",
      body: "",
      feedback: "text-sm",
      description: "text-sm",
    },
    classes: {
      wrapper: "",
      label: "",
      body: "",
      feedback: "text-gray-500",
      description: "text-gray-500",
    },
    variants: {
      danger: {
        label: "text-red-500",
        feedback: "text-red-500",
      },
      success: {
        label: "text-green-500",
        feedback: "text-green-500",
      },
    },
  },
}
Vue.use(VueTailwind, tailwindSettings)
Vue.use(Notifications)
Vue.use(Vuelidate)

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app")
