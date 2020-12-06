package com.mnp.store.contracts.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class CreateCartDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private String blob;

    public String getBlob() {
        return blob;
    }

    public void setBlob(String blob) {
        this.blob = blob;
    }
}
