package com.euce.dessert.model.address;

import javax.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name="countries")
public class Country implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "iso3")
    private String iso3Code;

    @Column(name = "iso2")
    private String iso2Code;

    @Column(name = "numeric_code")
    private Integer numericCode;

    @Column(name = "phone_code")
    private String phoneCode;

    @Column(name = "capital")
    private String capital;

    @Column(name = "currency")
    private String currency;

    @Column(name = "currency_name")
    private String currencyName;

    @Column(name = "currency_symbol")
    private String currencySymbol;

    @Column(name = "tld")
    private String tld;

    @Column(name = "region")
    private String region;

    @Column(name = "subregion")
    private String subRegion;

    @Column(name = "latitude")
    private Integer latitude;

    @Column(name = "longitude")
    private Integer longitude;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "country")
    private List<Address> addressList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "country")
    private List<City> cityList;
}
