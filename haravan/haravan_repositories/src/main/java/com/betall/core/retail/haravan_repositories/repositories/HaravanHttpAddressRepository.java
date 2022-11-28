package com.betall.core.retail.haravan_repositories.repositories;

import org.springframework.stereotype.Repository;

import com.betall.core.retail.shared_kernels.configs.HaravanServiceInfo;
import com.betall.core.retail.haravanaddresscontext.representations.HaravanWardsRepresentation;
import com.betall.core.retail.haravanaddresscontext.representations.HaravanDistrictsRepresentation;
import com.betall.core.retail.haravanaddresscontext.representations.HaravanProvincesRepresentation;

@Repository
public interface HaravanHttpAddressRepository {
    HaravanProvincesRepresentation getAllProvinces(final HaravanServiceInfo serviceInfo);
    HaravanDistrictsRepresentation getAllDistrictsByProvince(final HaravanServiceInfo serviceInfo, final Integer provinceId);
    HaravanWardsRepresentation getAllWardsByDistrictId(final HaravanServiceInfo serviceInfo, final Integer districtId);
}
