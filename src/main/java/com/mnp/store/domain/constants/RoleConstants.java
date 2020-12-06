package com.mnp.store.domain.constants;

import com.mnp.store.domain.Role;

public final class RoleConstants {
    public static final String ADMIN = "ROLE_ADMIN";
    public static final String USER = "ROLE_USER";
    public static final String ANONYMOUS = "ROLE_ANONYMOUS";

    public static Role admin() {
        Role role = new Role();
        role.setId(1L);
        role.setName(ROLE.ROLE_ADMIN);
        return role;
    }

    public static Role user() {
        Role role = new Role();
        role.setId(2L);
        role.setName(ROLE.ROLE_USER);
        return role;
    }

    public static Role anonymous() {
        Role role = new Role();
        role.setId(3L);
        role.setName(ROLE.ROLE_ANONYMOUS);
        return role;
    }

    private RoleConstants() {
    }
}
