package com.betall.core.retail.addressinfrastructure.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import com.betall.core.retail.addresscontext.responses.AddressResponse;

import com.betall.core.retail.addressinfrastructure.models.Address;
import com.betall.core.retail.addressinfrastructure.AddressInfrastructureDataFactory;

import com.betall.core.retail.addressinfrastructure.repositories.ReadAddressRepository;

import com.betall.core.retail.shared_kernels.exceptions.ResourceNotFoundException;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ReadAddressServiceTest {
    private ReadAddressService service;
    private ReadAddressRepository query;

    private List<Address> addresses = AddressInfrastructureDataFactory.initAddresses(5);
    private Address address = AddressInfrastructureDataFactory.initAddress();

    @BeforeEach
    void setUp() {
        query = mock(ReadAddressRepository.class);
        service = mock(ReadAddressService.class, withSettings().useConstructor(query));

        when(service.findAll(any(Pageable.class))).thenCallRealMethod();
        when(query.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(addresses));

        when(service.findById(anyInt())).thenCallRealMethod();
        when(query.findById(anyInt())).thenReturn(Optional.of(address));
    }

    @Test
    void testFindAll() {
        final Page<AddressResponse> response = service.findAll(PageRequest.of(0, 10));
        assertNotNull(response);
        assertTrue(response.hasContent());
        assertEquals(5, response.getContent().size());
    }

    @Test
    void testFindAllNoData() {
        when(query.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(new ArrayList<>()));

        final Page<AddressResponse> response = service.findAll(PageRequest.of(0, 10));
        assertNotNull(response);
        assertFalse(response.hasContent());
    }

    @Test
    void testFindById() {
        final Optional<AddressResponse> response = service.findById(1);
        assertNotNull(response);
        assertTrue(response.isPresent());
    }

    @Test
    void testFindByIdNoData() {
        when(query.findById(anyInt())).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
            ResourceNotFoundException.class,
            () -> service.findById(1),
            String.format("Can't find address with id %s", 1)
        );

        assertTrue(exception.getMessage().contains("Can't find address with id"));
    }

    @Test
    void testFindByIdNull() {
        when(service.findById(any())).thenCallRealMethod();
        when(query.findById(any())).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
            ResourceNotFoundException.class,
            () -> service.findById(null),
            "Can't find address with id null"
        );

        assertTrue(exception.getMessage().contains("Can't find address with id"));
    }
}
