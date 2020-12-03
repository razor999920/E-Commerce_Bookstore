package com.mnp.store.domain.authorization;

public final class RoleConstants {
    public static final String ADMIN = "ROLE_ADMIN";
    public static final String USER = "ROLE_USER";
    public static final String ANONYMOUS = "ROLE_ANONYMOUS";

    public static Role admin() {
        Role role = new Role();
        role.setId(1);
        role.setName(ADMIN);
        return role;
    }

    public static Role user() {
        Role role = new Role();
        role.setId(2);
        role.setName(USER);
        return role;
    }

    public static Role anonymous() {
        Role role = new Role();
        role.setId(3);
        role.setName(ANONYMOUS);
        return role;
    }

    private RoleConstants() {
    }
}
