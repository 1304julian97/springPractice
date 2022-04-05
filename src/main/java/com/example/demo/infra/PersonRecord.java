package com.example.demo.infra;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

//import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table("person")
@ToString
public class PersonRecord {

    @Id
    @Column("id_person")
    private Integer id;

    @Column("name_person")
    private String name;

    @Column("lastName_person")
    private String lastName;
}
