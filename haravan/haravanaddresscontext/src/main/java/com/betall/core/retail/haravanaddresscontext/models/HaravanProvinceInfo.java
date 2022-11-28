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
public class HaravanProvinceInfo {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("country_id")
    private Integer countryId;
    @JsonProperty("code")
    private String code;
    @JsonProperty("name")
    private String name;
    @JsonProperty("tax")
    private Double tax;
    @JsonProperty("tax_name")
    private String taxName;
    @JsonProperty("tax_type")
    private String taxType;
    @JsonProperty("tax_percentage")
    private String taxPercentage;

    public void validate() {
        if (id == null || id < 0) {
            throw new DataValidationException("HaravanProvinceInfo id must be greater than 0.");
        }

        if (countryId == null || countryId != 241) {
            throw new DataValidationException("HaravanProvinceInfo countryId must be 241 (VN country id in Haravan).");
        }

        if (code == null || code.trim().length() == 0) {
            throw new DataValidationException("HaravanProvinceInfo code is null or empty.");
        }

        if (name == null || name.trim().length() == 0) {
            throw new DataValidationException("HaravanProvinceInfo name is null or empty.");
        }
    }

    public String toString() {
        return new Gson().toJson(this);
    }
}
