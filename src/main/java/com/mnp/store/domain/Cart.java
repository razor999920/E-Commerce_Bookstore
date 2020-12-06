package com.mnp.store.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mnp.store.common.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "cart")
public class Cart extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    @OneToOne(optional = false, fetch = FetchType.LAZY, mappedBy = "cart")
    @JsonIgnore
    private User user;

    @NotNull
    @NotEmpty
    @Lob
    @Column(name = "item_blob", nullable = false)
    private String itemBlob;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Cart blob(String blob) {
        this.itemBlob = blob;
        return this;
    }

    public Cart user(User user) {
        this.user = user;
        return this;
    }

    public String getItemBlob() {
        return itemBlob;
    }

    public void setItemBlob(String itemBlobs) {
        this.itemBlob = itemBlobs;
    }
}
