package com.example.learningvn;

//import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.context.WebServerInitializedEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.example.learningvn.model.entity.Dog;
//import com.example.learningvn.model.entity.User;
import com.example.learningvn.repository.DogRepository;
//import com.example.learningvn.model.entity.Product;
//import com.example.learningvn.repository.ProductRepository;
//import com.example.learningvn.repository.UserRepository;

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
    CommandLineRunner initDatabase(DogRepository repository) {
        return args -> {

            repository.save(new Dog("Terror", "white", null));
            repository.save(new Dog("Hunter", "black", null));
            repository.save(new Dog("Charlie", "brown", null));
            repository.save(new Dog("Thunder", "golden", null));
            repository.save(new Dog("Biscuit", "white", null));
            repository.save(new Dog("Shadow", "gray", null));
            repository.save(new Dog("Maximus", "black", null));
            repository.save(new Dog("Diamond", "cream", null));
            repository.save(new Dog("Captain", "brown", null));
            repository.save(new Dog("Sunshine", "white", null));
            repository.save(new Dog("Cinnamon", "golden", null));
            repository.save(new Dog("Bentley", "black", null));
            repository.save(new Dog("Samurai", "gray", null));
            repository.save(new Dog("Chocolate", "brown", null));
            repository.save(new Dog("Jackson", "white", null));
            repository.save(new Dog("Pumpkin", "cream", null));
            repository.save(new Dog("Soldier", "black", null));
            repository.save(new Dog("Peanutty", "golden", null));
            repository.save(new Dog("Kingston", "brown", null));
            repository.save(new Dog("Mochichi", "white", null));
            repository.save(new Dog("Sparrow", "gray", null));
            repository.save(new Dog("Blizzard", "white", null));
            repository.save(new Dog("Majesty", "cream", null));
            repository.save(new Dog("Monster", "black", null));
            repository.save(new Dog("Freedom", "brown", null));
            repository.save(new Dog("Rockets", "golden", null));
            repository.save(new Dog("Tornado", "gray", null));
            repository.save(new Dog("Panther", "black", null));
            repository.save(new Dog("Explorer", "white", null));
            repository.save(new Dog("Miracle", "cream", null));
            repository.save(new Dog("Lightning", "golden", null));
            repository.save(new Dog("Kingdom", "brown", null));
            repository.save(new Dog("Galaxy", "gray", null));
            repository.save(new Dog("Captainy", "white", null));
            repository.save(new Dog("Snowball", "cream", null));
            repository.save(new Dog("Victory", "black", null));
            repository.save(new Dog("Spartan", "brown", null));
            repository.save(new Dog("Stormy", "golden", null));
            repository.save(new Dog("Mystery", "gray", null));
            repository.save(new Dog("Treasure", "white", null));
            // repository.save(new User("khacnhan21", "nhannk15@gmail.com", "21012006",
            // null, null));
            // repository.save(new User("khanh", "khanhkt@gmail.com", "kieu123123", null,
            // null));
            // repository.save(new User("thanhnha", "thanhnha@gmail.com", "22042006", null,
            // null));
            // System.out.println("User Count: " + repository.count());
            // User foundUser = repository.findById(2L).get();
            // if (foundUser != null) {
            // System.out.println("User found: " + foundUser.getUsername());
            // }

            // repository.save(new Product(
            // null,
            // "iPhone 15",
            // "Điện thoại iPhone 15 128GB",
            // new BigDecimal("24990000"),
            // 50,
            // "Điện thoại",
            // null,
            // null
            // ));

            // repository.save(new Product(
            // null,
            // "MacBook Air M2",
            // "Laptop Apple MacBook Air M2 2023",
            // new BigDecimal("32990000"),
            // 30,
            // "Laptop",
            // null,
            // null
            // ));
        };
    }
}
