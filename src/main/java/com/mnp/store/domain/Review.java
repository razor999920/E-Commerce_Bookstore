package com.mnp.store.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Review.
 */
@Entity
@Table(name = "review")
public class Review implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Min(value = 1)
    @Max(value = 5)
    @Column(name = "rating", nullable = false)
    private Integer rating;

    @Size(max = 254)
    @Column(name = "comment", length = 254)
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Book book;

    @ManyToMany(mappedBy = "reviews")
    @JsonIgnore
    private Set<User> users = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRating() {
        return rating;
    }

    public Review rating(Integer rating) {
        this.rating = rating;
        return this;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public Review comment(String comment) {
        this.comment = comment;
        return this;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Book getBook() {
        return book;
    }

    public Review book(Book book) {
        this.book = book;
        return this;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Set<User> getUsers() {
        return users;
    }

    public Review users(Set<User> users) {
        this.users = users;
        return this;
    }

    public Review addUser(User user) {
        this.users.add(user);
        user.getReviews().add(this);
        return this;
    }

    public Review removeUser(User user) {
        this.users.remove(user);
        user.getReviews().remove(this);
        return this;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Review)) {
            return false;
        }
        return id != null && id.equals(((Review) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Review{" +
                "id=" + getId() +
                ", rating=" + getRating() +
                ", comment='" + getComment() + "'" +
                "}";
    }
}


