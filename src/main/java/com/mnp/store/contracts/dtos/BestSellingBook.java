package com.mnp.store.contracts.dtos;

import java.math.BigDecimal;

public interface BestSellingBook {
    int getId();

    String getTitle();

    String getIsbn();

    String getAuthor();

    String getImageUrl();

    BigDecimal getSale();

    int getQuantity();
}
