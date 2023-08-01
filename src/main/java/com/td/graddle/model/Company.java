package com.td.graddle.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "company")
@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column
    private String slogan;

    @Column
    private String email;

    @Column
    private String address;

    @Column
    private String nif;

    @Column
    private String stat;

    @Column
    private String rcs;

    @Column
    @ElementCollection
    private List<String> phones;

    @Column
    private String logo;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Employee> employees;

}