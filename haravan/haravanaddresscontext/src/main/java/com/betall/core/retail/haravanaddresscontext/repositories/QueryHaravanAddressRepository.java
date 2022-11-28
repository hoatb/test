package com.betall.core.retail.haravanaddresscontext.repositories;

import com.betall.core.retail.haravanaddresscontext.representations.HaravanWardsRepresentation;
import com.betall.core.retail.haravanaddresscontext.representations.HaravanDistrictsRepresentation;
import com.betall.core.retail.haravanaddresscontext.representations.HaravanProvincesRepresentation;

public interface QueryHaravanAddressRepository {
    HaravanProvincesRepresentation findAllProvinces();
    HaravanDistrictsRepresentation findAllDistrictsByProvince(final Integer provinceId);
    HaravanWardsRepresentation findAllWardsByDistrictId(final Integer districtId);
}
