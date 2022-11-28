package com.betall.core.retail.productbannerinfrastructure.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "retail", name = "product_banner")
@NamedQuery(name = "ProductBanner.findAll", query = "SELECT pb FROM ProductBanner pb")
public class ProductBanner {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "url")
    private String url;

    @Column(name = "priority")
    private Integer priority;

    @Column(name = "is_active")
    private Integer isActive;
}
