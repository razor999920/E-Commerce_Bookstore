package com.mnp.store.domain.constants;

import com.mnp.store.domain.Role;

public final class RoleConstants {
    public static Role admin() {
        Role role = new Role();
        role.setId(1L);
        role.setName(ROLE.ADMIN);
        return role;
    }

    public static Role user() {
        Role role = new Role();
        role.setId(2L);
        role.setName(ROLE.USER);
        return role;
    }

    public static Role anonymous() {
        Role role = new Role();
        role.setId(3L);
        role.setName(ROLE.ANONYMOUS);
        return role;
    }

    private RoleConstants() {
    }
}
