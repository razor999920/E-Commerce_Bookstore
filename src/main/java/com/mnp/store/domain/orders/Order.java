package com.mnp.store.domain.orders;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mnp.store.common.BaseEntity;
import com.mnp.store.domain.users.Address;
import com.mnp.store.domain.users.User;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "mnp_order")
public class Order extends BaseEntity {
    @Size(max = 254)
    @Column(length = 254)
    private String note;

    @ManyToOne
    private OrderStatus orderStatus;

    @JsonIgnore
    @OneToMany(mappedBy = "order")
    private Set<OrderItem> items = new HashSet<>();

    @JsonIgnore
    @ManyToOne
    private User user;

    @JsonIgnore
    @ManyToOne
    private Address shippingAddress;

    @JsonIgnore
    @ManyToOne
    private Address billingAddress;
}
