package com.betall.core.retail.producttypeinfrastructure.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.betall.core.retail.producttypecontext.models.ProductTypeInfo;

import com.betall.core.retail.producttypeinfrastructure.models.ProductType;
import com.betall.core.retail.producttypeinfrastructure.ProductTypeInfrastructureDataFactory;

import com.betall.core.retail.producttypeinfrastructure.repositories.ReadProductTypeRepository;

import com.betall.core.retail.shared_kernels.exceptions.ResourceNotFoundException;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;

class ReadProductTypeServiceTest {
    private ReadProductTypeService service;
    private ReadProductTypeRepository query;

    private List<ProductType> productTypes = ProductTypeInfrastructureDataFactory.initProductTypes(5);
    private ProductType productType = ProductTypeInfrastructureDataFactory.initProductType();

    @BeforeEach
    void setUp() {
        query = mock(ReadProductTypeRepository.class);
        service = mock(ReadProductTypeService.class, withSettings().useConstructor(query));

        when(service.findAll(any(Pageable.class))).thenCallRealMethod();
        when(query.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(productTypes));

        when(service.findById(anyLong())).thenCallRealMethod();
        when(query.findById(anyLong())).thenReturn(Optional.of(productType));
    }

    @Test
    void testFindAll() {
        final Page<ProductTypeInfo> responses = service.findAll(PageRequest.of(0, 10));
        assertNotNull(responses);
        assertTrue(responses.hasContent());
    }

    @Test
    void testFindAllNoData() {
        when(query.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(new ArrayList<>()));

        final Page<ProductTypeInfo> responses = service.findAll(PageRequest.of(0, 10));
        assertNotNull(responses);
        assertFalse(responses.hasContent());
    }

    @Test
    void testFindById() {
        final Optional<ProductTypeInfo> response = service.findById(1L);
        assertNotNull(response);
        assertTrue(response.isPresent());
    }

    @Test
    void testFindByIdNoData() {
        when(query.findById(anyLong())).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
            ResourceNotFoundException.class,
            () -> service.findById(1L),
            String.format("Can't find product type with id %s", 1)
        );

        assertTrue(exception.getMessage().contains("Can't find product type with id"));
    }
}
