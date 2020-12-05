package com.mnp.store.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 2, max = 64)
    @Column(name = "username", length = 64, nullable = false, unique = true)
    private String username;

    @NotNull
    @Size(min = 2, max = 64)
    @Column(name = "email", length = 64, nullable = false, unique = true)
    private String email;

    @Column(name = "email_confirmed")
    private Boolean emailConfirmed;

    @NotNull
    @JsonIgnore
    @Size(min = 60, max = 60)
    @Column(name = "password", length = 60, nullable = false)
    private String password;

    @OneToMany(mappedBy = "user")
    private Set<Purchase> purchases = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "user_review",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "review_id", referencedColumnName = "id"))
    private Set<Review> reviews = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Address> addresses = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public User username(String username) {
        this.username = username;
        return this;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public User email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean isEmailConfirmed() {
        return emailConfirmed;
    }

    public User emailConfirmed(Boolean emailConfirmed) {
        this.emailConfirmed = emailConfirmed;
        return this;
    }

    public void setEmailConfirmed(Boolean emailConfirmed) {
        this.emailConfirmed = emailConfirmed;
    }

    public String getPassword() {
        return password;
    }

    public User password(String password) {
        this.password = password;
        return this;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Purchase> getPurchases() {
        return purchases;
    }

    public User purchases(Set<Purchase> purchases) {
        this.purchases = purchases;
        return this;
    }

    public User addPurchase(Purchase purchase) {
        this.purchases.add(purchase);
        purchase.setUser(this);
        return this;
    }

    public User removePurchase(Purchase purchase) {
        this.purchases.remove(purchase);
        purchase.setUser(null);
        return this;
    }

    public void setPurchases(Set<Purchase> purchases) {
        this.purchases = purchases;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public User roles(Set<Role> roles) {
        this.roles = roles;
        return this;
    }

    public User addRole(Role role) {
        this.roles.add(role);
        role.getUsers().add(this);
        return this;
    }

    public User removeRole(Role role) {
        this.roles.remove(role);
        role.getUsers().remove(this);
        return this;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public User reviews(Set<Review> reviews) {
        this.reviews = reviews;
        return this;
    }

    public User addReview(Review review) {
        this.reviews.add(review);
        review.getUsers().add(this);
        return this;
    }

    public User removeReview(Review review) {
        this.reviews.remove(review);
        review.getUsers().remove(this);
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
        if (!(o instanceof User)) {
            return false;
        }
        return id != null && id.equals(((User) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "User{" +
                "id=" + getId() +
                ", username='" + getUsername() + "'" +
                ", email='" + getEmail() + "'" +
                ", emailConfirmed='" + isEmailConfirmed() + "'" +
                ", password='" + getPassword() + "'" +
                "}";
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }
}


