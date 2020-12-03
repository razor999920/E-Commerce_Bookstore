package com.mnp.store.domain.catalogs.category;

import com.mnp.store.common.BaseEntity;
import com.mnp.store.domain.catalogs.product.Product;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "category")
public class Category extends BaseEntity {

    @NotNull
    @Size(max = 50)
    @Column(unique = true, nullable = false, length = 50)
    private String name;

    @ManyToMany
    @JoinTable(name = "category_product",
            joinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"))
    @BatchSize(size = 20)
    private Set<Product> products = new HashSet<>();
}
