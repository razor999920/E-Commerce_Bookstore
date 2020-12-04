package com.mnp.store.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mnp.store.domain.constants.PurchaseStatus;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * A Purchase.
 */
@Entity
@Table(name = "purchase")
public class Purchase implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 20)
    @Column(name = "firstname", length = 20, nullable = false)
    private String firstname;

    @NotNull
    @Size(max = 20)
    @Column(name = "lastname", length = 20, nullable = false)
    private String lastname;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private PurchaseStatus status;

    @Column(name = "purchased_date")
    private Instant purchasedDate;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "total", precision = 21, scale = 2, nullable = false)
    private BigDecimal total;

    @OneToMany(mappedBy = "purchase")
    private Set<PurchaseItem> purchaseItems = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "purchase_address",
            joinColumns = @JoinColumn(name = "purchase_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "address_id", referencedColumnName = "id"))
    private Set<Address> addresses = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "purchases", allowSetters = true)
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public Purchase firstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public Purchase lastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public PurchaseStatus getStatus() {
        return status;
    }

    public Purchase status(PurchaseStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(PurchaseStatus status) {
        this.status = status;
    }

    public Set<PurchaseItem> getPurchaseItems() {
        return purchaseItems;
    }

    public Purchase purchaseItems(Set<PurchaseItem> purchaseItems) {
        this.purchaseItems = purchaseItems;
        return this;
    }

    public Purchase addPurchaseItem(PurchaseItem purchaseItem) {
        this.purchaseItems.add(purchaseItem);
        purchaseItem.setPurchase(this);
        return this;
    }

    public Purchase removePurchaseItem(PurchaseItem purchaseItem) {
        this.purchaseItems.remove(purchaseItem);
        purchaseItem.setPurchase(null);
        return this;
    }

    public void setPurchaseItems(Set<PurchaseItem> purchaseItems) {
        this.purchaseItems = purchaseItems;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public Purchase addresses(Set<Address> addresses) {
        this.addresses = addresses;
        return this;
    }

    public Purchase addAddress(Address address) {
        this.addresses.add(address);
        address.getPurchases().add(this);
        return this;
    }

    public Purchase removeAddress(Address address) {
        this.addresses.remove(address);
        address.getPurchases().remove(this);
        return this;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    public User getUser() {
        return user;
    }

    public Purchase user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Purchase)) {
            return false;
        }
        return id != null && id.equals(((Purchase) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + getId() +
                ", firstname='" + getFirstname() + "'" +
                ", lastname='" + getLastname() + "'" +
                ", status='" + getStatus() + "'" +
                "}";
    }

    public Instant getPurchasedDate() {
        return purchasedDate;
    }

    public void setPurchasedDate(Instant purchasedDate) {
        this.purchasedDate = purchasedDate;
    }
}


