package com.betall.core.retail.haravanordercontext.models;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.google.gson.Gson;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiscountInfo {
    @JsonProperty("code")
    private String code;

    @JsonProperty("amount")
    private Double amount;

    @JsonProperty("type")
    private String type;

    public String toString() {
        return new Gson().toJson(this);
    }
}
