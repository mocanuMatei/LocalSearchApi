package com.localsearch.localsearchapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class LocalSearchApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(LocalSearchApiApplication.class, args);
    }

}
