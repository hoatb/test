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
public class HaravanDistrictInfo {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("code")
    private String code;
    @JsonProperty("province_id")
    private Integer provinceId;

    public void validate() {
        if (id == null || id < 0) {
            throw new DataValidationException("HaravanDistrictInfo id must be greater than 0.");
        }

        if (code == null || code.trim().length() == 0) {
            throw new DataValidationException("HaravanDistrictInfo code is null or empty.");
        }

        if (name == null || name.trim().length() == 0) {
            throw new DataValidationException("HaravanDistrictInfo name is null or empty.");
        }

        if (provinceId == null || provinceId < 0) {
            throw new DataValidationException("HaravanDistrictInfo provinceId must be greater than 0.");
        }
    }

    public String toString() {
        return new Gson().toJson(this);
    }
}
