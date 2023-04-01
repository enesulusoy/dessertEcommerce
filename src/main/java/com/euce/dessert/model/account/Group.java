package com.euce.dessert.model.account;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Data
@Entity
@Table(name="groups")
@SQLDelete(sql = "UPDATE groups SET deleted=true WHERE id=?")
@Where(clause = "deleted = false")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "deleted", nullable = false)
    private Boolean deleted;
}
