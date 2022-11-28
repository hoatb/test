package com.betall.core.retail.addressinfrastructure.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "retail", name = "address")
@NamedQuery(name = "Address.findAll", query = "SELECT w FROM Address w")
public class Address {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "city_id")
    private Integer cityId;

    @Column(name = "city_code")
    private String cityCode;

    @Column(name = "city_name")
    private String cityName;

    @Column(name = "district_id")
    private Integer districtId;

    @Column(name = "district_code")
    private String districtCode;

    @Column(name = "district_name")
    private String districtName;

    @Column(name = "ward_id")
    private Integer wardId;

    @Column(name = "ward_code")
    private String wardCode;

    @Column(name = "ward_name")
    private String wardName;

    @Column(name = "street")
    private String street;

    @Column(name = "haravan_city_code")
    private String haravanCityCode;

    @Column(name = "haravan_district_code")
    private String haravanDistrictCode;

    @Column(name = "haravan_ward_code")
    private String haravanWardCode;
}
