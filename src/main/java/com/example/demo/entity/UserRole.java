package com.example.demo.entity;

public class UserRole {

    private UserJpa user;
    private RoleJpa role;

    public UserJpa getUser() {
        return user;
    }

    public void setUser(UserJpa user) {
        this.user = user;
    }

    public RoleJpa getRole() {
        return role;
    }

    public void setRole(RoleJpa role) {
        this.role = role;
    }
}
