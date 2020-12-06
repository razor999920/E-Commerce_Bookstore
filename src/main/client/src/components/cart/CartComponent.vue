<template>
  <div class="pr-8">
    <t-table
      variant="demo"
      :headers="headers"
      :data="items"
    >
      <template slot="row" slot-scope="props">
        <tr :class="[props.trClass, props.rowIndex % 2 === 0 ? 'bg-gray-100' : '']">
          <td :class="props.tdClass">
            {{ props.row.title }}
          </td>
          <td :class="props.tdClass">
            {{ props.row.author }}
          </td>
          <td :class="props.tdClass">
            <span class="text-green-500">${{ props.row.price }}</span>
          </td>
          <td :class="props.tdClass">
            {{ props.row.quantity }}
          </td>
          <td :class="props.tdClass">
            <t-button variant="secondary" @click="removeFromCart(props.row.id)">
              <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24"
                   xmlns="http://www.w3.org/2000/svg">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                      d="M15 12H9m12 0a9 9 0 11-18 0 9 9 0 0118 0z"></path>
              </svg>
            </t-button>
            <t-button variant="secondary" class="ml-2" @click="addToCart(props.row)">
              <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24"
                   xmlns="http://www.w3.org/2000/svg">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                      d="M12 9v3m0 0v3m0-3h3m-3 0H9m12 0a9 9 0 11-18 0 9 9 0 0118 0z"></path>
              </svg>
            </t-button>
          </td>
        </tr>
      </template>
    </t-table>
    <div class="float-right">
      <div
        class="px-2 py-2 mt-6 font-medium tracking-widest uppercase">
        Subtotal: ${{ subtotal.toFixed(2) }}
      </div>
      <div>
        <router-link to="/products" tag="button"
                     class="px-2 py-2 mt-6 font-medium tracking-widest text-white uppercase bg-black shadow-lg focus:outline-none hover:bg-gray-900 hover:shadow-none">
          Continue Shopping
        </router-link>
      </div>
      <div>
        <router-link v-if="items.length !== 0" to="/cart/payment" tag="button"
                     class="px-2 py-2 mt-6 font-medium tracking-widest text-white uppercase bg-black shadow-lg focus:outline-none hover:bg-gray-900 hover:shadow-none">
          Checkout
        </router-link>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex"

export default {
  data() {
    return {
      headers: [
        {
          value: "title",
          text: "title",
        },
        {
          value: "author",
          text: "author",
        },
        {
          value: "price",
          text: "price",
        },
        {
          value: "quantity",
          text: "quantity",
        },
        {
          text: "action",
        },
      ],
    }
  },
  methods: {
    addToCart(item) {
      this.$store.dispatch("cartStore/addToCart", item)
    },
    removeFromCart(id) {
      this.$store.dispatch("cartStore/removeFromCart", id)
    },
  },
  computed: {
    ...mapGetters({
      items: "cartStore/getItems",
      subtotal: "cartStore/getTotal",
    }),
  },
}
</script>
