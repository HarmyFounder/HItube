package com.HarmyIndustries.Hitube.model;

public enum Permission {
    POSTS_READ("posts:read"),
    POSTS_WRITE("posts:write");


    private final String permission;

    public String getPermission() {
        return permission;
    }

    Permission(String permission) {
        this.permission = permission;
    }
}
