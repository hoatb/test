package com.betall.core.retail.productgroupinfrastructure.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import com.betall.core.retail.productgroupcontext.models.ProductGroupInfo;
import com.betall.core.retail.productgroupinfrastructure.models.ProductGroup;
import com.betall.core.retail.productgroupinfrastructure.ProductGroupInfrastructureDataFactory;

import com.betall.core.retail.productgroupinfrastructure.repositories.ReadProductGroupRepository;

import com.betall.core.retail.shared_kernels.exceptions.ResourceNotFoundException;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ReadProductGroupServiceTest {
    private ReadProductGroupService service;
    private ReadProductGroupRepository query;

    private List<ProductGroup> productGroups = ProductGroupInfrastructureDataFactory.initProductGroups(5);
    private ProductGroup productGroup = ProductGroupInfrastructureDataFactory.initProductGroup();

    @BeforeEach
    void setUp() {
        query = mock(ReadProductGroupRepository.class);
        service = mock(ReadProductGroupService.class, withSettings().useConstructor(query));

        when(service.findAll(any(Pageable.class))).thenCallRealMethod();
        when(query.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(productGroups));

        when(service.findById(anyLong())).thenCallRealMethod();
        when(query.findById(anyLong())).thenReturn(Optional.of(productGroup));
    }

    @Test
    void testFindAll() {
        final Page<ProductGroupInfo> responses = service.findAll(PageRequest.of(0, 10));
        assertNotNull(responses);
        assertTrue(responses.hasContent());
    }

    @Test
    void testFindAllNoData() {
        when(query.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(new ArrayList<>()));

        final Page<ProductGroupInfo> responses = service.findAll(PageRequest.of(0, 10));
        assertNotNull(responses);
        assertFalse(responses.hasContent());
    }

    @Test
    void testFindById() {
        final Optional<ProductGroupInfo> response = service.findById(1L);
        assertNotNull(response);
        assertTrue(response.isPresent());
    }

    @Test
    void testFindByIdNoData() {
        when(query.findById(anyLong())).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
            ResourceNotFoundException.class,
            () -> service.findById(1L),
            String.format("Can't find product group with id %s", 1)
        );

        assertTrue(exception.getMessage().contains("Can't find product group with id"));
    }
}
