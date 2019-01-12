package com.soudegesu.example.webflux.handler;

import com.soudegesu.example.webflux.repository.PostgresqlAsyncClient;
import com.soudegesu.example.webflux.response.Customer;
import io.r2dbc.client.R2dbc;
import io.r2dbc.postgresql.PostgresqlResult;
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

    public RouterFunction<ServerResponse> routes() {
        return nest(path("/customers"),
                route(GET("/"), this::getCustomers)
//                .andRoute(GET("/{id}"))
        );

    }

    private Mono<ServerResponse> getCustomers(ServerRequest request) {

        R2dbc dbc = new PostgresqlAsyncClient().getConnection();
        Flux<Customer> data = dbc.withHandle(handle ->
                        handle.createQuery("SELECT id, name FROM customer;")
                                .mapResult(
                                        result ->
                                                result.map((row, metadata) ->
                                                        new Customer(
                                                                row.get("id", Integer.class),
                                                                row.get("name", String.class)
                                                        )
                                                )
                                )
                        );
        return data
                .then(ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(data, Customer.class)));

    }
}
