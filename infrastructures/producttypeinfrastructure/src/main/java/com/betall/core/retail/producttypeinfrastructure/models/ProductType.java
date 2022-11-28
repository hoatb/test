package com.betall.core.retail.producttypeinfrastructure.models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "retail", name = "product_type")
@NamedQuery(name = "ProductType.findAll", query = "SELECT pt FROM ProductType pt")
public class ProductType {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "priority")
    private Integer priority;

    @Column(name = "is_active")
    private Integer isActive;
}
