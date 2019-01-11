package com.soudegesu.example.webflux.handler;

import com.soudegesu.example.webflux.response.Customer;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;

@Component
public class CustomerHandler {

    private static final List<Customer> CUSTOMERS = Collections.unmodifiableList(
            new ArrayList() {{
                add(new Customer(1, "John"));
                add(new Customer(2, "Mike"));
                add(new Customer(3, "Ken"));
            }});

    public RouterFunction<ServerResponse> routes() {
        return nest(path("/customers"),
                route(GET("/"), this::getCustomers)
//                .andRoute(GET("/{id}"))
        );

    }

    private Mono<ServerResponse> getCustomers(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(CUSTOMERS));
    }
}
