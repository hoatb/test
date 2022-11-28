package com.betall.core.retail.haravanordercontext.models;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.google.gson.Gson;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShippingLineInfo {
    @JsonProperty("code")
    private String code;

    @JsonProperty("price")
    private Double price;

    @JsonProperty("source")
    private String source;

    @JsonProperty("title")
    private String title;

    public String toString() {
        return new Gson().toJson(this);
    }
}
