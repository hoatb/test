package com.betall.core.retail.haravanordercontext.models;

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
    private Long id;

    @JsonProperty("address1")
    private String address1;

    @JsonProperty("address2")
    private String address2;

    @JsonProperty("city")
    private String city;

    @JsonProperty("company")
    private String company;

    @JsonProperty("country")
    private String country;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("province")
    private String province;

    @JsonProperty("zip")
    private String zip;

    @JsonProperty("name")
    private String name;

    @JsonProperty("province_code")
    private String provinceCode;

    @JsonProperty("country_code")
    private String countryCode;

    @JsonProperty("default")
    private Boolean defaultAddress;

    @JsonProperty("district")
    private String district;

    @JsonProperty("district_code")
    private String districtCode;

    @JsonProperty("ward")
    private String ward;

    @JsonProperty("ward_code")
    private String wardCode;

    public void validate() {
        if (id == null || id <= 0) {
            throw new DataViolationException("AddressInfo id must be greater than 0.");
        }

        if (name == null || name.trim().length() == 0) {
            throw new DataViolationException("AddressInfo name is null or empty.");
        }

        if (address1 == null || address1.trim().length() == 0) {
            throw new DataViolationException("AddressInfo address1 is null or empty.");
        }

        if (countryCode == null || countryCode.trim().length() == 0) {
            throw new DataViolationException("AddressInfo countryCode is null or empty.");
        }

        if (provinceCode == null || provinceCode.trim().length() == 0) {
            throw new DataViolationException("AddressInfo provinceCode is null or empty.");
        }

        if (districtCode == null || districtCode.trim().length() == 0) {
            throw new DataViolationException("AddressInfo districtCode is null or empty.");
        }

        if (wardCode == null || wardCode.trim().length() == 0) {
            throw new DataViolationException("AddressInfo wardCode is null or empty.");
        }
    }

    public String toString() {
        return new Gson().toJson(this);
    }
}
