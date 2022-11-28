package com.betall.core.retail.productcontext.models;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import com.google.gson.Gson;

import com.betall.core.retail.shared_kernels.exceptions.DataViolationException;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductColorInfo {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("productId")
    private Long productId;
    @JsonProperty("colorName")
    private String colorName;
    @JsonProperty("colorCode")
    private String colorCode;
    @JsonProperty("percent")
    private Double percent;
    @JsonProperty("priceDiscount")
    private Double priceDiscount;
    @JsonProperty("price")
    private Double price;
    @JsonProperty("urlProductImage")
    private List<String> urlProductImage;

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public void validate() {
        if (id == null) {
            throw new DataViolationException("ProductColorInfo's id must not be null");
        }

        if (productId == null) {
            throw new DataViolationException("ProductColorInfo's productId must not be null");
        }

        if (colorName == null || colorName.trim().length() == 0) {
            throw new DataViolationException("ProductColorInfo's colorName must not be null or empty");
        }
    }
}
