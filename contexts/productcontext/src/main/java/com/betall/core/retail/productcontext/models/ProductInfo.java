package com.betall.core.retail.productcontext.models;

import com.google.gson.Gson;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ProductInfo extends BaseProduct {
    @JsonProperty("imageUrl")
    private String imageUrl;
    @JsonProperty("colorId")
    private Long colorId;
    @JsonProperty("colorName")
    private String colorName;
    @JsonProperty("portalId")
    private Long portalId;
    @JsonProperty("number")
    private Integer number;
    @JsonProperty("haravanProductId")
    private Long haravanProductId;
    @JsonProperty("productColorActive")
    private Integer productColorActive;

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
