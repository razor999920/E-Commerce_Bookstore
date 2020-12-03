package com.mnp.store.domain.catalogs;
import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@Table(name = "cartitem")
public class CartItem {
    
    @Column(nullable = false)
    private long id;

    @ManyToOne
    @Column(nullable = false)
    private Cart cart;

    @OneToOne
    @Column(nullable = false)
    private Product product;

    public long getId () {
        return id;
    }

    public void setId (long id) {
        this.id = id;
    }

    public Cart getCart () {
        return cart;
    }

    public void setCart (Cart cart) {
        this.cart = cart;
    }

    public Product getProduct () {
        return product;
    }

    public void setProduct (Product product) {
        this.product = product;
    }


}
