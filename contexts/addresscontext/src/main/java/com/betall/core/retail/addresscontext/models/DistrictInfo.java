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
public class DistrictInfo {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("code")
    private String code;
    @JsonProperty("name")
    private String name;
    @JsonProperty("wards")
    private List<WardInfo> wards;
    @JsonProperty("isActive")
    private Boolean isActive;

    public void validate() {
        if (id == null || id <= 0) {
            throw new DataViolationException("DistrictInfo id must be greater than 0.");
        }

        if (code == null || code.trim().length() == 0) {
            throw new DataViolationException("DistrictInfo code is null or empty.");
        }

        if (name == null || name.trim().length() == 0) {
            throw new DataViolationException("DistrictInfo name is null or empty.");
        }

        if (wards == null || wards.isEmpty()) {
            throw new DataViolationException("DistrictInfo wards must not be null or empty");
        }

        validateWards();
    }

    private void validateWards() {
        for(WardInfo wardInfo : wards) {
            wardInfo.validate();
        }
    }

    public String toString() {
        return new Gson().toJson(this);
    }
}
