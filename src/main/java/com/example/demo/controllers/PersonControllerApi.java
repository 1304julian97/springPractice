package com.example.demo.controllers;


import com.example.demo.infra.PersonRecord;
import com.example.demo.models.Person;
import com.example.demo.models.dtos.PersonDTO;
import com.example.demo.service.PersonService;
import io.vavr.control.Option;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@Log4j2
public class PersonControllerApi {

    private final PersonService personService;


    @GetMapping("/all")
    public Flux<Person> getAll(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer numberPerPage){

        Option<Integer> optionalPageNumber = Option.of(page);
        Option<Integer> optionalnumberPerPage = Option.of(numberPerPage);
        return personService.getAll(optionalPageNumber, optionalnumberPerPage);
    }

    @GetMapping("/find/{partialName}")
    public Flux<PersonDTO> findByName(@PathVariable("partialName")String partialName,@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer numberPerPage){
        System.out.println(partialName);
        System.out.println(page);
        System.out.println(numberPerPage);
        Option<Integer> optionalPageNumber = Option.of(page);
        Option<Integer> optionalnumberPerPage = Option.of(numberPerPage);
        Flux<Person> personRecor = personService.findByName(partialName,optionalPageNumber,optionalnumberPerPage);
        return personRecor.map(p -> new PersonDTO(p.getName(),p.getLastName()));

    }


    @PostMapping("/save")
    public Mono<PersonDTO> save(@RequestBody PersonDTO personDTO){
        Mono<Person> personRecord = personService.post(new Person(personDTO.getName(), personDTO.getLastName())).log();
        //personRecord.subscribe();
        return personRecord.map( pr -> new PersonDTO(pr.getName(),pr.getLastName()));
    }

    @GetMapping("/find/{lastNameIgnoreCase}/{partialName}")
    public Flux<PersonDTO> findByLastNameIgnoreCaseAndNameContains(@PathVariable("lastNameIgnoreCase") String lastName, @PathVariable("partialName") String name){
        return personService.findByLastNameIgnoreCaseAndNameContains(lastName,name).map( pr -> new PersonDTO(pr.getName(),pr.getLastName()));
    }


}
