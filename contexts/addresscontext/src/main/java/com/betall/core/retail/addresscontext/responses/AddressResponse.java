package com.betall.core.retail.addresscontext.responses;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.google.gson.Gson;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponse {
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
    @JsonProperty("cityCode")
    private String cityCode;
    @JsonProperty("cityName")
    private String cityName;

    @JsonProperty("districtId")
    private Integer districtId;
    @JsonProperty("districtCode")
    private String districtCode;
    @JsonProperty("districtName")
    private String districtName;

    @JsonProperty("wardId")
    private Integer wardId;
    @JsonProperty("wardCode")
    private String wardCode;
    @JsonProperty("wardName")
    private String wardName;

    @JsonProperty("street")
    private String street;
    @JsonProperty("haravanCityCode")
    private String haravanCityCode;
    @JsonProperty("haravanDistrictCode")
    private String haravanDistrictCode;
    @JsonProperty("haravanWardCode")
    private String haravanWardCode;

    public String toString() {
        return new Gson().toJson(this);
    }
}
