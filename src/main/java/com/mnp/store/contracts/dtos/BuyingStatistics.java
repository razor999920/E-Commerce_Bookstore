package com.mnp.store.contracts.dtos;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class BuyingStatistics {
    private String username;
    private BigDecimal spending;

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
