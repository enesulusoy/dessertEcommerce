package com.euce.dessert.model.account;

import com.euce.dessert.model.constant.PrivilegeType;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name="privileges")
@SQLDelete(sql = "UPDATE privileges SET deleted=true WHERE id=?")
@Where(clause = "deleted = false")
public class Privilege implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false)
    private PrivilegeType name;

    @Column(name = "description")
    private String description;

    @Column(name = "deleted", nullable = false)
    private Boolean deleted;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "privileges_groups",
            joinColumns = {
                    @JoinColumn(name = "privilege_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "group_id")
            }
    )
    private Set<Group> groups = new HashSet<>();
}
