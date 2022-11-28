package com.betall.core.retail.productinfrastructure.models;

import lombok.*;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.Set;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "retail", name = "product_color")
@NamedQuery(name = "ProductColor.findAll", query = "SELECT pc FROM ProductColor pc")
@NamedQuery(
    name = "ProductColor.findByProductIdAndColorId",
    query = "SELECT c FROM ProductColor AS c WHERE c.product.productId = :productId AND c.id = :colorId")
public class ProductColor {
    @Id
    private Long id;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "color_name")
    private String colorName;

    @Column(name = "color_code")
    private String colorCode;

    @Column(name = "percent")
    private Double percent;

    @Column(name = "price_discount")
    private Double priceDiscount;

    @Column(name = "price")
    private Double price;

    @ElementCollection
    @Column(name = "url_product_image")
    private Set<String> urlProductImage;
}
