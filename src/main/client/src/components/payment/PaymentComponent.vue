<template>
  <div>
    <t-modal variant="clean" ref="modal" v-model="showModal" :click-to-close="false" :esc-to-close="false">
      <div class="flex flex-col items-center">
        <div v-if="success" class="text-green-300">
          <svg class="w-12 h-12" fill="none" stroke="currentColor" viewBox="0 0 24 24"
               xmlns="http://www.w3.org/2000/svg">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                  d="M9 12l2 2 4-4m6 2a9 9 0 11-18 0 9 9 0 0118 0z"></path>
          </svg>
        </div>
        <div v-else class="text-red-300">
          <svg class="w-12 h-12" fill="none" stroke="currentColor" viewBox="0 0 24 24"
               xmlns="http://www.w3.org/2000/svg">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                  d="M12 8v4m0 4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z"></path>
          </svg>
        </div>
        <div class="text-lg my-3">
          {{ success ? "Payment success!" : "Payment failed!" }}
        </div>
        <button
          v-if="success"
          class="w-full flex items-center justify-center px-4 py-2 border border-transparent shadow-sm text-base font-medium text-white bg-black hover:bg-gray-800 hover:text-gray-200"
          @click="$refs.modal.hide(); $router.push({path: '/'})" type="button">
          Go back to home page
        </button>
        <button
          v-else
          class="w-full flex items-center justify-center px-4 py-2 border border-transparent shadow-sm text-base font-medium text-white bg-black hover:bg-gray-800 hover:text-gray-200"
          @click="$refs.modal.hide()" type="button">
          OK
        </button>
      </div>
    </t-modal>
    <div class="" v-if="!showBilling">
      <div class="max-w-lg mx-auto">
        <form @submit.prevent>
          <div class="shadow overflow-hidden sm:rounded-md">
            <div class="px-4 py-5 bg-white sm:p-6">
              <div class="grid grid-cols-6 gap-6">
                <div class="col-span-6 sm:col-span-3">
                  <label for="first_name" class="block text-sm font-medium text-gray-700">First name</label>
                  <input
                    v-model="order.firstname"
                    type="text"
                    name="first_name"
                    id="first_name"
                    autocomplete="given-name"
                    class="mt-1 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border-gray-300 rounded-md"
                  />
                  <span class="form_error" v-if="!$v.order.firstname.required && $v.order.firstname.$dirty">Field is required!</span>
                </div>

                <div class="col-span-6 sm:col-span-3">
                  <label for="last_name" class="block text-sm font-medium text-gray-700">Last name</label>
                  <input
                    v-model="order.lastname"
                    type="text"
                    name="last_name"
                    id="last_name"
                    autocomplete="family-name"
                    class="mt-1 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border-gray-300 rounded-md"
                  />
                  <span class="form_error" v-if="!$v.order.lastname.required && $v.order.lastname.$dirty">Field is required!</span>
                </div>

                <div class="col-span-6 sm:col-span-6">
                  <label for="credit-card" class="block text-sm font-medium text-gray-700">Credit Card</label>
                  <input
                    v-model="order.creditCard"
                    type="text"
                    name="credit-card"
                    id="credit-card"
                    autocomplete="email"
                    class="mt-1 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border-gray-300 rounded-md"
                  />
                  <span class="form_error"
                        v-if="!$v.order.creditCard.required && $v.order.creditCard.$dirty">Field is required!</span>
                  <span class="form_error" v-if="!$v.order.creditCard.minLength && $v.order.creditCard.$dirty">Credit Card number must have at least 13 digits!</span>
                  <span class="form_error" v-if="!$v.order.creditCard.maxLength && $v.order.creditCard.$dirty">Credit Card should not have more than 19 digits!</span>
                </div>
                <div class="col-span-6 sm:col-span-3">
                  <label for="phone" class="block text-sm font-medium text-gray-700">Phone</label>
                  <input
                    v-model="order.phone"
                    type="text"
                    name="street_address"
                    id="phone"
                    autocomplete="phone"
                    class="mt-1 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border-gray-300 rounded-md"
                  />
                </div>

                <div class="col-span-6 sm:col-span-3">
                  <label for="country" class="block text-sm font-medium text-gray-700">Country / Region</label>
                  <select
                    v-model="order.country"
                    id="country"
                    name="country"
                    autocomplete="country"
                    class="mt-1 block w-full py-2 px-3 border border-gray-300 bg-white rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
                  >
                    <option>United States</option>
                    <option>Canada</option>
                    <option>Mexico</option>
                  </select>
                  <span class="form_error"
                        v-if="!$v.order.country.required && $v.order.country.$dirty">Field is required!</span>
                </div>

                <div class="col-span-6">
                  <label for="street_address" class="block text-sm font-medium text-gray-700">Street address</label>
                  <input
                    v-model="order.street"
                    type="text"
                    name="street_address"
                    id="street_address"
                    autocomplete="street-address"
                    class="mt-1 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border-gray-300 rounded-md"
                  />
                  <span class="form_error"
                        v-if="!$v.order.street.required && $v.order.street.$dirty">Field is required!</span>
                </div>

                <div class="col-span-6 sm:col-span-6 lg:col-span-2">
                  <label for="city" class="block text-sm font-medium text-gray-700">City</label>
                  <input v-model="order.city" type="text" name="city" id="city"
                         class="mt-1 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border-gray-300 rounded-md" />
                  <span class="form_error"
                        v-if="!$v.order.city.required && $v.order.city.$dirty">Field is required!</span>
                </div>

                <div class="col-span-6 sm:col-span-3 lg:col-span-2">
                  <label for="state" class="block text-sm font-medium text-gray-700">State / Province</label>
                  <input v-model="order.province" type="text" name="state" id="state"
                         class="mt-1 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border-gray-300 rounded-md" />
                  <span class="form_error" v-if="!$v.order.province.required && $v.order.province.$dirty">Field is required!</span>
                </div>

                <div class="col-span-6 sm:col-span-3 lg:col-span-2">
                  <label for="postal_code" class="block text-sm font-medium text-gray-700">ZIP / Postal</label>
                  <input
                    v-model="order.zip"
                    type="text"
                    name="postal_code"
                    id="postal_code"
                    autocomplete="postal-code"
                    class="mt-1 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border-gray-300 rounded-md"
                  />
                  <span class="form_error"
                        v-if="!$v.order.zip.required && $v.order.zip.$dirty">Field is required!</span>
                </div>
              </div>
            </div>
            <div class="px-4 py-3 bg-gray-50 text-right sm:px-6">
              <button
                type="button"
                @click="validateForm()"
                class="inline-flex justify-center py-2 px-4 border border-transparent shadow-sm text-sm font-medium rounded-md text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500">
                Finish
              </button>
            </div>
          </div>
        </form>
      </div>
    </div>
    <div v-else class="shadow overflow-hidden sm:rounded-md max-w-lg mx-auto">
      <div class="px-4 py-5 bg-white sm:p-6">
        <label class="block text-4xl font-extrabold text-gray-700">Confirm Billing and Shipping
          Information</label>
        <label class="text-sm font-medium text-gray-700">First Name:</label> {{ order.firstname }}
        <br />
        <label class="text-sm font-medium text-gray-700">Last Name:</label> {{ order.lastname }} <br />
        <label class="text-sm font-medium text-gray-700">Credit Card Number:</label>{{ order.creditCard }} <br />
        <label class="text-sm font-medium text-gray-700">Phone:</label> {{ order.phone }} <br />
        <label class="text-sm font-medium text-gray-700">Country:</label> {{ order.country }} <br />
        <label class="text-sm font-medium text-gray-700">Street:</label> {{ order.street }} <br />
        <label class="text-sm font-medium text-gray-700">City:</label> {{ order.city }} <br />
        <label class="text-sm font-medium text-gray-700">Province:</label> {{ order.province }} <br />
        <label class="text-sm font-medium text-gray-700">Zip Code:</label> {{ order.zip }} <br />
      </div>
      <div class="px-4 py-3 bg-gray-50 text-right sm:px-6">
        <button @click="handleCheckout()"
                class="inline-flex justify-center py-2 px-4 border border-transparent shadow-sm text-sm font-medium rounded-md text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500">
          Confirm Order
        </button>
      </div>

    </div>

  </div>
