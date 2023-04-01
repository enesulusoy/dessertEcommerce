package com.euce.dessert.model;

import com.euce.dessert.model.address.Address;
import javax.persistence.*;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name="manufacturers")
@SQLDelete(sql = "UPDATE manufacturers SET deleted=true WHERE id=?")
@Where(clause = "deleted = false")
public class Manufacturer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "description")
    private String description;

    @Column(name = "deleted")
    private Boolean deleted;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "manufacturers_addresses",
            joinColumns = {
                    @JoinColumn(name = "manufacturer_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "address_id")
            }
    )
    private Set<Address> addresses = new HashSet<>();
}
