package com.betall.core.retail.haravan_product_context.models;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.google.gson.Gson;

import com.betall.core.retail.shared_kernels.exceptions.DataValidationException;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HaravanProductVariantInfo {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("barcode")
    private String barCode;
    @JsonProperty("compare_at_price")
    private Double compareAtPrice;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("fulfillment_service")
    private String fulfillmentService;
    @JsonProperty("grams")
    private Double grams;
    @JsonProperty("inventory_management")
    private String inventoryManagement;
    @JsonProperty("inventory_policy")
    private String inventoryPolicy;
    @JsonProperty("inventory_quantity")
    private Integer inventoryQuantity;
    @JsonProperty("position")
    private Integer position;
    @JsonProperty("price")
    private Double price;
    @JsonProperty("product_id")
    private Long productId;
    @JsonProperty("requires_shipping")
    private boolean requiresShipping;
    @JsonProperty("sku")
    private String sku;
    @JsonProperty("taxable")
    private boolean taxable;
    @JsonProperty("title")
    private String title;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("image_id")
    private Long imageId;
    @JsonProperty("option1")
    private String option1;
    @JsonProperty("option2")
    private String option2;
    @JsonProperty("option3")
    private String option3;
    @JsonProperty("inventory_advance")
    private HaravanInventoryAdvanceInfo inventoryAdvance;

    public void validate() {
        if (id == null) {
            throw new DataValidationException("HaravanProductVariantInfo id must not be null.");
        }

        if (price == null || price < 0) {
            throw new DataValidationException("HaravanProductVariantInfo price must be greater or equals 0.");
        }

        if (position == null || position <= 0) {
            throw new DataValidationException("HaravanProductVariantInfo position must be greater than 0.");
        }

        if (compareAtPrice == null || compareAtPrice < 0) {
            throw new DataValidationException("HaravanProductVariantInfo compareAtPrice must be greater than 0.");
        }
    }

    public String toString() {
        return new Gson().toJson(this);
    }
}
