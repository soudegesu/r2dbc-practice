package com.soudegesu.example.webflux.repository;

import io.r2dbc.client.R2dbc;
import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;

public class PostgresqlAsyncClient {

    private final PostgresqlConnectionConfiguration configuration =
            PostgresqlConnectionConfiguration.builder()
                    .database("testdb")
                    .host("localhost")
                    .port(5432)
                    .username("postgres")
                    .password("postgres")
                    .build();

    private final PostgresqlConnectionFactory connectionFactory =
            new PostgresqlConnectionFactory(this.configuration);


    public R2dbc getConnection() {
        return new R2dbc(this.connectionFactory);
    }
}
