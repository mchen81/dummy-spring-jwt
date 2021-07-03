package com.example.demo.dao;

import com.example.demo.entity.UserEntity;

import java.util.Optional;

public class UserRepository {

    public Optional<UserEntity> findUserByName(String name) {
        UserEntity fakeUser = new UserEntity();
        fakeUser.setId(999L);
        fakeUser.setUsername("Fake");
        fakeUser.setEmail("test@email.com");
        fakeUser.setPassword("$2a$10$cpijmsGyVjkxAD38NRaKluX1lmVDhv4j6S3XExlqeb7Ql8pgt/niW");
        return Optional.of(fakeUser);
    }

}
