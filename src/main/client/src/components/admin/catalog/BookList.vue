<template>
  <div>
    <div>
      <t-button>
        <router-link to="/admin/books/add">Add Book</router-link>
      </t-button>
    </div>
    <div>
      <t-table
        variant="demo"
        :headers="headers"
        :data="books"
      ></t-table>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex"

export default {
  name: "BookList",
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
          value: "inventory",
          text: "inventory",
        },
      ],
    }
  },
  created() {
    this.loadBooks()
  },
  methods: {
    loadBooks() {
      this.$store.dispatch("catalogStore/loadBooks")
    },
  },
  computed: {
    ...mapGetters({
      books: "catalogStore/getItems",
    }),
  },
}
</script>

<style scoped>

</style>
