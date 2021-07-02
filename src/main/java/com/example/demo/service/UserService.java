package com.example.demo.service;

import com.example.demo.dao.UserRepository;
import com.example.demo.entity.UserJpa;
import com.example.demo.model.AppUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository = new UserRepository();


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Optional<UserJpa> optionalUser = userRepository.findUserByName(s);

        // TODO find user role
        String[] roles = new String[]{"USER"};
        List<GrantedAuthority> authorityList = new ArrayList<>(roles.length);
        for (String role : roles) {
            authorityList.add(new SimpleGrantedAuthority("ROLE_" + role));
        }

        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("Cannot find user " + s);
        }

        UserJpa user = optionalUser.get();
        return new AppUser(
                user.getUserIdentity(),
                user.getPassword(),
                authorityList,
                user.getId()
        );


    }
}
