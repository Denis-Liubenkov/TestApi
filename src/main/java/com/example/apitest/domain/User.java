package com.example.apitest.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@Entity(name = "users")
public class User {
    @Id
    @SequenceGenerator(name = "user_seq", sequenceName = "user_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    private Integer id;

    @Pattern(regexp = "^[А-Яа-яЁё]+$")
    @Column(name = "name", nullable = false)
    private String name;

    @Pattern(regexp = "^[А-Яа-яЁё]+$")
    @Column(name = "sur_name", nullable = false)
    private String surName;

    @Email
    @Column(name = "email", nullable = false)
    private String email;

    @ElementCollection
    private List<String> roles;

    @ElementCollection
    private List<String> phones;
}
