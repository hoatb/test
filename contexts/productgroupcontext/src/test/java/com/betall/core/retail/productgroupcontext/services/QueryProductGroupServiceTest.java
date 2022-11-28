package com.betall.core.retail.productgroupcontext.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.betall.core.retail.productgroupcontext.models.ProductGroupInfo;
import com.betall.core.retail.productgroupcontext.ProductGroupContextDataFactory;

import com.betall.core.retail.productgroupcontext.responses.ProductGroupRepresentation;
import com.betall.core.retail.productgroupcontext.responses.ProductListGroupRepresentation;

import com.betall.core.retail.productgroupcontext.repositories.QueryProductGroupRepository;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class QueryProductGroupServiceTest {
    private QueryProductGroupService service;
    private QueryProductGroupRepository query;

    private List<ProductGroupInfo> productGroups = ProductGroupContextDataFactory.initProductGroups(3);
    private ProductGroupInfo productGroupInfo = ProductGroupContextDataFactory.initProductGroup();

    @BeforeEach
    void setUp() {
        query = mock(QueryProductGroupRepository.class);
        service = mock(QueryProductGroupService.class, withSettings().useConstructor(query));

        when(service.findAll(anyInt(), anyInt())).thenCallRealMethod();
        when(query.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(productGroups));

        when(service.findById(anyLong())).thenCallRealMethod();
        when(query.findById(anyLong())).thenReturn(Optional.of(productGroupInfo));
    }

    @Test
    void testFindAll() {
        final ProductListGroupRepresentation responses = service.findAll(0, 10);
        assertNotNull(responses);
        assertNotNull(responses.getData());
        assertEquals(3, responses.getData().getLstGroup().size());
    }

    @Test
    void testFindAllNull() {
        when(query.findAll(any(Pageable.class))).thenReturn(null);

        final ProductListGroupRepresentation responses = service.findAll(0, 10);
        assertNotNull(responses);
        assertNotNull(responses.getData());
        assertEquals(0, responses.getData().getLstGroup().size());
    }

    @Test
    void testFindAllNoData() {
        when(query.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(new ArrayList<>()));

        final ProductListGroupRepresentation responses = service.findAll(0, 10);
        assertNotNull(responses);
        assertNotNull(responses.getData());
        assertEquals(0, responses.getData().getLstGroup().size());
    }

    @Test
    void testFindById() {
        final ProductGroupRepresentation response = service.findById(1L);
        assertNotNull(response);
        assertNotNull(response.getData());
    }

    @Test
    void testFindByIdNoData() {
        when(query.findById(anyLong())).thenReturn(Optional.empty());

        final ProductGroupRepresentation response = service.findById(1L);
        assertNotNull(response);
        assertNotNull(response.getData());
        assertNull(response.getData().getId());
    }
}
