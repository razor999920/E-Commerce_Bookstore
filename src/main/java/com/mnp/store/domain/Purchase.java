package com.mnp.store.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(name = "purchased_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Instant purchasedDate;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "total", precision = 21, scale = 2, nullable = false)
    private BigDecimal total;

    @NotNull
    @NotEmpty
    @OneToMany(cascade = CascadeType.ALL)
    private Set<PurchaseItem> purchaseItems = new HashSet<>();

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToOne
    @JsonIgnore
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
        return this;
    }

    public Purchase removePurchaseItem(PurchaseItem purchaseItem) {
        this.purchaseItems.remove(purchaseItem);
        return this;
    }

    public void setPurchaseItems(Set<PurchaseItem> purchaseItems) {
        this.purchaseItems = purchaseItems;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddresses(Address address) {
        this.address = address;
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

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Instant getPurchasedDate() {
        return purchasedDate;
    }
}


