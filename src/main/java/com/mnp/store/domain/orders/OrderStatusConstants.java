package com.mnp.store.domain.orders;

public final class OrderStatusConstants {
    public static String ORDER_IN_CART = "IN_CART";
    public static String ORDER_PAID = "PAID";
    public static String ORDER_SHIPPED = "SHIPPED";

    public static OrderStatus StatusInCart = new OrderStatus(1, ORDER_IN_CART);
    public static OrderStatus StatusPaid = new OrderStatus(2, ORDER_PAID);
    public static OrderStatus StatusShipped = new OrderStatus(3, ORDER_SHIPPED);
}
