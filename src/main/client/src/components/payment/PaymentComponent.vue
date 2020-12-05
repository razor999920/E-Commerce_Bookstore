<template>
  <div class="" v-if="!showBilling">
    <div class="max-w-lg mx-auto">
      <form @submit.prevent>
        <div class="shadow overflow-hidden sm:rounded-md">
          <div class="px-4 py-5 bg-white sm:p-6">
            <div class="grid grid-cols-6 gap-6">
              <div class="col-span-6 sm:col-span-3">
                <label for="first_name" class="block text-sm font-medium text-gray-700">First name</label>
                <input
                  v-model="firstname"
                  type="text"
                  name="first_name"
                  id="first_name"
                  autocomplete="given-name"
                  class="mt-1 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border-gray-300 rounded-md"
                />
                <span class="form_error" v-if="!$v.firstname.required && $v.firstname.$dirty">Field is required!</span>
              </div>

              <div class="col-span-6 sm:col-span-3">
                <label for="last_name" class="block text-sm font-medium text-gray-700">Last name</label>
                <input
                  v-model="lastname"
                  type="text"
                  name="last_name"
                  id="last_name"
                  autocomplete="family-name"
                  class="mt-1 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border-gray-300 rounded-md"
                />
                <span class="form_error" v-if="!$v.lastname.required && $v.lastname.$dirty">Field is required!</span>
              </div>

              <div class="col-span-6 sm:col-span-6">
                <label for="credit-card" class="block text-sm font-medium text-gray-700">Credit Card</label>
                <input
                  v-model="creditCard"
                  type="text"
                  name="credit-card"
                  id="credit-card"
                  autocomplete="email"
                  class="mt-1 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border-gray-300 rounded-md"
                />
                <span class="form_error" v-if="!$v.creditCard.required && $v.creditCard.$dirty">Field is required!</span>
                <span class="form_error" v-if="!$v.creditCard.minLength && $v.creditCard.$dirty">Credit Card number must have at least 13 digits!</span>
                <span class="form_error" v-if="!$v.creditCard.maxLength && $v.creditCard.$dirty">Credit Card should not have more than 19 digits!</span>
              </div>
              <div class="col-span-6 sm:col-span-3">
                <label for="phone" class="block text-sm font-medium text-gray-700">Phone</label>
                <input
                  v-model="phone"
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
                  v-model="country"
                  id="country"
                  name="country"
                  autocomplete="country"
                  class="mt-1 block w-full py-2 px-3 border border-gray-300 bg-white rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
                >
                  <option>United States</option>
                  <option>Canada</option>
                  <option>Mexico</option>
                </select>
                <span class="form_error" v-if="!$v.country.required && $v.country.$dirty">Field is required!</span>
              </div>

              <div class="col-span-6">
                <label for="street_address" class="block text-sm font-medium text-gray-700">Street address</label>
                <input
                  v-model="street"
                  type="text"
                  name="street_address"
                  id="street_address"
                  autocomplete="street-address"
                  class="mt-1 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border-gray-300 rounded-md"
                />
                <span class="form_error" v-if="!$v.street.required && $v.street.$dirty">Field is required!</span>
              </div>

              <div class="col-span-6 sm:col-span-6 lg:col-span-2">
                <label for="city" class="block text-sm font-medium text-gray-700">City</label>
                <input v-model="city" type="text" name="city" id="city" class="mt-1 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border-gray-300 rounded-md" />
                <span class="form_error" v-if="!$v.city.required && $v.city.$dirty">Field is required!</span>
              </div>

              <div class="col-span-6 sm:col-span-3 lg:col-span-2">
                <label for="state" class="block text-sm font-medium text-gray-700">State / Province</label>
                <input v-model="province" type="text" name="state" id="state" class="mt-1 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border-gray-300 rounded-md" />
                <span class="form_error" v-if="!$v.province.required && $v.province.$dirty">Field is required!</span>
              </div>

              <div class="col-span-6 sm:col-span-3 lg:col-span-2">
                <label for="postal_code" class="block text-sm font-medium text-gray-700">ZIP / Postal</label>
                <input
                  v-model="zip"
                  type="text"
                  name="postal_code"
                  id="postal_code"
                  autocomplete="postal-code"
                  class="mt-1 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border-gray-300 rounded-md"
                />
                <span class="form_error" v-if="!$v.zip.required && $v.zip.$dirty">Field is required!</span>
              </div>
            </div>
          </div>
          <div class="px-4 py-3 bg-gray-50 text-right sm:px-6">
            <button
              @click="handleCheckout()"
              type="submit"
              class="inline-flex justify-center py-2 px-4 border border-transparent shadow-sm text-sm font-medium rounded-md text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
            >
              Finish
            </button>
          </div>
        </div>
      </form>
    </div>
  </div>
  <div v-else class="shadow overflow-hidden sm:rounded-md max-w-lg mx-auto">
    <div class="px-4 py-5 bg-white sm:p-6">
    <label for="confirm_info" class="block text-4xl font-extrabold text-gray-700">Confirm Billing and Shipping Information</label>
    <label for="confirm_firstname" class="text-sm font-medium text-gray-700">First Name:</label> {{ firstname }} <br />
    <label for="confirm_lastname" class="text-sm font-medium text-gray-700">Last Name:</label> {{ lastname }} <br />
    <label for="confirm_creditCard" class="text-sm font-medium text-gray-700">Credit Card Number:</label> {{ creditCard }} <br />
    <label for="confirm_phone" class="text-sm font-medium text-gray-700">Phone:</label> {{ phone }} <br />
    <label for="confirm_country" class="text-sm font-medium text-gray-700">Country:</label> {{ country }} <br />
    <label for="confirm_street" class="text-sm font-medium text-gray-700">Street:</label> {{ street }} <br />
    <label for="confirm_city" class="text-sm font-medium text-gray-700">City:</label> {{ city }} <br />
    <label for="confirm_province" class="text-sm font-medium text-gray-700">Province:</label> {{ province }} <br />
    <label for="confirm_zip" class="text-sm font-medium text-gray-700">Zip Code:</label> {{ zip }} <br />
    </div>
    <div class="px-4 py-3 bg-gray-50 text-right sm:px-6">
      <button
        class="inline-flex justify-center py-2 px-4 border border-transparent shadow-sm text-sm font-medium rounded-md text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
      >
        Confirm Order
      </button>
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
      creditCard: "",
      firstname: "",
      lastname: "",
      country: "",
      street: "",
      city: "",
      province: "",
      zip: "",
      phone: "",
      showBilling: false,
    }
  },

  validations: {
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

  methods: {
    handleCheckout() {
      this.$v.$touch()
      if (!this.$v.$invalid) {
        this.showBilling = true
        console.log(this.creditCard)
      }
    },
  },
  computed: {
    ...mapGetters({
      isSessionActive: "authStore/isSessionActive",
    }),
  },
}
</script>
