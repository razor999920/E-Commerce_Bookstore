package com.mnp.store.contracts.dtos;

import java.io.Serializable;
import java.math.BigDecimal;

public class BuyingStatResultDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;
    private BigDecimal spending;

    public BuyingStatResultDto(String username, BigDecimal spending) {
        this.username = username;
        this.spending = spending;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public BigDecimal getSpending() {
        return spending;
    }

    public void setSpending(BigDecimal spending) {
        this.spending = spending;
    }
}
