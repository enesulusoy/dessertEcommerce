package com.euce.dessert.model.account;

import com.euce.dessert.model.constant.RoleType;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name="roles")
@SQLDelete(sql = "UPDATE roles SET deleted=true WHERE id=?")
@Where(clause = "deleted = false")
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false)
    private RoleType name;

    @Column(name = "description")
    private String description;

    @Column(name = "deleted", nullable = false)
    private Boolean deleted;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "roles_privileges",
            joinColumns = {
                    @JoinColumn(name = "role_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "privilege_id")
            }
    )
    private Set<Privilege> privileges = new HashSet<>();
}
