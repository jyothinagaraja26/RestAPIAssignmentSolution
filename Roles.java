package com.greatlearning.employee.entities;

import lombok.Data;

import javax.persistence.*;

@Data

@Entity
@Table(name="roles")
public class Roles {

    @Id
    @Column(name="role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;

    private String name;
}
