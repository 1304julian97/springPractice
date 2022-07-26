package com.example.demo.service;

import com.example.demo.infra.PersonRecord;
import com.example.demo.infra.repository.PersonRepository;
import com.example.demo.models.Person;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PersonService {


    private final PersonRepository personRepository;

    public Flux<Person> getAll(Option<Integer> optionalPageNumber, Option<Integer> optionalnumberPerPage) {
/*        List<Person> people = new ArrayList<>();
        people.add(new Person("JUlian","Carvajal"));
        people.add(new Person("JUlian","Castro"));
        people.add(new Person("JUlian","Alzate"));
        people.add(new Person("JUlian","Montoya"));
        return people;*/
        return findAll(optionalPageNumber,optionalnumberPerPage);
    }

    public Flux<Person> findAll(Option<Integer> optionalPageNumber, Option<Integer> optionalnumberPerPage){

        Integer pageNumber = optionalPageNumber.getOrElse(1)-1;
        Integer numberPerPage = optionalnumberPerPage.getOrElse(10);
        return personRepository.findAll(numberPerPage,pageNumber*numberPerPage).map(personRecord -> new Person(personRecord.getName(),personRecord.getLastName()));
    }

    public Mono<Person> post(Person person){
        return personRepository.save(new PersonRecord(null,person.getName(),person.getLastName())).map(personRecord -> new Person(personRecord.getName(),personRecord.getLastName()));
    }

    public Flux<Person> findByName(String partialName,Option<Integer> optionalPageNumber, Option<Integer> optionalnumberPerPage) {
        //return personRepository.finByName(partialName, PageRequest.of(optionalPageNumber.getOrElse(0),optionalnumberPerPage.getOrElse(10))).toList();
        Integer pageNumber = optionalPageNumber.getOrElse(0);
        Integer numberPerPage = optionalnumberPerPage.getOrElse(10);
        return personRepository.findByNameIgnoreCaseContains(partialName,PageRequest.of(pageNumber, numberPerPage)).map(personRecord -> new Person(personRecord.getName(),personRecord.getLastName()));
    }


    public Flux<Person> findByLastNameIgnoreCaseAndNameContains(String lastName, String name) {
        return personRepository.findByLastNameIgnoreCaseAndNameContains(lastName,name).map(personRecord -> new Person(personRecord.getName(),personRecord.getLastName()));
    }

    public Flux<Person> findByNameIgnoreCase(String name){
        System.out.println(name);
        return personRepository.findByNameIgnoreCase(name).map(personRecord ->{
            System.out.println(personRecord);
            return new Person(personRecord.getName(),personRecord.getLastName());
        });
    }
}
