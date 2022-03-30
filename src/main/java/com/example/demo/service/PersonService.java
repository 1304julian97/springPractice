package com.example.demo.service;

import com.example.demo.infra.PersonRecord;
import com.example.demo.infra.repository.PersonRepository;
import com.example.demo.models.Person;
import io.vavr.control.Option;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class PersonService {


    private final PersonRepository personRepository;

    public Flux<Person> getAll() {
/*        List<Person> people = new ArrayList<>();
        people.add(new Person("JUlian","Carvajal"));
        people.add(new Person("JUlian","Castro"));
        people.add(new Person("JUlian","Alzate"));
        people.add(new Person("JUlian","Montoya"));
        return people;*/
        return findAll();
    }

    public Flux<Person> findAll(){
        return personRepository.findAll().map(personRecord -> new Person(personRecord.getName(),personRecord.getLastName()));
        //return StreamSupport.stream(personRepository.findAll().spliterator(),false).map(personRecord -> new Person(personRecord.getName(),personRecord.getLastName())).collect(Collectors.toList());
    }

    public Mono<PersonRecord> post(Person person){
        return personRepository.save(new PersonRecord(null,person.getName(),person.getLastName()));
    }

    public List<PersonRecord> findByName(String partialName, Option<Integer> optionalPageNumber, Option<Integer> optionalnumberPerPage) {
        //return personRepository.finByName(partialName, PageRequest.of(optionalPageNumber.getOrElse(0),optionalnumberPerPage.getOrElse(10))).toList();
        return personRepository.finByName(partialName).collectList().block();
    }
}
