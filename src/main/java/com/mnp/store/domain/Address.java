package com.mnp.store.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "address")
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "street", length = 100, nullable = false)
    private String street;

    @NotNull
    @Size(max = 20)
    @Column(name = "province", length = 20, nullable = false)
    private String province;

    @NotNull
    @Size(max = 20)
    @Column(name = "country", length = 20, nullable = false)
    private String country;

    @NotNull
    @Size(max = 20)
    @Column(name = "zip", length = 20, nullable = false)
    private String zip;

    @NotNull
    @Size(max = 20)
    @Column(name = "phone", length = 20, nullable = false)
    private String phone;

    @ManyToMany(mappedBy = "addresses")
    @JsonIgnore
    private Set<Purchase> purchases = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "addresses", allowSetters = true)
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public Address street(String street) {
        this.street = street;
        return this;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getProvince() {
        return province;
    }

    public Address province(String province) {
        this.province = province;
        return this;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public Address country(String country) {
        this.country = country;
        return this;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZip() {
        return zip;
    }

    public Address zip(String zip) {
        this.zip = zip;
        return this;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public Address phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<Purchase> getPurchases() {
        return purchases;
    }

    public Address purchases(Set<Purchase> purchases) {
        this.purchases = purchases;
        return this;
    }

    public Address addPurchase(Purchase purchase) {
        this.purchases.add(purchase);
        purchase.getAddresses().add(this);
        return this;
    }

    public Address removePurchase(Purchase purchase) {
        this.purchases.remove(purchase);
        purchase.getAddresses().remove(this);
        return this;
    }

    public void setPurchases(Set<Purchase> purchases) {
        this.purchases = purchases;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Address)) {
            return false;
        }
        return id != null && id.equals(((Address) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Address{" +
                "id=" + getId() +
                ", street='" + getStreet() + "'" +
                ", province='" + getProvince() + "'" +
                ", country='" + getCountry() + "'" +
                ", zip='" + getZip() + "'" +
                ", phone='" + getPhone() + "'" +
                "}";
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}



