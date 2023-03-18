package com.euce.dessert.model;

import com.euce.dessert.model.address.Address;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Builder
@Table(name="manufacturers")
@NoArgsConstructor
@AllArgsConstructor
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

    @ManyToMany(fetch = FetchType.EAGER)
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
