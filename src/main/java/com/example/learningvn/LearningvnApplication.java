package com.example.learningvn;

//import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.context.WebServerInitializedEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.example.learningvn.model.entity.User;
//import com.example.learningvn.model.entity.Product;
//import com.example.learningvn.repository.ProductRepository;
import com.example.learningvn.repository.UserRepository;

@SpringBootApplication
public class LearningvnApplication {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeHttpRequests(configurer -> configurer.anyRequest().permitAll());
		httpSecurity.httpBasic(Customizer.withDefaults());
		httpSecurity.csrf(csrf -> csrf.disable());
        httpSecurity.headers(headers -> headers.frameOptions(frame -> frame.disable()));
		return httpSecurity.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(LearningvnApplication.class, args);
	}

	@EventListener
	public void onApplicationEvent(WebServerInitializedEvent event) {
		System.out.println("\n\n=============================The application is running on " + event.getWebServer().getPort() + "=============================\n\n");
	}

	@Bean
	CommandLineRunner initDatabase(UserRepository repository) {
		 return args -> {
            repository.save(new User("khacnhan21", "nhannk15@gmail.com", "21012006", null, null));
            repository.save(new User("khanh", "khanhkt@gmail.com", "kieu123123", null, null));
            repository.save(new User("thanhnha", "thanhnha@gmail.com", "22042006", null, null));
            System.out.println("User Count: " + repository.count());
            User foundUser = repository.findById(2L).get();
            if (foundUser != null) {
                System.out.println("User found: " + foundUser.getUsername());
            }
            // repository.save(new Product(
            //     null, 
            //     "iPhone 15", 
            //     "Điện thoại iPhone 15 128GB", 
            //     new BigDecimal("24990000"), 
            //     50, 
            //     "Điện thoại", 
            //     null, 
            //     null
            // ));
            
            // repository.save(new Product(
            //     null, 
            //     "MacBook Air M2", 
            //     "Laptop Apple MacBook Air M2 2023", 
            //     new BigDecimal("32990000"), 
            //     30, 
            //     "Laptop", 
            //     null, 
            //     null
            // ));
        };
    }
}
