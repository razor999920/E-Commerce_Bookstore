package com.mnp.store.domain.catalogs;

import javax.persistence.*;

import com.mnp.store.common.BaseEntity;

import java.util.List;

@Entity
@Table(name = "order")
public class Order extends BaseEntity{
    @Column(unique = true, nullable = false)
    private long order_number;

    @Column(nullable = false)
    private List<Product> product;

    @Column(nullable = false)
    private int total;

    public long get_order_numer () {
        return this.order_number;
    }

    public void set_order_number (long order_number) {
        this.order_number = order_number;
    }

    public List<Product> get_items () {
        return this.product;
    }

    public void set_items (List<Product> items) {
        this.product = items;
    }

    public double get_total () {
        return this.total;
    }

    public void set_total (int price) {
        this.total = price;
    }
}