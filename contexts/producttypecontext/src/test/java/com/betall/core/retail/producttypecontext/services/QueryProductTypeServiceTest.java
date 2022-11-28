package com.betall.core.retail.producttypecontext.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.betall.core.retail.producttypecontext.models.ProductTypeInfo;
import com.betall.core.retail.producttypecontext.ProductTypeContextDataFactory;

import com.betall.core.retail.producttypecontext.responses.ProductTypeRepresentation;
import com.betall.core.retail.producttypecontext.responses.ProductListTypeRepresentation;

import com.betall.core.retail.producttypecontext.repositories.QueryProductTypeRepository;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;

class QueryProductTypeServiceTest {
    private QueryProductTypeService service;
    private QueryProductTypeRepository query;

    private List<ProductTypeInfo> productTypes = ProductTypeContextDataFactory.initProductTypes(3);
    private ProductTypeInfo productTypeInfo = ProductTypeContextDataFactory.initProductType();

    @BeforeEach
    void setUp() {
        query = mock(QueryProductTypeRepository.class);
        service = mock(QueryProductTypeService.class, withSettings().useConstructor(query));

        when(service.findAll(anyInt(), anyInt())).thenCallRealMethod();
        when(query.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(productTypes));

        when(service.findById(anyLong())).thenCallRealMethod();
        when(query.findById(anyLong())).thenReturn(Optional.of(productTypeInfo));
    }

    @Test
    void testFindAll() {
        final ProductListTypeRepresentation responses = service.findAll(0, 10);
        assertNotNull(responses);
        assertNotNull(responses.getData());
        assertEquals(3, responses.getData().getLstType().size());
    }

    @Test
    void testFindAllNull() {
        when(query.findAll(any(Pageable.class))).thenReturn(null);

        final ProductListTypeRepresentation responses = service.findAll(0, 10);
        assertNotNull(responses);
        assertNotNull(responses.getData());
        assertEquals(0, responses.getData().getLstType().size());
    }

    @Test
    void testFindAllNoData() {
        when(query.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(new ArrayList<>()));

        final ProductListTypeRepresentation responses = service.findAll(0, 10);
        assertNotNull(responses);
        assertNotNull(responses.getData());
        assertEquals(0, responses.getData().getLstType().size());
    }

    @Test
    void testFindById() {
        final ProductTypeRepresentation response = service.findById(1L);
        assertNotNull(response);
        assertNotNull(response.getData());
    }

    @Test
    void testFindByIdNoData() {
        when(query.findById(anyLong())).thenReturn(Optional.empty());

        final ProductTypeRepresentation response = service.findById(1L);
        assertNotNull(response);
        assertNotNull(response.getData());
        assertNull(response.getData().getId());
    }
}
