package com.culicode.dating.mewmew.domain;

public enum ERole {
    ADMIN(1, "ADMIN"),
    USER(2, "USER");

    private int id;
    private String name;

    private ERole(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static ERole getRoleById(int id) {
        for (ERole role : ERole.values()) {
            if (role.id == id) {
                return role;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
