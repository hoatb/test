package com.betall.core.retail.haravanordercontext.models;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.google.gson.Gson;

import com.betall.core.retail.shared_kernels.exceptions.DataViolationException;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LineItemInfo {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("fulfillable_quantity")
    private Integer fulfillableQuantity;

    @JsonProperty("fulfillment_service")
    private String fulfillmentService;

    @JsonProperty("fulfillment_status")
    private String fulfillmentStatus;

    @JsonProperty("grams")
    private Double grams;

    @JsonProperty("price")
    private Double price;

    @JsonProperty("price_original")
    private Double priceOriginal;

    @JsonProperty("price_promotion")
    private Double pricePromotion;

    @JsonProperty("product_id")
    private Long productId;

    @JsonProperty("quantity")
    private Integer quantity;

    @JsonProperty("requires_shipping")
    private Boolean requiresShipping;

    @JsonProperty("sku")
    private String sku;

    @JsonProperty("title")
    private String title;

    @JsonProperty("variant_id")
    private Long variantId;

    @JsonProperty("variant_title")
    private String variantTitle;

    @JsonProperty("variant_inventory_management")
    private String variantInventoryManagement;

    @JsonProperty("vendor")
    private String vendor;

    @JsonProperty("type")
    private String type;

    @JsonProperty("name")
    private String name;

    @JsonProperty("gift_card")
    private Boolean giftCard;

    @JsonProperty("taxable")
    private Boolean taxable;

    @JsonProperty("product_exists")
    private Boolean productExists;

    @JsonProperty("total_discount")
    private Double totalDiscount;

    @JsonProperty("image")
    private ImageInfo image;

    @JsonProperty("not_allow_promotion")
    private Boolean notAllowPromotion;

    @JsonProperty("ma_cost_amount")
    private Double maCostAmount;

    @JsonProperty("actual_price")
    private Double actualPrice;

    public void validate() {
        if (id == null || id <= 0) {
            throw new DataViolationException("LineItemInfo id must be greater than 0.");
        }

        if (productId == null || productId <= 0) {
            throw new DataViolationException("LineItemInfo productId must be greater than 0.");
        }

        if (quantity == null || quantity <= 0) {
            throw new DataViolationException("LineItemInfo quantity must be greater than 0.");
        }

        if (variantId == null || variantId <= 0) {
            throw new DataViolationException("LineItemInfo variantId must be greater than 0.");
        }

        if (price == null || price < 0) {
            throw new DataViolationException("LineItemInfo price must be greater than or equals 0.");
        }
    }

    public String toString() {
        return new Gson().toJson(this);
    }
}
