package com.example.demo.infra.repository;

import com.example.demo.infra.PersonRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.data.repository.PagingAndSortingRepository;
//import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.List;

@Repository
public interface PersonRepository extends ReactiveCrudRepository<PersonRecord,Integer> {

/*
    @Query(value = "select * from person where name_person like %:partialName%")//, nativeQuery = true)
    Page<PersonRecord> finByName(@Param("partialName") String partialName, Pageable pageable);
*/

    @Query(value = "select * from person where name_person like %$1%")//, nativeQuery = true)
    Flux<PersonRecord> finByName(String partialName);
}
