package com.mnp.store.domain.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mnp.store.common.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "address")
public class Address extends BaseEntity {

    @NotNull
    @Size(min = 2, max = 254)
    @Column(nullable = false, length = 254)
    private String addressOne;

    @Size(min = 2, max = 254)
    @Column(length = 254)
    private String addressTwo;

    @NotNull
    @Size(min = 2, max = 254)
    @Column(nullable = false, length = 254)
    private String city;

    @NotNull
    @Size(min = 2, max = 254)
    @Column(nullable = false, length = 254)
    private String province;

    @NotNull
    @Size(min = 2, max = 9)
    @Column(nullable = false, length = 9)
    private String postalCode;

    @NotNull
    @Size(min = 2, max = 254)
    @Column(nullable = false, length = 254)
    private String country;

    @Size(max = 50)
    @Column(length = 50)
    private String firstname;

    @Size(max = 50)
    @Column(length = 50)
    private String lastname;

    @JsonIgnore
    @ManyToOne
    private User user;
}
