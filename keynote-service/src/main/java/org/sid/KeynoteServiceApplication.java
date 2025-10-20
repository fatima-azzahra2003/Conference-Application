package org.sid;


import com.example.keynoteservice.entities.Keynote;
import com.example.keynoteservice.repository.KeynoteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class KeynoteServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(KeynoteServiceApplication.class, args);
    }


    @Bean
    CommandLineRunner start(KeynoteRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                repository.save(new Keynote(null, "Yassine", "Yassine", "yassine@gmail.com", "Professeur"));
                repository.save(new Keynote(null, "Ali", "Ali", "ali@example.com", "CTO"));
                repository.save(new Keynote(null, "Hamza", "Hamza", "hamza@example.com", "Researcher"));
            }
            repository.findAll().forEach(System.out::println);
        };
    }
}