package com.mnp.store.contracts.dtos;

import com.mnp.store.domain.Address;
import com.mnp.store.domain.constants.PurchaseStatus;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CreatePurchaseDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 20)
    private String firstname;

    @NotNull
    @Size(max = 20)
    private String lastname;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PurchaseStatus status;

    @NotNull
    @NotEmpty
    private List<CreatePurchaseItemDto> items = new ArrayList<>();

    @NotNull
    private Address address;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public List<CreatePurchaseItemDto> getItems() {
        return items;
    }

    public void setItems(List<CreatePurchaseItemDto> items) {
        this.items = items;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public PurchaseStatus getStatus() {
        return status;
    }

    public void setStatus(PurchaseStatus status) {
        this.status = status;
    }
}

