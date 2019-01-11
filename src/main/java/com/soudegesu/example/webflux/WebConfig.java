package com.soudegesu.example.webflux;

import com.soudegesu.example.webflux.handler.CustomerHandler;
import com.soudegesu.example.webflux.handler.HelloHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.DelegatingWebFluxConfiguration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@EnableWebFlux
public class WebConfig extends DelegatingWebFluxConfiguration {

    @Bean
    RouterFunction<ServerResponse> hello(HelloHandler handler) {
        return handler.routes();
    }


    @Bean
    RouterFunction<ServerResponse> customers(CustomerHandler handler) {
        return handler.routes();
    }
}
