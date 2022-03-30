package com.example.demo.configuration;

import com.example.demo.infra.repository.PersonRepository;
import io.r2dbc.h2.H2ConnectionConfiguration;
import io.r2dbc.h2.H2ConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.core.ReactiveDataAccessStrategy;
import org.springframework.data.r2dbc.repository.support.R2dbcRepositoryFactory;
import org.springframework.data.relational.core.mapping.RelationalMappingContext;
import org.springframework.r2dbc.core.DatabaseClient;

@Configuration
public class R2DBCConfiguration extends AbstractR2dbcConfiguration {


    @Bean
    public H2ConnectionFactory connectionFactory() {
        return new H2ConnectionFactory(
                H2ConnectionConfiguration.builder()
                        .url("mem:testdb;DB_CLOSE_DELAY=-1;TRACE_LEVEL_FILE=4")
                        .username("sa")
                        .build());
    }

    @Bean
    public DatabaseClient r2dbcDatabaseClient (ConnectionFactory connectionFactory){
        return DatabaseClient.builder().connectionFactory(connectionFactory).build();
    }

    @Bean
    public R2dbcRepositoryFactory repositoryFactory(DatabaseClient client) {
        RelationalMappingContext context = new RelationalMappingContext();
        return new R2dbcRepositoryFactory(client, (ReactiveDataAccessStrategy) context);
    }

    @Bean
    public PersonRepository personRepository(R2dbcRepositoryFactory repositoryFactory){
        return repositoryFactory.getRepository(PersonRepository.class);
    }
}