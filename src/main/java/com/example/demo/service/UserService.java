package com.example.demo.service;

import com.example.demo.entity.RoleEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.authtication.UserPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService implements UserDetailsManager {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("username not found: " + username)
                );

        return UserPrinciple.valueOf(user);

    }

    @Override
    public void createUser(UserDetails userDetails) {
        UserPrinciple user = (UserPrinciple) userDetails;

        RoleEntity role = roleRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Role Id not found"));

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(user.getEmail());
        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userEntity.setRoles(Set.of(role));
        userRepository.save(userEntity);
    }

    @Override
    public void updateUser(UserDetails userDetails) {
        //TODO to implement
    }

    @Override
    public void deleteUser(String s) {
        //TODO to implement
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        //TODO to implement
    }

    @Override
    public boolean userExists(String username) {
        return userRepository.existsByUsername(username);
    }
}
