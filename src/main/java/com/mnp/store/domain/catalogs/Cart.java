package com.mnp.store.domain.catalogs;

import javax.persistence.Entity;

import com.mnp.store.common.BaseEntity;
import com.mnp.store.domain.users.User;

import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name  = "cart")
public class Cart {
    
    @Column (nullable = false)
    private long id;
    
    @ManyToOne
    @Column (nullable = false)
    private User user;

    @OneToMany(mappedBy = "cartitems")
    @Column (nullable = false)
    private List<CartItem> items;

    public long getId () {
        return id;
    }

    public void setId (long id) {
        this.id = id;
    }

    public User getUser () {
        return user;
    }

    public void setUser (User user) {
        this.user = user;
    }

    public List<CartItem> getItems () {
        return items;
    }

    public void setItems (CartItem items) {
        this.items.add(items);
    }
    



}
