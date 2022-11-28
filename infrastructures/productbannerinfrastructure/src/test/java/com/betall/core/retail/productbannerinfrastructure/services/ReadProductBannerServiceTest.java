package com.betall.core.retail.productbannerinfrastructure.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.betall.core.retail.productbannercontext.models.ProductBannerInfo;

import com.betall.core.retail.productbannerinfrastructure.models.ProductBanner;
import com.betall.core.retail.productbannerinfrastructure.ProductBannerInfrastructureDataFactory;
import com.betall.core.retail.productbannerinfrastructure.repositories.ReadProductBannerRepository;

import com.betall.core.retail.shared_kernels.exceptions.ResourceNotFoundException;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;

class ReadProductBannerServiceTest {
    private ReadProductBannerService service;
    private ReadProductBannerRepository query;

    private List<ProductBanner> productBanners = ProductBannerInfrastructureDataFactory.initProductBanners(5);
    private ProductBanner productBanner = ProductBannerInfrastructureDataFactory.initProductBanner();

    @BeforeEach
    void setUp() {
        query = mock(ReadProductBannerRepository.class);
        service = mock(ReadProductBannerService.class, withSettings().useConstructor(query));

        when(service.findAll(any(Pageable.class))).thenCallRealMethod();
        when(query.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(productBanners));

        when(service.findById(anyLong())).thenCallRealMethod();
        when(query.findById(anyLong())).thenReturn(Optional.of(productBanner));
    }

    @Test
    void testFindAll() {
        final Page<ProductBannerInfo> responses = service.findAll(PageRequest.of(0, 10));
        assertNotNull(responses);
        assertTrue(responses.hasContent());
    }

    @Test
    void testFindAllNoData() {
        when(query.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(new ArrayList<>()));

        final Page<ProductBannerInfo> responses = service.findAll(PageRequest.of(0, 10));
        assertNotNull(responses);
        assertFalse(responses.hasContent());
    }

    @Test
    void testFindById() {
        final Optional<ProductBannerInfo> response = service.findById(1L);
        assertNotNull(response);
        assertTrue(response.isPresent());
    }

    @Test
    void testFindByIdNoData() {
        when(query.findById(anyLong())).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
            ResourceNotFoundException.class,
            () -> service.findById(1L),
            String.format("Can't find product banner with id %s", 1)
        );

        assertTrue(exception.getMessage().contains("Can't find product banner with id"));
    }
}
