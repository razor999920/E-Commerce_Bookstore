package com.mnp.store.domain.orders;

import com.mnp.store.common.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "order_status")
public class OrderStatus extends BaseEntity {

    @NotNull
    @Size(min = 1, max = 10)
    @Column(nullable = false, unique = true, length = 10)
    private String name;

    public OrderStatus() {
    }

    public OrderStatus(long id, String name) {
        this.name = name;
        this.setId(id);
    }
}
