package com.mnp.store.domain.catalogs.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mnp.store.common.BaseEntity;
import com.mnp.store.domain.catalogs.publisher.Publisher;
import com.mnp.store.domain.catalogs.review.Review;
import com.mnp.store.domain.catalogs.author.Author;
import com.mnp.store.domain.catalogs.category.Category;
import com.mnp.store.domain.catalogs.picture.Picture;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product extends BaseEntity {

    @NotNull
    @Size(min = 10, max = 50)
    @Column(unique = true, nullable = false, length = 50)
    private String isbn;

    @NotNull
    @Size(max = 254)
    @Column(nullable = false, length = 254)
    private String title;

    @Min(0)
    @Max(10000)
    private int inventory;

    @NotNull
    @DecimalMin(value = "0.0")
    @Digits(integer = 4, fraction = 2)
    @Column(nullable = false)
    private BigDecimal price = BigDecimal.ZERO;

    @Size(max = 254)
    @Column(length = 254)
    private String image;

    private String edition;

    private int year;

    @JsonIgnore
    @ManyToMany(mappedBy = "products")
    private Set<Author> authors = new HashSet<>();

    @JsonIgnore
    @ManyToOne
    private Publisher publisher;

    @JsonIgnore
    @ManyToMany(mappedBy = "products")
    private Set<Category> categories = new HashSet<>();

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "product_picture",
            joinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "picture_id", referencedColumnName = "id")}
    )
    @BatchSize(size = 5)
    private Set<Picture> galleries = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private Set<Review> reviews = new HashSet<>();
}
