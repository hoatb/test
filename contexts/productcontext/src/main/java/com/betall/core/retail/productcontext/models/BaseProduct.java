package com.betall.core.retail.productcontext.models;

import com.betall.core.retail.shared_kernels.exceptions.DataViolationException;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseProduct {
    @JsonProperty("productId")
    private Long productId;
    @JsonProperty("productName")
    private String productName;
    @JsonProperty("star")
    private Double star;
    @JsonProperty("numberReview")
    private Integer numberReview;
    @JsonProperty("priceDiscount")
    private Double priceDiscount;
    @JsonProperty("price")
    private Double price;
    @JsonProperty("percent")
    private Double percent;
    @JsonProperty("content")
    private String content;
    @JsonProperty("brand")
    private String brand;

    public void validate() {
        if (productId == null) {
            throw new DataViolationException("Product's productId must not be null.");
        }
        if (productName == null || productName.isEmpty()) {
            throw new DataViolationException("Product's productName must not be null or empty.");
        }
    }
}
