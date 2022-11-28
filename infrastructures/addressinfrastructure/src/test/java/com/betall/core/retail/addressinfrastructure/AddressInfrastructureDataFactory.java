package com.betall.core.retail.addressinfrastructure;

import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

import com.betall.core.retail.addresscontext.models.AddressInfo;
import com.betall.core.retail.addresscontext.models.WardInfo;
import com.betall.core.retail.addresscontext.models.DistrictInfo;
import com.betall.core.retail.addresscontext.models.ProvinceInfo;
import com.betall.core.retail.addresscontext.requests.UpdateAddressInfo;
import com.betall.core.retail.addresscontext.responses.AddressResponse;
import com.betall.core.retail.addressinfrastructure.models.Address;
import com.betall.core.retail.addressinfrastructure.models.District;
import com.betall.core.retail.addressinfrastructure.models.Province;
import com.betall.core.retail.addressinfrastructure.models.Ward;

public class AddressInfrastructureDataFactory {
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

    public static Province initProvince() {
        return Province.builder()
            .id(2)
            .countryId(241)
            .code("HG")
            .name("Hà Giang")
            .tax(null)
            .taxName(null)
            .taxType(null)
            .taxPercentage(null)
            .isActive(true)
            .districts(initDistricts(4))
            .build();
    }

    public static Set<District> initDistricts(int size) {
        final Set<District> districts = new HashSet<>();
        for (int i=0; i<size; i++) {
            final District district = District.builder()
                .id(i + 1)
                .code("HG" + i)
                .name("Quan/Huyen " + i)
                .isActive(true)
                .wards(initWards(3))
                .build();
            districts.add(district);
        }
        return districts;
    }

    public static Set<Ward> initWards(int size) {
        final Set<Ward> wards = new HashSet<>();
        for(int i=0; i<size; i++) {
            final Ward ward = Ward.builder()
                .id(i + 1)
                .code("1000" + i)
                .name("Phuong/Xa " + i)
                .isActive(true)
                .build();
            wards.add(ward);
        }
        return wards;
    }

    public static List<Province> initProvinces(int size) {
        final List<Province> provinces = new ArrayList<>();
        for(int i=0; i<size; i++) {
            final Province province = initProvince(i+1);
            provinces.add(province);
        }
        return provinces;
    }

    private static Province initProvince(int id) {
        return Province.builder()
            .id(id)
            .countryId(241)
            .code(String.format("Code_%s", id))
            .name(String.format("Province_%s", id))
            .tax(null)
            .taxName(null)
            .taxType(null)
            .taxPercentage(null)
            .isActive(true)
            .districts(initDistricts(4))
            .build();
    }

    public static AddressResponse initAddressResponse() {
        return AddressResponse.builder()
            .id(1)
            .name("Name")
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
            .haravanCityCode("HaravanCity")
            .haravanDistrictCode("HaravanDistrict")
            .haravanWardCode("HaravanWard")
            .build();
    }

    public static AddressInfo initAddressInfo() {
        return AddressInfo.builder()
                .id(1)
                .name("Name")
                .phoneNumber("0909000999")
                .address("Full Address")
                .cityId(1)
                .districtId(11)
                .wardId(111)
                .street("Street")
                .haravanCityCode("HaravanCity")
                .haravanDistrictCode("HaravanDistrict")
                .haravanWardCode("HaravanWard")
                .build();
    }

    public static UpdateAddressInfo initUpdateAddressInfo() {
        return UpdateAddressInfo.builder()
            .id(1)
            .name("Name")
            .phoneNumber("0909000999")
            .currentAddress("Full Address")
            .currentCity(1)
            .currentDistrict(11)
            .currentWard(111)
            .build();
    }

    public static Address initAddress() {
        return Address.builder()
            .id(1)
            .name("Name")
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
            .haravanCityCode("HaravanCity")
            .haravanDistrictCode("HaravanDistrict")
            .haravanWardCode("HaravanWard")
            .build();
    }

    public static List<Address> initAddresses(int size) {
        final List<Address> addresses = new ArrayList<>();
        for(int i=0; i<size; i++) {
            final Address address = Address.builder()
                .id(i+1)
                .name("Name " + i)
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
                .haravanCityCode("HaravanCity")
                .haravanDistrictCode("HaravanDistrict")
                .haravanWardCode("HaravanWard")
                .build();
            addresses.add(address);
        }
        return addresses;
    }
}
