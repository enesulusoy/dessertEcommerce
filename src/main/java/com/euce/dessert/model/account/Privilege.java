package com.euce.dessert.model.account;

import com.euce.dessert.model.constant.PrivilegeType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name="privileges")
public class Privilege implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false)
    private PrivilegeType privilegeType;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "privilegeList")
    @JsonIgnore
    private List<Role> roleList;
}
