package com.euce.dessert.model.address;

import com.euce.dessert.model.Manufacturer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name="addresses")
public class Address implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "apartment")
    private String apartment;

    @Column(name = "street")
    private String street;

    @Column(name = "avenue")
    private String avenue;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "addressList")
    @JsonIgnore
    private List<Manufacturer> manufacturerList;
}
