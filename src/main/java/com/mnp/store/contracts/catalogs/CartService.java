package com.mnp.store.contracts.catalogs;

import com.mnp.store.domain.catalogs.CartItem;
import com.mnp.store.domain.users.User;

public interface CartService {
    
    public void addCartItem (CartItem cartitem, User user, long id);

    public void removeCartItem (CartItem cartitem);

}
