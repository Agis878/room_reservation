package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@SpringBootApplication
//@EnableWebMvc
//@EnableTransactionManagement
//@EnableJpaRepositories(basePackages = "com.example")
public class RoomReservationApplication {

    public static void main(String[] args) {
        SpringApplication.run(RoomReservationApplication.class, args);
    }

}
