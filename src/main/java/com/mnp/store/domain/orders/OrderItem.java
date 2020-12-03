package com.mnp.store.domain.orders;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mnp.store.common.BaseEntity;
import com.mnp.store.domain.catalogs.product.Product;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Entity
@Table(name = "order_item")
public class OrderItem extends BaseEntity {

    @NotNull
    @DecimalMin(value = "0.0")
    @Digits(integer = 4, fraction = 2)
    @Column(nullable = false)
    private BigDecimal price;

    @NotNull
    @Min(1)
    @Column(nullable = false)
    private int quantity;

    @JsonIgnore
    @ManyToOne
    private Product product;

    @JsonIgnore
    @ManyToOne
    private Order order;
}
