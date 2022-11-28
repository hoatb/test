package com.betall.core.retail.haravanordercontext.models;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.google.gson.Gson;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageInfo {
    @JsonProperty("src")
    private String src;

    public String toString() {
        return new Gson().toJson(this);
    }
}
