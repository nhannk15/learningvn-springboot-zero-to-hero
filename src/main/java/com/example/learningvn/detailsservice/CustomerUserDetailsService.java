package com.example.learningvn.detailsservice;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    public UserDetails createSampleUser(String username) {
        return org.springframework.security.core.userdetails.User
                .withUsername("thanhnha")
                .password("{noop}22042006")
                .roles("USER")
                .build();
    }

}
