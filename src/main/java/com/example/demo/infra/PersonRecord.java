package com.example.demo.infra;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
//@Entity
@Table(name = "person")
public class PersonRecord {

    @Id
    @Column(name = "id_person")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name_person")
    private String name;

    @Column(name = "lastName_person")
    private String lastName;
}
