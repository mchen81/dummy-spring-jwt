package com.example.demo.dao;

import com.example.demo.entity.UserJpa;

import java.util.Optional;

public class UserRepository {

    public Optional<UserJpa> findUserByName(String name) {
        UserJpa fakeUser = new UserJpa();
        fakeUser.setId(999L);
        fakeUser.setUserIdentity("Fake");
        fakeUser.setPassword("$2a$10$cpijmsGyVjkxAD38NRaKluX1lmVDhv4j6S3XExlqeb7Ql8pgt/niW");
        return Optional.of(fakeUser);
    }

}
