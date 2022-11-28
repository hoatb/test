package com.betall.core.retail.addresscontext.models;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import com.google.gson.Gson;

import com.betall.core.retail.shared_kernels.exceptions.DataViolationException;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProvinceInfo {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("countryId")
    private Integer countryId;
    @JsonProperty("code")
    private String code;
    @JsonProperty("name")
    private String name;
    @JsonProperty("tax")
    private Double tax;
    @JsonProperty("taxName")
    private String taxName;
    @JsonProperty("taxType")
    private String taxType;
    @JsonProperty("taxPercentage")
    private String taxPercentage;
    @JsonProperty("districts")
    private List<DistrictInfo> districts;
    @JsonProperty("isActive")
    private Boolean isActive;

    public void validate() {
        if (id == null || id <= 0) {
            throw new DataViolationException("ProvinceInfo id must be greater than 0.");
        }

        if (countryId == null || countryId != 241) {
            throw new DataViolationException("ProvinceInfo countryId must be 241 (VN country id in Haravan).");
        }

        if (code == null || code.trim().length() == 0) {
            throw new DataViolationException("ProvinceInfo code is null or empty.");
        }

        if (name == null || name.trim().length() == 0) {
            throw new DataViolationException("ProvinceInfo name is null or empty.");
        }

        if (districts == null || districts.isEmpty()) {
            throw new DataViolationException("ProvinceInfo districts must not be null or empty.");
        }

        validateDistrict();
    }

    private void validateDistrict() {
        for(DistrictInfo districtInfo : districts) {
            districtInfo.validate();
        }
    }

    public String toString() {
        return new Gson().toJson(this);
    }
}
