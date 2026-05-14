package com.example.learningvn.detailsservice;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.example.learningvn.model.entity.User;
import com.example.learningvn.repository.UserRepository;

public class CustomerUserDetailsService implements UserDetailsService {

    private final UserRepository repository;

    public CustomerUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return createSampleUser(user);
    }

    public UserDetails createSampleUser(User user) {
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                //.roles(user.getRole())
                .build();
    }

}
