package com.example.demo.infra.repository;

import com.example.demo.infra.PersonRecord;
import io.vavr.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface PersonRepository extends R2dbcRepository<PersonRecord,Integer> {


    Flux<PersonRecord> findByNameIgnoreCaseContains(String partialName,Pageable pageable);

    Flux<PersonRecord> findByLastNameIgnoreCaseAndNameContains(String lastName, String name);


    @Query("select p.* from person p order by p.id_person limit :numberPerPage offset :page ")
    Flux<PersonRecord> findAll(Integer numberPerPage, Integer page );


    Flux<PersonRecord> findByNameIgnoreCase(String name);
}
