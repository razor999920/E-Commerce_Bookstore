package com.mnp.store.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * A Book.
 */
@Entity
@Table(name = "book")
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 2, max = 64)
    @Column(name = "title", length = 64, nullable = false, unique = true)
    private String title;

    @NotNull
    @Size(min = 10, max = 13)
    @Column(name = "isbn", length = 13, nullable = false, unique = true)
    private String isbn;

    @Size(max = 254)
    @Column(name = "description", length = 254)
    private String description;

    @Min(value = 0)
    @Column(name = "inventory")
    private Integer inventory;

    @DecimalMin(value = "0")
    @Column(name = "price", precision = 21, scale = 2)
    private BigDecimal price;

    @Size(max = 64)
    @Column(name = "image_url", length = 64)
    private String imageUrl;

    @NotNull
    @Size(max = 64)
    @Column(name = "author", length = 64, nullable = false)
    private String author;

    @Size(max = 64)
    @Column(name = "category", length = 64)
    private String category;

    @OneToOne(mappedBy = "book")
    @JsonIgnore
    private PurchaseItem purchaseItem;

    @OneToMany(mappedBy = "book")
    private Set<Review> reviews = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public Book title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public Book isbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getDescription() {
        return description;
    }

    public Book description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getInventory() {
        return inventory;
    }

    public Book inventory(Integer inventory) {
        this.inventory = inventory;
        return this;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Book price(BigDecimal price) {
        this.price = price;
        return this;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Book imageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAuthor() {
        return author;
    }

    public Book author(String author) {
        this.author = author;
        return this;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public Book category(String category) {
        this.category = category;
        return this;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public PurchaseItem getPurchaseItem() {
        return purchaseItem;
    }

    public Book purchaseItem(PurchaseItem purchaseItem) {
        this.purchaseItem = purchaseItem;
        return this;
    }

    public void setPurchaseItem(PurchaseItem purchaseItem) {
        this.purchaseItem = purchaseItem;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public Book reviews(Set<Review> reviews) {
        this.reviews = reviews;
        return this;
    }

    public Book addReview(Review review) {
        this.reviews.add(review);
        review.setBook(this);
        return this;
    }

    public Book removeReview(Review review) {
        this.reviews.remove(review);
        review.setBook(null);
        return this;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Book)) {
            return false;
        }
        return id != null && id.equals(((Book) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Book{" +
                "id=" + getId() +
                ", title='" + getTitle() + "'" +
                ", isbn='" + getIsbn() + "'" +
                ", description='" + getDescription() + "'" +
                ", inventory=" + getInventory() +
                ", price=" + getPrice() +
                ", imageUrl='" + getImageUrl() + "'" +
                ", author='" + getAuthor() + "'" +
                ", category='" + getCategory() + "'" +
                "}";
    }
}


