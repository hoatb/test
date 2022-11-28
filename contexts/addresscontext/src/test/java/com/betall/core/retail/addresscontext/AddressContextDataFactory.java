package com.betall.core.retail.addresscontext;

import java.util.List;
import java.util.ArrayList;

import com.betall.core.retail.addresscontext.models.WardInfo;
import com.betall.core.retail.addresscontext.models.AddressInfo;
import com.betall.core.retail.addresscontext.models.DistrictInfo;
import com.betall.core.retail.addresscontext.models.ProvinceInfo;

import com.betall.core.retail.addresscontext.representations.AddressRepresentation;
import com.betall.core.retail.addresscontext.representations.ProvinceRepresentation;
import com.betall.core.retail.addresscontext.requests.UpdateAddressInfo;
import com.betall.core.retail.addresscontext.responses.AddressResponse;

public class AddressContextDataFactory {
    public static ProvinceInfo initProvinceInfo() {
        return ProvinceInfo.builder()
            .id(2)
            .countryId(241)
            .code("HG")
            .name("Hà Giang")
            .tax(null)
            .taxName(null)
            .taxType(null)
            .taxPercentage(null)
            .isActive(true)
            .districts(initDistrictInfos(4))
            .build();
    }

    private static ProvinceInfo initProvinceInfo(int id) {
        return ProvinceInfo.builder()
                .id(id)
                .countryId(241)
                .code(String.format("Code_%s", id))
                .name(String.format("Province_%s", id))
                .tax(null)
                .taxName(null)
                .taxType(null)
                .taxPercentage(null)
                .isActive(true)
                .districts(initDistrictInfos(4))
                .build();
    }

    public static List<ProvinceInfo> initProvinceInfos(int size) {
        final List<ProvinceInfo> provinceInfos = new ArrayList<>();
        for(int i=0; i<size; i++) {
            final ProvinceInfo provinceInfo = initProvinceInfo(i+1);
            provinceInfos.add(provinceInfo);
        }
        return provinceInfos;
    }

    public static List<DistrictInfo> initDistrictInfos(int size) {
        final List<DistrictInfo> districtInfos = new ArrayList<>();
        for (int i=0; i<size; i++) {
            final DistrictInfo districtInfo = DistrictInfo.builder()
                .id(i + 1)
                .code("HG" + i)
                .name("Quan/Huyen " + i)
                .isActive(true)
                .wards(initWardInfos(3))
                .build();
            districtInfos.add(districtInfo);
        }
        return districtInfos;
    }

    public static List<WardInfo> initWardInfos(int size) {
        final List<WardInfo> wardInfos = new ArrayList<>();
        for(int i=0; i<size; i++) {
            final WardInfo wardInfo = WardInfo.builder()
                .id(i + 1)
                .code("1000" + i)
                .name("Phuong/Xa " + i)
                .isActive(true)
                .build();
            wardInfos.add(wardInfo);
        }
        return wardInfos;
    }

    public static ProvinceRepresentation initProvinceRepresentation() {
        return ProvinceRepresentation.builder()
            .status(0)
            .message(null)
            .data(initProvinceInfo())
            .build();
    }

    public static AddressInfo initAddressInfo() {
        return AddressInfo.builder()
            .id(1)
            .name("Address")
            .phoneNumber("0909000999")
            .address("Full Address")
            .cityId(1)
            .districtId(11)
            .wardId(111)
            .street("Street")
            .haravanCityCode("HaravanCityCode")
            .haravanDistrictCode("HaravanDistrictCode")
            .haravanWardCode("HaravanWardCode")
            .build();
    }

    public static AddressResponse initAddressResponse() {
        return AddressResponse.builder()
            .id(1)
            .name("Address")
            .phoneNumber("0909000999")
            .address("Full Address")
            .cityId(1)
            .cityCode("CityCode")
            .cityName("CityName")
            .districtId(11)
            .districtCode("DistrictCode")
            .districtName("DistrictName")
            .wardId(111)
            .wardCode("WardCode")
            .wardName("WardName")
            .street("Street")
            .haravanCityCode("HaravanCityCode")
            .haravanDistrictCode("HaravanDistrictCode")
            .haravanWardCode("HaravanWardCode")
            .build();
    }

    public static UpdateAddressInfo initUpdateAddressInfo() {
        return UpdateAddressInfo.builder()
            .id(1)
            .name("Address")
            .phoneNumber("0909000999")
            .currentCity(1)
            .currentDistrict(11)
            .currentWard(111)
            .currentAddress("Street")
            .build();
    }

    public static AddressRepresentation initAddressRepresentation() {
        return AddressRepresentation.builder()
            .status(0)
            .message(null)
            .data(initAddressResponse())
            .build();
    }

    public static List<AddressResponse> initAddressResponses(int size) {
        final List<AddressResponse> addressInfos = new ArrayList<>();
        for(int i=0; i<size; i++) {
            final AddressResponse addressInfo = AddressResponse.builder()
                .id(i + 1)
                .name("Address " + i)
                .phoneNumber("09090009" + i)
                .address("Full Address " + i)
                .cityId(1)
                .cityCode("CityCode")
                .cityName("CityName")
                .districtId(11)
                .districtCode("DistrictCode")
                .districtName("DistrictName")
                .wardId(111)
                .wardCode("WardCode")
                .wardName("WardName")
                .street("Street")
                .haravanCityCode("HaravanCityCode")
                .haravanDistrictCode("HaravanDistrictCode")
                .haravanWardCode("HaravanWardCode")
                .build();
            addressInfos.add(addressInfo);
        }
        return addressInfos;
    }
}
