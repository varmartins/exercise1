package com.varmartins.exercise1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 *
 */
@SpringBootApplication(scanBasePackages = "com.varmartins.exercise1")
@EnableJpaRepositories("com.varmartins.exercise1.persistence.repositories")
@EntityScan("com.varmartins.exercise1.persistence.entities")
public class Application {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
