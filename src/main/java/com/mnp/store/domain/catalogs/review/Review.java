package com.mnp.store.domain.catalogs.review;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mnp.store.common.BaseEntity;
import com.mnp.store.domain.catalogs.product.Product;
import com.mnp.store.domain.users.User;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "review")
public class Review extends BaseEntity {

    @NotNull
    @Size(min = 2, max = 254)
    @Column(nullable = false, length = 254)
    private String comment;

    @Min(1)
    @Max(5)
    private double rating;

    @JsonIgnore
    @OneToOne
    private User commenter;

    @JsonIgnore
    @ManyToOne
    private Product product;
}
