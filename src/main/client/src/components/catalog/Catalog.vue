<template>
  <div>
    <div class="flex flex-wrap">
      <div>
        <t-modal v-model="showModal" @click="showModal = false">
          <div class="modal" v-if="showModal">
            <h1>Add Review</h1>
            <hr />
            <div class="star_ratting">
              <star-rating @rating-selected="setRating" :show-rating="false"></star-rating>
            </div>
            <div class="star_rating"></div>
            <textarea class="review_text_box" id="w3review" name="w3review" rows="4" cols="40" placeholder="Please write your review here..." v-model="review.comment"></textarea> <br />
            <button class="submit_review" type="submit" @click="handleAddReview(), loadReviews()">Submit</button>
          </div>
        </t-modal>
      </div>
      <router-link v-for="item in items" :key="item.id" :to="{ path: '/products', query: { id: item.id } }">
        <div class="max-w-sm p-4">
          <div class="card flex flex-col justify-center p-10 bg-white rounded-lg shadow-lg">
            <div class="prod-img" @click=";(showModal = true), addReviewId(item.id)">
              <img v-bind:src="item.imageUrl" class="w-full object-cover object-center" />
            </div>
            <div class="prod-title mt-4 h-20">
              <p class="text-base uppercase text-gray-900 font-bold">{{ item.title }}</p>
              <p class="uppercase text-sm text-gray-400">by {{ item.author }}</p>
            </div>
            <div>
              <star-rating :rating="loadRatings(item.reviews)" @rating-selected="setRating" :read-only="true" :show-rating="false" v-bind:star-size="20"></star-rating>
            </div>
            <div class="prod-info grid gap-10">
              <div class="flex flex-col md:flex-row justify-between items-center text-gray-900">
                <p class="font-bold text-xl">${{ item.price }}</p>
                <button
                  @click="addToCart(item.id, item.title, item.author, item.price)"
                  class="px-6 py-2 transition ease-in duration-200 uppercase rounded-full hover:bg-gray-800 hover:text-white border-2 border-gray-900 focus:outline-none"
                >
                  Add to cart
                </button>
              </div>
            </div>
          </div>
        </div>
        <button @click="addtoCart(item.id, item.title, item.author, item.price)">+</button>
      </router-link>
    </div>
    <div>
      <button @click="loadMore()">load more</button>
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
        bookId: 0,
        rating: "",
        comment: "",
      },
    }
  },
  created() {
    this.loadItems()
  },
  methods: {
    loadItems() {
      const { category } = this.$route.query
      this.$store.dispatch("catalogStore/loadItems", category)
    },
    loadMore() {
      const { category } = this.$route.query
      this.$store.dispatch("catalogStore/loadMore", category)
    },
    loadReviews() {
      this.$store.dispatch("catalogStore/loadReviews")
    },
    loadRatings(reviews) {
      console.log(reviews)
      let avgRating = 0
      for (let i = 0; i < reviews.length; i += 1) {
        avgRating += reviews[i].rating
      }
      avgRating /= reviews.length
      return Math.ceil(avgRating).toString()
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
      } catch (e) {
        this.$notify({
          group: "all",
          type: "error",
          text: e.message,
        })
      }
    },
    addReviewId(bookId) {
      this.review.bookId = bookId
    },
    setRating(rating) {
      this.review.rating = parseInt(rating)
    },
  },
  computed: {
    ...mapGetters({
      items: "catalogStore/getItems",
      cartItems: "cartStore/getItems",
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
.modal {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  /* z-index: 99; */

  width: 100%;
  max-width: 575px;
  background-color: #fff;
  border-radius: 16px;

  padding: 25px;
}

h1 {
  font-weight: bold;
  font-size: 30px;
  padding-left: 180px;
}

hr {
  display: block;
  height: 1px;
  top: 50%;
  left: 50%;
  width: 350px;
  border: 0;
  border-top: 1px solid #ccc;
  margin-left: 90px;
  margin-bottom: 10px;
}

.star_ratting {
  width: 50%;
  margin: 15px 5px;
  margin-left: 125px;
}

.review_text_box {
  margin-left: 50px;
  -webkit-box-shadow: none;
  -moz-box-shadow: none;
  box-shadow: none;
  border-radius: 10px;
}

.submit_review {
  background-color: black;
  color: #fff;
  font-size: 15px;
  padding: 0.5rem 1rem 0.5rem 1rem;
  margin-left: 230px;
  margin-top: 8px;
}
</style>
