package com.betall.core.retail.haravan_product_context.models;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import com.google.gson.Gson;

import com.betall.core.retail.shared_kernels.exceptions.DataValidationException;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HaravanProductImageInfo {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("position")
    private Integer position;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("src")
    private String src;
    @JsonProperty("product_id")
    private Long productId;
    @JsonProperty("filename")
    private String fileName;
    @JsonProperty("variant_ids")
    private List<Long> variantIds;

    public void validate() {
        if (id == null) {
            throw new DataValidationException("HaravanProductImageInfo id must not be null.");
        }

        if (src == null || src.trim().length() == 0) {
            throw new DataValidationException("HaravanProductImageInfo src is null or empty.");
        }

        if (productId == null || productId <= 0) {
            throw new DataValidationException("HaravanProductImageInfo productId must be greater than 0.");
        }

        if (position == null || position <= 0) {
            throw new DataValidationException("HaravanProductImageInfo position must be greater than 0.");
        }
    }

    public String toString(){
        return new Gson().toJson(this);
    }
}



