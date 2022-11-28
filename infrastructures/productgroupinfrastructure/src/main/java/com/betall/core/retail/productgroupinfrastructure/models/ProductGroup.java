package com.betall.core.retail.productgroupinfrastructure.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "retail", name = "product_group")
@NamedQuery(name = "ProductGroup.findAll", query = "SELECT pg FROM ProductGroup pg")
public class ProductGroup {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "priority")
    private Integer priority;

    @Column(name = "is_active")
    private Integer isActive;

    @Column(name = "icon_url")
    private String iconUrl;
}
