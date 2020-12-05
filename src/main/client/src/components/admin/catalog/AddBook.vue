<template>
  <div>
    <t-card header="Add a book">
      <form @submit.prevent>
        <t-input-group label="Title">
          <t-input id="title" name="title" v-model="book.title" />
        </t-input-group>
        <t-input-group label="ISBN">
          <t-input id="isbn" name="isbn" v-model="book.isbn" />
        </t-input-group>
        <t-input-group label="Price">
          <t-input type="number" id="price" name="price" v-model="book.price" />
        </t-input-group>
        <t-input-group label="Inventory">
          <t-input type="number" id="inventory" name="inventory" v-model="book.inventory" />
        </t-input-group>
        <t-input-group label="Category">
          <t-input id="category" name="category" v-model="book.category" />
        </t-input-group>
        <t-input-group label="Author">
          <t-input id="author" name="author" v-model="book.author" />
        </t-input-group>
        <t-input-group label="Image URL">
          <t-input id="imageUrl" name="imageUrl" v-model="book.imageUrl" />
        </t-input-group>
        <t-input-group label="Description">
          <t-input type="text" id="description" name="description" v-model="book.description" />
        </t-input-group>
      </form>

      <template v-slot:footer>
        <div class="flex justify-between">
          <t-button type="button" @click="handleCancel()">Cancel</t-button>
          <t-button type="button" @click="handleAddBook()">Ok</t-button>
        </div>
      </template>
    </t-card>

  </div>
</template>

<script>
export default {
  name: "AddBook",
  data() {
    return {
      book: {
        id: null,
        title: "",
        isbn: "",
        description: "",
        inventory: 0,
        price: 0.0,
        imageUrl: "",
        author: "",
        category: "",
      },
    }
  },
  computed: {},
  methods: {
    validateForm() {
      return true
    },
    async handleAddBook() {
      try {
        await this.$store.dispatch("adminStore/addBook", this.book)
      } catch (e) {
        this.$notify({
          group: "all",
          type: "error",
          text: e.message,
        })
      }
    },
    handleCancel() {
      this.$router.push({ path: "/admin/books" })
    },
    getCategories() {

    },
  },
}
</script>
