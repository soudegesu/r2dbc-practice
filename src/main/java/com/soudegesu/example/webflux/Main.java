package com.soudegesu.example.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootConfiguration
@EnableAutoConfiguration
public class Main {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args); //NOSONAR
        context.registerShutdownHook();
    }

}
