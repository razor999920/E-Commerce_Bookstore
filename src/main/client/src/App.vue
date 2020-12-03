<template>
  <div id="app">
    <notifications group="all" width="100%" position="bottom center" classes="vue-notification" />
    <notifications group="toast" position="top right" classes="vue-notification" />
    <div v-if="isLoading" class="absolute inset-0 flex items-center justify-center bg-gray-100">
      <fulfilling-bouncing-circle-spinner :animation-duration="4000" :size="60" color="#ff1d5e" />
    </div>
    <router-view />
  </div>
</template>

<script>
import { mapGetters } from "vuex"
import { FulfillingBouncingCircleSpinner } from "epic-spinners"
import eventHub, {
  EVENT_AFTER_RESPONSE,
  EVENT_BEFORE_REQUEST,
  EVENT_REQUEST_ERROR,
  EVENT_RESPONSE_ERROR,
} from "@/utils/eventHub"
import localStore from "@/utils/localStore"
import moment from "moment"

export default {
  name: "app",
  components: {
    FulfillingBouncingCircleSpinner,
  },

  async created() {
    eventHub.$on(EVENT_BEFORE_REQUEST, this.setLoading)
    eventHub.$on(EVENT_REQUEST_ERROR, this.unsetLoading)
    eventHub.$on(EVENT_AFTER_RESPONSE, this.unsetLoading)
    eventHub.$on(EVENT_RESPONSE_ERROR, this.unsetLoading)

    await this.$store.dispatch("authStore/initializeAppSession")
    if (this.isSessionActive) {
      this.initializeApp().then()
    }
  },

  data() {
    return {
      isLoading: false,
    }
  },

  methods: {
    async initializeApp() {
      try {
        // TODO: initialize cart state.
      } catch (e) {
        console.log(e)
      }
    },

    setLoading() {
      this.isLoading = true
    },

    unsetLoading() {
      if (this.isSessionActive) {
        const isActive = this.checkSessionTimeout()

        if (!isActive) {
          this.$store.commit("authStore/logout")
        }
      }
      this.isLoading = false
    },

    checkSessionTimeout() {
      const timeout = localStore.getSessionTimeout()
      if (!timeout) return false

      const diff = moment.duration(moment().diff(moment(timeout)))
      if (diff.asMinutes() >= 30) return false

      localStore.setSessionTimeout(moment().format())
      return true
    },
  },

  computed: {
    ...mapGetters({
      isSessionActive: "authStore/isSessionActive",
    }),
  },
}
</script>

<style>
@import url("https://fonts.googleapis.com/css?family=Inter");
</style>
