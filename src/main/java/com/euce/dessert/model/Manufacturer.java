package com.euce.dessert.model;

import com.euce.dessert.model.address.Address;
import javax.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name="manufacturers")
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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "manufacturers_addresses",
            joinColumns = {
                    @JoinColumn(name = "manufacturer_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "address_id")
            }
    )
    private List<Address> addressList;
}
