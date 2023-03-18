package com.euce.dessert.model.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name="groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "groups")
    @JsonIgnore
    private Set<User> users = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "groups_privileges",
            joinColumns = {
                    @JoinColumn(name = "group_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "privilege_id")
            }
    )
    private Set<Privilege> privileges = new HashSet<>();
}
