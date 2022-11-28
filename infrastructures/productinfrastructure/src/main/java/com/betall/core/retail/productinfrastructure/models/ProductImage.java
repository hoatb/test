package com.betall.core.retail.productinfrastructure.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "retail", name = "product_image")
@NamedQuery(name = "ProductImage.findAll", query = "SELECT p FROM ProductImage p")
public class ProductImage {
    @Id
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "src")
    private String src;

    @Column(name = "position")
    private Integer position;

    @Column(name = "file_name")
    private String fileName;

    @ElementCollection
    @Column(name = "variant_ids")
    private Set<Long> variantIds;
}
