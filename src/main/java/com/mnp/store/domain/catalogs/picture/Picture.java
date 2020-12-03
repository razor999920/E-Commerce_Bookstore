package com.mnp.store.domain.catalogs.picture;

import com.mnp.store.common.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "picture")
public class Picture extends BaseEntity {

    @NotNull
    @Size(min = 2, max = 254)
    @Column(unique = true, nullable = false, length = 254)
    private String url;
}
