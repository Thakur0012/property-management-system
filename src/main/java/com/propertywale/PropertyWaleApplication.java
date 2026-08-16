package com.propertywale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the PropertyWale application.
 *
 * PropertyWale is a cloud-ready property search & management portal
 * (think MagicBricks / 99acres / Housing.com) built with Spring Boot,
 * Spring MVC, Spring Data JPA and Thymeleaf.
 */
@SpringBootApplication
public class PropertyWaleApplication {

    public static void main(String[] args) {
        SpringApplication.run(PropertyWaleApplication.class, args);
    }
}
