package com.soudegesu.example.webflux.controller;

import com.soudegesu.example.webflux.repository.PostgresqlAsyncClient;
import com.soudegesu.example.webflux.response.Customer;
import io.r2dbc.client.R2dbc;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class CustomerController {

    @GetMapping(value = "/controller/customers", produces = { MediaType.APPLICATION_JSON_VALUE })
    public Mono<List<Customer>> customers() {
        R2dbc dbc = new PostgresqlAsyncClient().getConnection();

        return dbc.withHandle(handle ->
                handle.createQuery("SELECT id, name FROM customer;")
                        .mapResult(result ->
                                result.map((row, metadata) ->
                                        new Customer(
                                                row.get("id", Integer.class),
                                                row.get("name", String.class)
                                        )
                                )
                        )
        ).collectList();
    }
}
