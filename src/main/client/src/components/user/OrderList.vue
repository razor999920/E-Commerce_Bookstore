<template>
  <div>
    <div>
      <div>
        <t-table
          :headers="headers"
          :data="orders"
        >
          <template v-slot:row="props">
            <tr @click="openDetailModal(props.row.id)"
                :class="[props.trClass, props.rowIndex % 2 === 0 ? 'bg-gray-100' : '']" class="cursor-pointer">
              <td :class="props.tdClass">{{ props.row.firstname }} {{ props.row.lastname }} -
                {{ props.row.address.street }}, {{ props.row.address.city }}, {{ props.row.address.province }},
                {{ props.row.address.zip }}
              </td>
              <td :class="props.tdClass">{{ convertDate(props.row.purchasedDate) }}</td>
              <td :class="props.tdClass">${{ props.row.total }}</td>
              <td :class="props.tdClass">{{ props.row.status }}</td>
            </tr>
          </template>
        </t-table>
      </div>
      <div>
        <t-modal variant="big" v-model="showDetailModal">
          <t-table class="my-6" :data="orderDetail" :headers="['title', 'author', 'price', 'quantity']">
          </t-table>
        </t-modal>
      </div>
    </div>
  </div>
</template>

<script>
import moment from "moment"

export default {
  name: "OrderList",
  props: { orders: Array },
  data() {
    return {
      headers: [
        {
          text: "TO",
        },
        {
          value: "purchasedDate",
          text: "Purchase Date",
        },
        {
          value: "total",
          text: "Subtotal",
        },
        {
          value: "status",
          text: "Status",
        },
      ],
      showDetailModal: false,
      orderDetail: [],
    }
  },
  methods: {
    convertDate(date) {
      return moment.unix(date)
        .format("MM/DD/YYYY")
    },
    openDetailModal(orderId) {
      const order = _.find(this.orders, ["id", orderId])
      if (order && order.purchaseItems) {
        this.orderDetail = _.map(order.purchaseItems, (item) => ({
          title: item.book.title,
          author: item.book.author,
          price: `$${item.price}`,
          quantity: item.quantity,
        }))
        this.showDetailModal = true
      }
    },
  },
}
</script>

<style scoped>

</style>
