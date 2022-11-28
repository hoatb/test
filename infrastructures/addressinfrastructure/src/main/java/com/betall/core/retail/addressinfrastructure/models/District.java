package com.betall.core.retail.addressinfrastructure.models;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.ColumnDefault;

import java.util.Set;
import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "retail", name = "haravan_district")
@NamedQuery(name = "District.findAll", query = "SELECT d FROM District d")
public class District {
    @Id
    private Integer id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "is_active")
    @ColumnDefault(value = "true")
    private Boolean isActive;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="province_id", nullable = false)
    private Province province;

    @JsonManagedReference
    @OneToMany(mappedBy="district", cascade = CascadeType.ALL)
    private Set<Ward> wards;
}
