package com.example.demo.entity;

public class UserJpa {

    private Long id;

    // can be username or email
    private String userIdentity;
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserIdentity() {
        return userIdentity;
    }

    public void setUserIdentity(String userIdentity) {
        this.userIdentity = userIdentity;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
