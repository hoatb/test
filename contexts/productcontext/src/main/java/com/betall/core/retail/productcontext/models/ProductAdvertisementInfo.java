package com.betall.core.retail.productcontext.models;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.google.gson.Gson;

import com.betall.core.retail.shared_kernels.exceptions.DataViolationException;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductAdvertisementInfo {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("productId")
    private Long productId;
    @JsonProperty("title")
    private String title;
    @JsonProperty("content")
    private String content;

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public void validate() {
        if (id == null) {
            throw new DataViolationException("ProductAdvertisementInfo's id must not be null");
        }

        if (productId == null) {
            throw new DataViolationException("ProductAdvertisementInfo's productId must not be null");
        }

        if (title == null || title.trim().length() == 0) {
            throw new DataViolationException("ProductAdvertisementInfo's title must not be null or empty");
        }

        if (content == null || content.trim().length() == 0) {
            throw new DataViolationException("ProductAdvertisementInfo's content must not be null or empty");
        }
    }
}