</template>

<script>
import { mapGetters } from "vuex"
import { required, minLength, maxLength } from "vuelidate/lib/validators"

export default {
  components: {},

  data() {
    return {
      order: {
        creditCard: "",
        firstname: "",
        lastname: "",
        country: "",
        street: "",
        city: "",
        province: "",
        zip: "",
        phone: "",
      },
      showBilling: false,
      success: false,
      showModal: false,
    }
  },

  validations: {
    order: {
      firstname: {
        required,
      },
      lastname: {
        required,
      },
      creditCard: {
        required,
        minLength: minLength(13),
        maxLength: maxLength(19),
      },
      country: {
        required,
      },
      street: {
        required,
      },
      city: {
        required,
      },
      province: {
        required,
      },
      zip: {
        required,
      },
    },
  },

  methods: {
    validateForm() {
      this.$v.$touch()
      if (!this.$v.$invalid) {
        this.showBilling = true
      }
    },
    async handleCheckout() {
      const purchaseItems = this.items.map(item => ({
        id: item.id,
        quantity: item.quantity,
      }))

      const billing = {
        country: this.order.country,
        phone: this.order.phone,
        province: this.order.province,
        street: this.order.street,
        zip: this.order.zip,
      }

      const purchase = {
        firstname: this.order.firstname,
        lastname: this.order.lastname,
        status: "PAID",
        items: purchaseItems,
        address: billing,
      }
      this.$store
        .dispatch("cartStore/submitCart", purchase)
        .then(() => {
          this.success = true
          this.openModal = true
          this.$store.dispatch("cartStore/clearCart")
        })
        .catch(() => {
          this.success = false
          this.openModal = true
        })
    },
  },
  computed: {
    ...mapGetters({
      isSessionActive: "authStore/isSessionActive",
      items: "cartStore/getItems",
      total: "cartStore/getTotal",
    }),
  },
}
</script>
