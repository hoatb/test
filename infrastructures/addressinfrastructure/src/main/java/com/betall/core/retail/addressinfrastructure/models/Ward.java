package com.betall.core.retail.addressinfrastructure.models;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "retail", name = "haravan_ward")
@NamedQuery(name = "Ward.findAll", query = "SELECT w FROM Ward w")
public class Ward {
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
    @JoinColumn(name="district_id", nullable = false)
    private District district;
}
