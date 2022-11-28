package com.betall.core.retail.productcontext.models;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import com.google.gson.Gson;

import com.betall.core.retail.shared_kernels.exceptions.DataViolationException;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailInfo extends BaseProduct {
    @JsonProperty("lstProductColor")
    private List<ProductColorInfo> lstProductColor;
    @JsonProperty("lstProductAdvertisement")
    private List<ProductAdvertisementInfo> lstProductAdvertisement;
    @JsonProperty("lstProductSpecification")
    private List<ProductSpecificationInfo> lstProductSpecification;
    @JsonProperty("description")
    private String description;
    @JsonProperty("repaymentAmountByMonth")
    private Double repaymentAmountByMonth;
    @JsonProperty("isAllowInstallment")
    private Integer isAllowInstallment;
    @JsonProperty("isActive")
    private Boolean isActive;
    @JsonProperty("images")
    private List<ProductImageInfo> images;

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    private void validateLstProductColor() {
        if (lstProductColor == null || lstProductColor.isEmpty()) {
            throw new DataViolationException("ProductDetailInfo's lstProductColor must not be null or empty.");
        }

        for (ProductColorInfo productColor : lstProductColor) {
            productColor.validate();
        }
    }

    private void validateLstProductAdvertisement() {
        if (lstProductAdvertisement != null && !lstProductAdvertisement.isEmpty()) {
            for (ProductAdvertisementInfo productAdvertisement : lstProductAdvertisement) {
                productAdvertisement.validate();
            }
        }
    }

    private void validateLstProductSpecification() {
        if (lstProductSpecification != null && !lstProductSpecification.isEmpty()) {
            for (ProductSpecificationInfo productSpecification : lstProductSpecification) {
                productSpecification.validate();
            }
        }
    }

    private void validateImages() {
        if (images != null && !images.isEmpty()) {
            for (ProductImageInfo image : images) {
                image.validate();
            }
        }
    }

    @Override
    public void validate() {
        super.validate();
        validateLstProductColor();
        validateLstProductAdvertisement();
        validateLstProductSpecification();
        validateImages();
    }
}
