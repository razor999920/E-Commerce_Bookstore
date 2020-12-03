package com.mnp.store.contracts.catalogs;

import com.mnp.store.domain.catalogs.Product;

public interface CartItemService {
    public void addProductToCart (Product product);

    public void removeProdcutFromCart (Product product);
}
