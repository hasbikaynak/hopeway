package com.hpw.domain.enums;

public enum RoleType {
    ROLE_USER("User"),
    ROLE_ADMIN("Administrator");

    private String name;

    private RoleType(String name) {
        this.name=name;
    }

    public String getName() {
        return name;
    }
}
