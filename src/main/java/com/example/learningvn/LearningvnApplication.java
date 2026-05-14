package com.example.learningvn;

import org.springframework.beans.factory.annotation.Autowired;

//import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.context.WebServerInitializedEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.example.learningvn.model.entity.Dog;
import com.example.learningvn.model.entity.User;
//import com.example.learningvn.model.entity.User;
import com.example.learningvn.repository.DogRepository;
//import com.example.learningvn.model.entity.Product;
//import com.example.learningvn.repository.ProductRepository;
//import com.example.learningvn.repository.UserRepository;
import com.example.learningvn.repository.UserRepository;

@EnableTransactionManagement
@SpringBootApplication
public class LearningvnApplication {
    public static void main(String[] args) {
        SpringApplication.run(LearningvnApplication.class, args);
    }

    @EventListener
    public void onApplicationEvent(WebServerInitializedEvent event) {
        System.out.println("\n\n=============================The application is running on "
                + event.getWebServer().getPort() + "=============================\n\n");
    }

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByUsername("testuser").isEmpty()) {
                User user = new User();
                user.setUsername("nhannk15");
                user.setPassword(passwordEncoder.encode("Nhannk21012006@"));
                user.setEmail("nhannk15@gmail.com");
                user.setEnable(true);
                // set roles nếu cần
                userRepository.save(user);
            }
        };
    }
}
