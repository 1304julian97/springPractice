package com.example.demo.controllers;


import com.example.demo.infra.PersonRecord;
import com.example.demo.models.Person;
import com.example.demo.models.dtos.PersonDTO;
import com.example.demo.service.PersonService;
import io.vavr.control.Option;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class PersonControllerApi {

    private final PersonService personService;


    @GetMapping("/all")
    public Flux<Person> getAll(){
        return personService.getAll();
    }

    @GetMapping("/find/{partialName}")
    public List<PersonDTO> findByName(@PathVariable("partialName")String partialName, @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer numberPerPage){
        System.out.println(partialName);
        Option<Integer> optionalPageNumber = Option.of(page);
        Option<Integer> optionalnumberPerPage = Option.of(numberPerPage);
        List<PersonRecord> personRecor = personService.findByName(partialName, optionalPageNumber, optionalnumberPerPage);
        return personRecor.stream().map(p -> new PersonDTO(p.getName(),p.getLastName())).collect(Collectors.toList());

    }


    @PostMapping("/save")
    public Mono<PersonDTO> save(@RequestBody PersonDTO personDTO){
        Mono<PersonRecord> personRecord = personService.post(new Person(personDTO.getName(), personDTO.getLastName()));
        return personRecord.map( pr -> new PersonDTO(pr.getName(),pr.getLastName()));
    }


}
