<template>
  <div>
    book details
  </div>
</template>

<script>
export default {
  name: "BookDetail",
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
    },
    handleEditBook() {
    },
    getCategories() {
    },
  },
}
</script>

<style scoped>

</style>
