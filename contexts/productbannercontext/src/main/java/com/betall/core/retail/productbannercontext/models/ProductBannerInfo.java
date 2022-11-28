package com.betall.core.retail.productbannercontext.models;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.google.gson.Gson;

import com.betall.core.retail.shared_kernels.exceptions.DataViolationException;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductBannerInfo {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("imageUrl")
    private String imageUrl;
    @JsonProperty("url")
    private String url;
    @JsonProperty("priority")
    private Integer priority;
    @JsonProperty("isActive")
    private Integer isActive;

    @Override
    public String toString() {
        final Gson gson = new Gson();
        return gson.toJson(this);
    }

    public void validate() {
        if (imageUrl == null || imageUrl.trim().length() == 0) {
            throw new DataViolationException("ProductBannerInfo imageUrl must not be null or empty.");
        }

        if (url == null || url.trim().length() == 0) {
            throw new DataViolationException("ProductBannerInfo url must not be null or empty.");
        }
    }
}
