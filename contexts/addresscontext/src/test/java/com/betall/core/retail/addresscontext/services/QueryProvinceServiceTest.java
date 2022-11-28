package com.betall.core.retail.addresscontext.services;

import org.junit.jupiter.api.BeforeEach;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.betall.core.retail.addresscontext.models.ProvinceInfo;
import com.betall.core.retail.addresscontext.AddressContextDataFactory;
import com.betall.core.retail.addresscontext.representations.ProvinceRepresentation;
import com.betall.core.retail.addresscontext.representations.ProvincesRepresentation;

import com.betall.core.retail.addresscontext.repositories.QueryProvinceRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class QueryProvinceServiceTest {
    private QueryProvinceService service;
    private QueryProvinceRepository query;

    private List<ProvinceInfo> provinceInfos = AddressContextDataFactory.initProvinceInfos(5);
    private ProvinceInfo provinceInfo = AddressContextDataFactory.initProvinceInfo();

    @BeforeEach
    void setUp() {
        query = mock(QueryProvinceRepository.class);
        service = mock(QueryProvinceService.class, withSettings().useConstructor(query));

        when(service.findAll(anyInt(), anyInt())).thenCallRealMethod();
        when(query.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(provinceInfos));

        when(service.findById(anyInt())).thenCallRealMethod();
        when(query.findById(anyInt())).thenReturn(Optional.of(provinceInfo));
    }

    @Test
    void testFindAll() {
        final ProvincesRepresentation responses = service.findAll(0, 10);
        assertNotNull(responses);
        assertNotNull(responses.getData());
        assertEquals(5, responses.getData().size());
    }

    @Test
    void testFindAllNoData() {
        when(query.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(new ArrayList<>()));

        final ProvincesRepresentation responses = service.findAll(0, 10);
        assertNotNull(responses);
        assertNotNull(responses.getData());
        assertEquals(0, responses.getData().size());
    }

    @Test
    void testFindAllPageNull() {
        when(query.findAll(any(Pageable.class))).thenReturn(null);

        final ProvincesRepresentation responses = service.findAll(0, 10);
        assertNotNull(responses);
        assertNotNull(responses.getData());
        assertEquals(0, responses.getData().size());
    }

    @Test
    void testFindById() {
        final ProvinceRepresentation response = service.findById(1);
        assertNotNull(response);
        assertNotNull(response.getData());
    }

    @Test
    void testFindByIdNoData() {
        when(query.findById(anyInt())).thenReturn(Optional.empty());

        final ProvinceRepresentation response = service.findById(1);
        assertNotNull(response);
        assertNotNull(response.getData());
        assertNull(response.getData().getId());
    }
}
