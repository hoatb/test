package com.betall.core.retail.addresscontext.requests;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.google.gson.Gson;

import com.betall.core.retail.shared_kernels.exceptions.DataViolationException;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAddressInfo {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("phoneNumber")
    private String phoneNumber;
    @JsonProperty("currentAddress")
    private String currentAddress;
    @JsonProperty("currentCity")
    private Integer currentCity;
    @JsonProperty("currentDistrict")
    private Integer currentDistrict;
    @JsonProperty("currentWard")
    private Integer currentWard;

    public void validate() {
        if (id == null || id <= 0) {
            throw new DataViolationException("UpdateAddressInfo id must be greater than 0.");
        }

        if (name == null || name.trim().length() == 0) {
            throw new DataViolationException("UpdateAddressInfo name is null or empty.");
        }

        if (phoneNumber == null || phoneNumber.trim().length() == 0) {
            throw new DataViolationException("UpdateAddressInfo phoneNumber is null or empty.");
        }

        if (currentAddress == null || currentAddress.trim().length() == 0) {
            throw new DataViolationException("UpdateAddressInfo currentAddress is null or empty.");
        }

        if (currentCity == null || currentCity <= 0) {
            throw new DataViolationException("UpdateAddressInfo currentCity must be greater than 0.");
        }

        if (currentDistrict == null || currentDistrict <= 0) {
            throw new DataViolationException("UpdateAddressInfo currentDistrict must be greater than 0.");
        }

        if (currentWard == null || currentWard <= 0) {
            throw new DataViolationException("UpdateAddressInfo currentWard must be greater than 0.");
        }
    }

    public String toString() {
        return new Gson().toJson(this);
    }
}
