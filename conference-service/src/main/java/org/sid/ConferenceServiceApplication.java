package org.sid;


import com.example.conferenceservice.Repository.ConferenceRepository;
import com.example.conferenceservice.entities.Conference;
import com.example.conferenceservice.entities.Review;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Arrays;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ConferenceServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConferenceServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ConferenceRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                Conference c1 = new Conference(null, "Microservices 101", "academique",
                        LocalDate.now().plusDays(10), 90, 120, 4.5);
                Review r1 = new Review(null, LocalDate.now(), "Très instructif", 5);
                c1.getReviews().add(r1);

                Conference c2 = new Conference(null, "Selling with APIs", "commercial",
                        LocalDate.now().plusDays(20), 60, 80, 4.0);

                repository.saveAll(Arrays.asList(c1, c2));
            }
            repository.findAll().forEach(System.out::println);
        };
    }
}