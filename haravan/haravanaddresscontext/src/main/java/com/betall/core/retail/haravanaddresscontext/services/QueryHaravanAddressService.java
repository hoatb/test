package com.betall.core.retail.haravanaddresscontext.services;

import com.betall.core.retail.haravanaddresscontext.representations.HaravanWardsRepresentation;
import com.betall.core.retail.haravanaddresscontext.representations.HaravanDistrictsRepresentation;
import com.betall.core.retail.haravanaddresscontext.representations.HaravanProvincesRepresentation;

import com.betall.core.retail.haravanaddresscontext.repositories.QueryHaravanAddressRepository;

public class QueryHaravanAddressService {
    private QueryHaravanAddressRepository query;

    public QueryHaravanAddressService(final QueryHaravanAddressRepository query) {
        this.query = query;
    }

    /**
     * Find all provinces
     * @return ProvincesRepresentation
     */
    public HaravanProvincesRepresentation findAllProvinces() {
        return query.findAllProvinces();
    }

    /**
     * Find all districts by province id
     * @param provinceId
     * @return DistrictsRepresentation
     */
    public HaravanDistrictsRepresentation findAllDistrictsByProvince(final Integer provinceId) {
        return query.findAllDistrictsByProvince(provinceId);
    }

    /**
     * Find all wards by district id
     * @param districtId
     * @return WardsRepresentation
     */
    public HaravanWardsRepresentation findAllWardsByDistrictId(final Integer districtId) {
        return query.findAllWardsByDistrictId(districtId);
    }
}
