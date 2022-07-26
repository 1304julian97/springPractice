package com.example.demo.configuration.graphqlconfig;


import com.example.demo.controllers.graphql.PersonGraphql;
import com.example.demo.service.PersonService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonGraphqlConfig {

    @Bean
    public PersonGraphql personGraphql2(PersonService personService){
        return new PersonGraphql(personService);
    }
}
