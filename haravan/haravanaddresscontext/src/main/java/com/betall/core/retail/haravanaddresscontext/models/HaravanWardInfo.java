package com.betall.core.retail.haravanaddresscontext.models;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.google.gson.Gson;

import com.betall.core.retail.shared_kernels.exceptions.DataValidationException;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HaravanWardInfo {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("code")
    private String code;
    @JsonProperty("district_id")
    private Integer districtId;

    public void validate() {
        if (id == null || id < 0) {
            throw new DataValidationException("HaravanWardInfo id must be greater than 0.");
        }

        if (code == null || code.trim().length() == 0) {
            throw new DataValidationException("HaravanWardInfo code is null or empty.");
        }

        if (name == null || name.trim().length() == 0) {
            throw new DataValidationException("HaravanWardInfo name is null or empty.");
        }

        if (districtId == null || districtId < 0) {
            throw new DataValidationException("HaravanWardInfo districtId must be greater than 0.");
        }
    }

    public String toString() {
        return new Gson().toJson(this);
    }
}
