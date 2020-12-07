<template>
  <div>
    <div class="flex flex-wrap">
      <div>
        <t-modal header="add review" v-model="showModal">
          <div class="flex flex-col items-center justify-center">
            <div class="">
              <star-rating
                :rating="review.rating"
                @rating-selected="setRating"
                :show-rating="false"
                :item-size="32"
                :border-width="0" />
            </div>
            <textarea class="mt-4" id="w3review" name="w3review" rows="4" cols="40"
                      placeholder="Please write your review here..." v-model="review.comment"></textarea> <br />
          </div>
          <template v-slot:footer>
            <div class="flex justify-center">
              <button
                @click="handleAddReview()"
                class="ml-3 w-full flex items-center justify-center px-4 py-2 border border-transparent shadow-sm text-base font-medium text-white bg-black hover:bg-gray-800 hover:text-gray-200"
                type="button">
                Submit
              </button>
            </div>
          </template>
        </t-modal>
      </div>
      <div v-for="item in items" :key="item.id">
        <div class="max-w-sm p-4 cursor-pointer">
          <div class="card flex flex-col justify-center p-10 bg-white rounded-lg shadow-lg">
            <div
              class="prod-img"
              @click="addReviewId(item.id)">
              <img v-bind:src="item.imageUrl" class="w-full object-cover object-center" />
            </div>
            <div class="prod-title mt-4 h-20">
              <p class="text-base uppercase text-gray-900 font-bold">{{ item.title }}</p>
              <div class="">
                <div class="uppercase text-sm text-gray-400">
                  by {{ item.author }}
                </div>
                <div class="mt-2">
                  <star-rating :rating="loadRatings(item.reviews)"
                               @rating-selected="setRating"
                               :read-only="true"
                               :show-rating="false"
                               :item-size="14"
                               :border-width="0" />
                </div>
              </div>
            </div>
            <div class="mt-3 prod-info grid gap-10">
              <div class="flex flex-col md:flex-row justify-between items-center text-gray-900">
                <p class="font-bold text-xl">${{ item.price }}</p>
                <button
                  @click="addToCart(item.id, item.title, item.author, item.price)"
                  class="cursor-pointer px-6 py-2 transition ease-in duration-200 uppercase rounded-full hover:bg-gray-800 hover:text-white border-2 border-gray-900 focus:outline-none"
                >
                  Add to cart
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="flex items-center justify-center p-5">
      <t-button @click="loadMore()">{{ isLast ? "No more" : "Load more" }}</t-button>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex"
import { StarRating } from "vue-rate-it"

export default {
  name: "Catalog",

  components: {
    StarRating,
  },

  data() {
    return {
      currentCategory: "",
      showModal: false,
      review: {
        bookId: null,
        rating: 0,
        comment: "",
      },
    }
  },
  created() {
    this.loadItems()
  },
  methods: {
    loadRatings(reviews) {
      if (!reviews) {
        return 0
      }
      const total = _.sumBy(reviews, "rating")
      return Math.ceil(total / reviews.length)
    },
    loadItems() {
      const { category } = this.$route.query
      if (this.currentCategory !== category) {
        this.currentCategory = category
        this.$store.dispatch("catalogStore/resetCategory")
      }
      this.$store.dispatch("catalogStore/loadBookByCategories", category)
    },
    loadMore() {
      this.$store.dispatch("catalogStore/loadBooks")
    },
    addToCart(idComponent, titleComponent, authorComponent, priceComponent) {
      this.$store.dispatch("cartStore/addToCart", {
        id: idComponent,
        title: titleComponent,
        author: authorComponent,
        price: priceComponent,
        quantity: 1,
      })
    },
    async handleAddReview() {
      try {
        await this.$store.dispatch("catalogStore/addReview", this.review)
        this.$notify({
          group: "all",
          type: "success",
          text: "Review added!",
        })
        this.showModal = false
      } catch (e) {
        this.$notify({
          group: "all",
          type: "error",
          text: e.message,
        })
      }
    },
    async addReviewId({ rootState }, bookId) {
      if (!rootState.authStore.isSessionActive) {
        return
      }
      const review = await this.$store.dispatch("catalogStore/loadUserReviewForBook", bookId)
      if (review) {
        this.review = review
      } else {
        this.review = {}
      }

      this.review.bookId = bookId
      this.showModal = true
    },
    setRating(rating) {
      this.review.rating = parseInt(rating)
    },
  },
  computed: {
    ...mapGetters({
      items: "catalogStore/getItems",
      isLast: "catalogStore/getLast",
      getReviews: "catalogStore/getReviews",
    }),
  },
  watch: {
    $route() {
      this.loadItems()
    },
  },
}
</script>

<style scoped>
</style>
