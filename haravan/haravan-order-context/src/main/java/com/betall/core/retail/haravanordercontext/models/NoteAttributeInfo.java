package com.betall.core.retail.haravanordercontext.models;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.google.gson.Gson;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoteAttributeInfo {
    @JsonProperty("name")
    private String name;

    @JsonProperty("value")
    private String value;

    public String toString() {
        return new Gson().toJson(this);
    }
}
