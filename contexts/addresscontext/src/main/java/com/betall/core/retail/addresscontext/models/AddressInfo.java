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
public class AddressInfo {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("phoneNumber")
    private String phoneNumber;
    @JsonProperty("address")
    private String address;
    @JsonProperty("cityId")
    private Integer cityId;
    @JsonProperty("districtId")
    private Integer districtId;
    @JsonProperty("wardId")
    private Integer wardId;
    @JsonProperty("street")
    private String street;
    @JsonProperty("haravanCityCode")
    private String haravanCityCode;
    @JsonProperty("haravanDistrictCode")
    private String haravanDistrictCode;
    @JsonProperty("haravanWardCode")
    private String haravanWardCode;

    public void validate() {
        if (name == null || name.trim().length() == 0) {
            throw new DataViolationException("AddressInfo name is null or empty.");
        }

        if (phoneNumber == null || phoneNumber.trim().length() == 0) {
            throw new DataViolationException("AddressInfo phoneNumber is null or empty.");
        }

        if (address == null || address.trim().length() == 0) {
            throw new DataViolationException("AddressInfo address is null or empty.");
        }

        if (cityId == null || cityId <= 0) {
            throw new DataViolationException("AddressInfo cityId must be greater than 0.");
        }

        if (districtId == null || districtId <= 0) {
            throw new DataViolationException("AddressInfo districtId must be greater than 0.");
        }

        if (wardId == null || wardId <= 0) {
            throw new DataViolationException("AddressInfo wardId must be greater than 0.");
        }

        if (street == null || street.trim().length() == 0) {
            throw new DataViolationException("AddressInfo street is null or empty.");
        }

        if (haravanCityCode == null || haravanCityCode.trim().length() == 0) {
            throw new DataViolationException("AddressInfo haravanCityCode is null or empty.");
        }

        if (haravanDistrictCode == null || haravanDistrictCode.trim().length() == 0) {
            throw new DataViolationException("AddressInfo haravanDistrictCode is null or empty.");
        }

        if (haravanWardCode == null || haravanWardCode.trim().length() == 0) {
            throw new DataViolationException("AddressInfo haravanWardCode is null or empty.");
        }
    }

    public String toString() {
        return new Gson().toJson(this);
    }
}
