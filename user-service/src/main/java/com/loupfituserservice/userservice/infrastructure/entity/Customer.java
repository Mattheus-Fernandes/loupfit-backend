package com.loupfituserservice.userservice.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "customers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Column(name = "gender", nullable = false)
    private Character gender;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "cpf", nullable = false)
    private String cpf;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "neighbour", nullable = false)
    private String neighbour;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "number", nullable = false)
    private Integer number;

    @Column(name = "uf", nullable = false)
    private String uf;

}
