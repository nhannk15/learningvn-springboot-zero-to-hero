package com.example.learningvn;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.context.WebServerInitializedEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.example.learningvn.model.entity.Product;
import com.example.learningvn.repository.ProductRepository;

@SpringBootApplication
public class LearningvnApplication {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) {
		httpSecurity.authorizeHttpRequests(configurer -> configurer.anyRequest().permitAll());
		httpSecurity.httpBasic(Customizer.withDefaults());
		httpSecurity.csrf(csrf -> csrf.disable());
		return httpSecurity.build();
	}
	public static void main(String[] args) {
		SpringApplication.run(LearningvnApplication.class, args);
	}

	@EventListener
	public void onApplicationEvent(WebServerInitializedEvent event) {
		System.out.println("\n\n=============================The application is running on " + event.getWebServer().getPort() + "=============================\n\n");
	}

	// @Bean
	// CommandLineRunner initDatabase(ProductRepository repository) {
	// 	 return args -> {
    //         repository.save(new Product(
    //             null, 
    //             "iPhone 15", 
    //             "Điện thoại iPhone 15 128GB", 
    //             new BigDecimal("24990000"), 
    //             50, 
    //             "Điện thoại", 
    //             null, 
    //             null
    //         ));
            
    //         repository.save(new Product(
    //             null, 
    //             "MacBook Air M2", 
    //             "Laptop Apple MacBook Air M2 2023", 
    //             new BigDecimal("32990000"), 
    //             30, 
    //             "Laptop", 
    //             null, 
    //             null
    //         ));
    //     };
    // }
}
