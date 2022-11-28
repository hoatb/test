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
public class HaravanProductOptionInfo {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("position")
    private Integer position;
    @JsonProperty("product_id")
    private Long productId;

    public void validate() {
        if (id == null) {
            throw new DataValidationException("HaravanProductOptionInfo id must not be null.");
        }

        if (position == null || position <= 0) {
            throw new DataValidationException("HaravanProductOptionInfo position must be greater than 0.");
        }
    }

    public String toString(){
        return new Gson().toJson(this);
    }
}
