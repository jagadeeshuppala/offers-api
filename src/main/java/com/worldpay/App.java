package com.worldpay;

import com.worldpay.model.Offer;
import com.worldpay.repo.OfferRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public CommandLineRunner bootstrapData(OfferRepo repository) {
        return (args) -> {
            // save a couple of customers

            LocalDate today = LocalDate.now();
            LocalDate tomorrow = LocalDate.now().plus(1, ChronoUnit.DAYS);
            LocalDate yesterday = LocalDate.now().minusDays(1);


            repository.save(new Offer("Buy One Get One free", "Buy One Get One free", today, false));
            repository.save(new Offer("Buy One Get Two free", "Buy One Get Two free", tomorrow, false));
            repository.save(new Offer("Buy One Get Three free", "Buy One Get Three free",  yesterday, false));
            repository.save(new Offer("Buy One Get Four free", "Buy One Get Four free",  tomorrow, true));

        };
    }

}
