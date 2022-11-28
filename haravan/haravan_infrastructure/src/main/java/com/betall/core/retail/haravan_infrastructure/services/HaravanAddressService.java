package com.betall.core.retail.haravan_infrastructure.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.betall.core.retail.shared_kernels.configs.HaravanServiceInfo;

import com.betall.core.retail.haravanaddresscontext.representations.HaravanWardsRepresentation;
import com.betall.core.retail.haravanaddresscontext.representations.HaravanDistrictsRepresentation;
import com.betall.core.retail.haravanaddresscontext.representations.HaravanProvincesRepresentation;

import com.betall.core.retail.haravan_repositories.repositories.HaravanHttpAddressRepository;
import com.betall.core.retail.haravanaddresscontext.repositories.QueryHaravanAddressRepository;

public class HaravanAddressService implements QueryHaravanAddressRepository {
    @Autowired
    private HaravanHttpAddressRepository query;

    @Autowired
    private HaravanServiceInfo serviceInfo;

    @Override
    public HaravanProvincesRepresentation findAllProvinces() {
        return query.getAllProvinces(serviceInfo);
    }

    @Override
    public HaravanDistrictsRepresentation findAllDistrictsByProvince(final Integer provinceId) {
        return query.getAllDistrictsByProvince(serviceInfo, provinceId);
    }

    @Override
    public HaravanWardsRepresentation findAllWardsByDistrictId(final Integer districtId) {
        return query.getAllWardsByDistrictId(serviceInfo, districtId);
    }
}
