package com.example.learningvn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(configurer -> configurer
            .requestMatchers("/home", "/").permitAll()
            .requestMatchers("/admin/**").hasRole("ADMIN")
            .requestMatchers("user/**").hasAnyRole("USER", "ADMIN")
            .anyRequest().authenticated() //--- other requests must be authenticated.
        )
        .formLogin(form -> form
            .loginPage("/login")
            .loginProcessingUrl("/auth")
            .defaultSuccessUrl("/dashboard", true)
            .failureUrl("/login?error=true")
            .permitAll()
        )
        .logout(logout -> logout
            .logoutUrl("/logout")
            .logoutSuccessUrl("/login?logout=true")
            .invalidateHttpSession(true)
            .deleteCookies("JSESSIONID")
            .permitAll()
        );
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
        .username("user")
        .password("password")
        .roles("USER")
        .build();

        UserDetails admin = User.builder()
        .username("admin")
        .password("admin123")
        .roles("ADMIN")
        .build();

        return new InMemoryUserDetailsManager(user, admin);
    }
}
