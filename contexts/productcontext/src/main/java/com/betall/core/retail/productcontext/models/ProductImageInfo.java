package com.betall.core.retail.productcontext.models;

import com.betall.core.retail.shared_kernels.exceptions.DataViolationException;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductImageInfo {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("productId")
    private Long productId;
    @JsonProperty("src")
    private String src;
    @JsonProperty("position")
    private Integer position;
    @JsonProperty("fileName")
    private String fileName;
    @JsonProperty("variantIds")
    private List<Long> variantIds;

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public void validate() {
        if (id == null) {
            throw new DataViolationException("ProductImageInfo's id must not be null.");
        }

        if (productId == null) {
            throw new DataViolationException("ProductImageInfo's productId must not be null.");
        }

        if (src == null || src.trim().isEmpty()) {
            throw new DataViolationException("ProductImageInfo's src must not be null or empty.");
        }
    }
}
