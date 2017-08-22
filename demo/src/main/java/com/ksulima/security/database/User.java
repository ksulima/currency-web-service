package com.ksulima.security.database;

import com.ksulima.security.model.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by Krzysztof Sulima on 08.08.2017.
 */
@Getter
@Setter
@Entity
@Table(name = "user")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;


    @Column(name = "password_hash", nullable = false)
    private String passwordHash;


    @Enumerated(EnumType.STRING)
    @Column(name="role", nullable = false)
    private Role role;

}
