package com.betall.core.retail.addresscontext.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.betall.core.retail.addresscontext.AddressContextDataFactory;
import com.betall.core.retail.addresscontext.responses.AddressResponse;
import com.betall.core.retail.addresscontext.representations.AddressRepresentation;
import com.betall.core.retail.addresscontext.representations.AddressesRepresentation;

import com.betall.core.retail.addresscontext.repositories.QueryAddressRepository;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class QueryAddressServiceTest {
    private QueryAddressService service;
    private QueryAddressRepository query;

    private List<AddressResponse> addressResponses = AddressContextDataFactory.initAddressResponses(5);
    private AddressResponse addressResponse = AddressContextDataFactory.initAddressResponse();

    @BeforeEach
    void setUp() {
        query = mock(QueryAddressRepository.class);
        service = mock(QueryAddressService.class, withSettings().useConstructor(query));

        when(service.findAll(anyInt(), anyInt())).thenCallRealMethod();
        when(query.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(addressResponses));

        when(service.findById(anyInt())).thenCallRealMethod();
        when(query.findById(anyInt())).thenReturn(Optional.of(addressResponse));
    }

    @Test
    void testFindAll() {
        final AddressesRepresentation response = service.findAll(0, 10);
        assertNotNull(response);
        assertNotNull(response.getData());
        assertEquals(5, response.getData().getLstAddress().size());
    }

    @Test
    void testFindAllNoData() {
        when(query.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(new ArrayList<>()));

        final AddressesRepresentation response = service.findAll(0, 10);
        assertNotNull(response);
        assertNotNull(response.getData());
        assertEquals(0, response.getData().getLstAddress().size());
    }

    @Test
    void testFindAllNullData() {
        when(query.findAll(any(Pageable.class))).thenReturn(null);

        final AddressesRepresentation response = service.findAll(0, 10);
        assertNotNull(response);
        assertNotNull(response.getData());
        assertEquals(0, response.getData().getLstAddress().size());
    }

    @Test
    void testFindById() {
        final AddressRepresentation response = service.findById(1);
        assertNotNull(response);
        assertNotNull(response.getData());
    }

    @Test
    void testFindByIdNoData() {
        when(query.findById(anyInt())).thenReturn(Optional.empty());

        final AddressRepresentation response = service.findById(1);
        assertNotNull(response);
        assertNotNull(response.getData());
        assertNull(response.getData().getId());
    }
}
