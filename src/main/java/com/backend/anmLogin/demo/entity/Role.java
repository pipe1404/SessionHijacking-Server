package com.backend.anmLogin.demo.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "role",  uniqueConstraints = { @UniqueConstraint(name = "ROLE_UK", columnNames = "name") })
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", length = 30, nullable = false)
    private String name;
}
