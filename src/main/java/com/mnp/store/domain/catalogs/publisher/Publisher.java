package com.mnp.store.domain.catalogs.publisher;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mnp.store.common.BaseEntity;
import com.mnp.store.domain.catalogs.product.Product;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "publisher")
public class Publisher extends BaseEntity {

    @NotNull
    @Size(min = 2, max = 254)
    @Column(unique = true, nullable = false, length = 254)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "publisher")
    private Set<Product> products = new HashSet<>();
}
