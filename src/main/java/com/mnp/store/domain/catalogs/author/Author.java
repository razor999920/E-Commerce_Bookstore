package com.mnp.store.domain.catalogs.author;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mnp.store.common.BaseEntity;
import com.mnp.store.domain.catalogs.picture.Picture;
import com.mnp.store.domain.catalogs.product.Product;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "author")
public class Author extends BaseEntity {

    @Size(max = 50)
    @Column(length = 50)
    private String firstname;

    @Size(max = 50)
    @Column(length = 50)
    private String lastname;

    @OneToOne
    private Picture avatar;

    @Size(max = 254)
    @Column(length = 254)
    private String bio;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "author_product",
            joinColumns = {@JoinColumn(name = "author_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")}
    )
    @BatchSize(size = 20)
    private Set<Product> products = new HashSet<>();
}
