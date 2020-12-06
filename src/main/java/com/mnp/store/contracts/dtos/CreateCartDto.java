package com.mnp.store.contracts.dtos;

import java.io.Serializable;

public class CreateCartDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String blob;

    public String getBlob() {
        return blob;
    }

    public void setBlob(String blob) {
        this.blob = blob;
    }
}
