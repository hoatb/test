package com.betall.core.retail.addressinfrastructure.models;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.Set;
import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "retail", name = "haravan_province")
@NamedQuery(name = "Province.findAll", query = "SELECT p FROM Province p")
public class Province {
    @Id
    private Integer id;

    @Column(name = "country_id")
    private Integer countryId;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "tax")
    private Double tax;

    @Column(name = "tax_name")
    private String taxName;

    @Column(name = "tax_type")
    private String taxType;

    @Column(name = "tax_percentage")
    private String taxPercentage;

    @Column(name = "is_active")
    @ColumnDefault(value = "true")
    private Boolean isActive;

    @JsonManagedReference
    @OneToMany(mappedBy="province", cascade = CascadeType.ALL)
    private Set<District> districts;
}
