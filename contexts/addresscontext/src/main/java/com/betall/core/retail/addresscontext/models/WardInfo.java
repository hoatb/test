package com.betall.core.retail.addresscontext.models;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.google.gson.Gson;

import com.betall.core.retail.shared_kernels.exceptions.DataViolationException;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WardInfo {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("code")
    private String code;
    @JsonProperty("name")
    private String name;
    @JsonProperty("isActive")
    private Boolean isActive;

    public void validate() {
        if (id == null || id <= 0) {
            throw new DataViolationException("WardInfo id must be greater than 0.");
        }

        if (code == null || code.trim().length() == 0) {
            throw new DataViolationException("WardInfo code is null or empty.");
        }

        if (name == null || name.trim().length() == 0) {
            throw new DataViolationException("WardInfo name is null or empty.");
        }
    }

    public String toString() {
        return new Gson().toJson(this);
    }
}
