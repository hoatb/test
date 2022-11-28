package com.betall.core.retail.productgroupcontext.models;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.google.gson.Gson;

import com.betall.core.retail.shared_kernels.exceptions.DataViolationException;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductGroupInfo {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("priority")
    private Integer priority;
    @JsonProperty("isActive")
    private Integer isActive;
    @JsonProperty("iconUrl")
    private String iconUrl;

    @Override
    public String toString() {
        final Gson gson = new Gson();
        return gson.toJson(this);
    }

    public void validate() {
        if (name == null || name.trim().length() == 0) {
            throw new DataViolationException("ProductGroupInfo name must not be null or empty.");
        }
    }
}
