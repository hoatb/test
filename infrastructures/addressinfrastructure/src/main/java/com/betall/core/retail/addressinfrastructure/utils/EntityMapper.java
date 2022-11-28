package com.betall.core.retail.addressinfrastructure.utils;

import java.util.Set;
import java.util.List;
import java.util.stream.Collectors;

import com.betall.core.retail.addresscontext.models.AddressInfo;
import com.betall.core.retail.addresscontext.models.WardInfo;
import com.betall.core.retail.addresscontext.representations.AddressRepresentation;
import com.betall.core.retail.addresscontext.requests.UpdateAddressInfo;
import com.betall.core.retail.addresscontext.responses.AddressResponse;
import com.betall.core.retail.addressinfrastructure.models.Address;
import com.betall.core.retail.addressinfrastructure.models.Ward;
import org.springframework.beans.BeanUtils;

import com.betall.core.retail.addresscontext.models.DistrictInfo;
import com.betall.core.retail.addresscontext.models.ProvinceInfo;

import com.betall.core.retail.addresscontext.representations.ProvinceRepresentation;

import com.betall.core.retail.addressinfrastructure.models.District;
import com.betall.core.retail.addressinfrastructure.models.Province;

public class EntityMapper {
    public Province toProvince(final ProvinceInfo provinceInfo) {
        final Province province = Province.builder().build();
        BeanUtils.copyProperties(provinceInfo, province);
        province.setIsActive(true);
        province.setDistricts(toDistricts(province, provinceInfo.getDistricts()));

        return province;
    }

    private Set<District> toDistricts(final Province province, final List<DistrictInfo> districtInfoList) {
        final Set<District> districts = districtInfoList.stream().map(districtInfo -> {
            final District district = District.builder().build();
            BeanUtils.copyProperties(districtInfo, district);
            district.setIsActive(true);
            district.setProvince(province);
            district.setWards(toWards(district, districtInfo.getWards()));
            return district;
        }).collect(Collectors.toSet());
        return districts;
    }

    private Set<Ward> toWards(final District district, final List<WardInfo> wardInfoList) {
        final Set<Ward> wards = wardInfoList.stream().map(wardInfo -> {
            final Ward ward = Ward.builder().build();
            BeanUtils.copyProperties(wardInfo, ward);
            ward.setIsActive(true);
            ward.setDistrict(district);
            return ward;
        }).collect(Collectors.toSet());
        return wards;
    }

    public ProvinceInfo toProvinceInfo(final Province province) {
        final ProvinceInfo provinceInfo = ProvinceInfo.builder().build();
        BeanUtils.copyProperties(province, provinceInfo);
        provinceInfo.setDistricts(toDistrictInfos(province.getDistricts()));
        return provinceInfo;
    }

    public List<DistrictInfo> toDistrictInfos(final Set<District> districts) {
        return districts.stream().map(
            district -> {
                final DistrictInfo districtInfo = DistrictInfo.builder().build();
                BeanUtils.copyProperties(district, districtInfo);
                districtInfo.setWards(toWardInfos(district.getWards()));
                return districtInfo;
            }
        ).collect(Collectors.toList());
    }

    public List<WardInfo> toWardInfos(final Set<Ward> wards) {
        return wards.stream().map(
            ward -> {
                final WardInfo wardInfo = WardInfo.builder().build();
                BeanUtils.copyProperties(ward, wardInfo);
                return wardInfo;
            }
        ).collect(Collectors.toList());
    }

    public ProvinceRepresentation toProvinceRepresentation(final Province province) {
        final ProvinceRepresentation provinceRepresentation = ProvinceRepresentation.builder()
            .data(toProvinceInfo(province))
            .build();
        return provinceRepresentation;
    }

    public void update(final Province province, final ProvinceInfo provinceInfo) {
        BeanUtils.copyProperties(provinceInfo, province);
        province.setDistricts(toDistricts(province, provinceInfo.getDistricts()));
    }

    public Address toAddress(final AddressInfo addressInfo) {
        final Address address = Address.builder().build();
        BeanUtils.copyProperties(addressInfo, address);
        return address;
    }

    public AddressRepresentation toAddressRepresentation(final Address address) {
        final AddressResponse addressInfo = toAddressResponse(address);
        return AddressRepresentation.builder()
            .status(0)
            .message(null)
            .data(addressInfo)
            .build();
    }

    public void update(final Address address, final UpdateAddressInfo addressInfo) {
        BeanUtils.copyProperties(addressInfo, address);
        address.setAddress(addressInfo.getCurrentAddress());
        address.setCityId(addressInfo.getCurrentCity());
        address.setDistrictId(addressInfo.getCurrentDistrict());
        address.setWardId(addressInfo.getCurrentWard());
    }

    public AddressResponse toAddressResponse(final Address address) {
        final AddressResponse addressInfo = AddressResponse.builder().build();
        BeanUtils.copyProperties(address, addressInfo);
        return addressInfo;
    }
}
