package com.betall.core.retail.productinfrastructure.models;

import lombok.*;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "retail", name = "product_advertisement")
@NamedQuery(name = "ProductAdvertisement.findAll", query = "SELECT pa FROM ProductAdvertisement pa")
public class ProductAdvertisement {
    @Id
    private Long id;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;
}
