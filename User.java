package com.greatlearning.employee.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name="user")
public class User {
    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

    @ManyToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinTable(name="user_roles",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="role_id"))

    private List<Roles>  listOfRoles=new ArrayList<Roles>();

}
