package com.example.demo.service;

import com.example.demo.dao.UserRepository;
import com.example.demo.entity.UserEntity;
import com.example.demo.security.authtication.UserPrinciple;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsManager {

    private final UserRepository userRepository = new UserRepository();

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Optional<UserEntity> optionalUser = userRepository.findUserByName(s);

        // TODO find user role
        String[] roles = new String[]{"USER"};
        List<GrantedAuthority> authorityList = new ArrayList<>(roles.length);
        for (String role : roles) {
            authorityList.add(new SimpleGrantedAuthority("ROLE_" + role));
        }

        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("Cannot find user " + s);
        }

        UserEntity user = optionalUser.get();

        return new UserPrinciple(user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                authorityList);

    }

    @Override
    public void createUser(UserDetails userDetails) {
        //TODO to implement
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
    public void changePassword(String s, String s1) {
        //TODO to implement
    }

    @Override
    public boolean userExists(String s) {
        // TODO to implement
        return false;
    }
}
