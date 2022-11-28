package com.betall.core.retail.productinfrastructure.models;

import lombok.*;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.Set;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "retail", name = "product")
@NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p")
public class Product {
    @Id
    private Long productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "star")
    private Double star;

    @Column(name = "number_review")
    private Integer numberReview;

    @Column(name = "price_discount")
    private Double priceDiscount;

    @Column(name = "price")
    private Double price;

    @Column(name = "percent")
    private Double percent;

    @Column(name = "content")
    private String content;

    @Column(name = "brand")
    private String brand;

    @JsonManagedReference
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<ProductColor> productColors;

    @JsonManagedReference
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<ProductAdvertisement> productAdvertisements;

    @JsonManagedReference
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<ProductSpecification> productSpecifications;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "repayment_amount_by_month")
    private Double repaymentAmountByMonth;

    @Column(name = "is_allow_installment")
    private Integer isAllowInstallment;

    @Column(name = "is_active")
    private Boolean isActive;

    @JsonManagedReference
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<ProductImage> images;
}
