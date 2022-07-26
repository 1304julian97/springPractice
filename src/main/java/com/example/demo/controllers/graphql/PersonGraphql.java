package com.example.demo.controllers.graphql;


import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.example.demo.graphql.generated.GetPersonQueryResolver;
import com.example.demo.graphql.generated.model.PersonTO;
import com.example.demo.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@AllArgsConstructor
public class PersonGraphql implements GetPersonQueryResolver, GraphQLMutationResolver {

    private final PersonService personService;


    @Override
    public Flux<PersonTO> getPerson(String exactNameIgnoreCase) {
        System.out.println(exactNameIgnoreCase);
        System.out.println("___________________________________");
        System.out.println("___________________________________");
        System.out.println("___________________________________");
        System.out.println("___________________________________");
        System.out.println("___________________________________");
        System.out.println("___________________________________");
        System.out.println("___________________________________");
        return personService.findByNameIgnoreCase(exactNameIgnoreCase).map(p -> new PersonTO(p.getName(),p.getLastName()));
    }
}
