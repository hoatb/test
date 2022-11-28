package com.betall.core.retail.haravanaddresscontext.services;

import com.betall.core.retail.haravanaddresscontext.QueryHaravanAddressDataFactory;
import com.betall.core.retail.haravanaddresscontext.repositories.QueryHaravanAddressRepository;
import com.betall.core.retail.haravanaddresscontext.representations.HaravanDistrictsRepresentation;
import com.betall.core.retail.haravanaddresscontext.representations.HaravanProvincesRepresentation;
import com.betall.core.retail.haravanaddresscontext.representations.HaravanWardsRepresentation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class QueryHaravanAddressServiceTest {
    private QueryHaravanAddressService service;
    private QueryHaravanAddressRepository query;

    private HaravanProvincesRepresentation haravanProvincesRepresentation = QueryHaravanAddressDataFactory.initHaravanProvincesRepresentation(5);
    private HaravanProvincesRepresentation haravanProvincesNoData = QueryHaravanAddressDataFactory.initHaravanProvincesNoData();
    private HaravanDistrictsRepresentation haravanDistrictsRepresentation = QueryHaravanAddressDataFactory.initHaravanDistrictsRepresentation(5);
    private HaravanDistrictsRepresentation haravanDistrictsNoData = QueryHaravanAddressDataFactory.initHaravanDistrictsNoData();
    private HaravanWardsRepresentation haravanWardsRepresentation = QueryHaravanAddressDataFactory.initHaravanWardsRepresentation(5);
    private HaravanWardsRepresentation haravanWardsNoData = QueryHaravanAddressDataFactory.initHaravanWardsRepresentation();

    @BeforeEach
    void setUp() {
        query = mock(QueryHaravanAddressRepository.class);
        service = mock(QueryHaravanAddressService.class, withSettings().useConstructor(query));

        when(service.findAllProvinces()).thenCallRealMethod();
        when(query.findAllProvinces()).thenReturn(haravanProvincesRepresentation);

        when(service.findAllDistrictsByProvince(anyInt())).thenCallRealMethod();
        when(query.findAllDistrictsByProvince(anyInt())).thenReturn(haravanDistrictsRepresentation);

        when(service.findAllWardsByDistrictId(anyInt())).thenCallRealMethod();
        when(query.findAllWardsByDistrictId(anyInt())).thenReturn(haravanWardsRepresentation);
    }

    @Test
    void testFindAllProvinces() {
        final HaravanProvincesRepresentation response = service.findAllProvinces();
        assertNotNull(response);
        assertNotNull(response.getProvinces());
    }

    @Test
    void testFindAllProvinceNoData() {
        when(query.findAllProvinces()).thenReturn(haravanProvincesNoData);

        final HaravanProvincesRepresentation response = service.findAllProvinces();
        assertNotNull(response);
        assertNotNull(response.getProvinces());
        assertEquals(0, response.getProvinces().size());
    }

    @Test
    void testFindDistrictsByProvice() {
        final HaravanDistrictsRepresentation response = service.findAllDistrictsByProvince(1);
        assertNotNull(response);
        assertNotNull(response.getDistricts());
    }

    @Test
    void testFindDistrictsByProvinceNoData() {
        when(query.findAllDistrictsByProvince(anyInt())).thenReturn(haravanDistrictsNoData);

        final HaravanDistrictsRepresentation response = service.findAllDistrictsByProvince(1);
        assertNotNull(response);
        assertNotNull(response.getDistricts());
        assertEquals(0, response.getDistricts().size());
    }

    @Test
    void testFindWardsByDistrict() {
        final HaravanWardsRepresentation response = service.findAllWardsByDistrictId(1);
        assertNotNull(response);
        assertNotNull(response.getWards());
    }

    @Test
    void testFindWardsByDistrictNoData() {
        when(query.findAllWardsByDistrictId(anyInt())).thenReturn(haravanWardsNoData);

        final HaravanWardsRepresentation response = service.findAllWardsByDistrictId(1);
        assertNotNull(response);
        assertNotNull(response.getWards());
        assertEquals(0, response.getWards().size());
    }
}
