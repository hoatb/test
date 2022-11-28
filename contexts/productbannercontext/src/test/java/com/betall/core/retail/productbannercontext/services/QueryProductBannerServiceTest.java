package com.betall.core.retail.productbannercontext.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.betall.core.retail.productbannercontext.models.ProductBannerInfo;
import com.betall.core.retail.productbannercontext.ProductBannerContextDataFactory;
import com.betall.core.retail.productbannercontext.responses.ProductBannerRepresentation;
import com.betall.core.retail.productbannercontext.responses.ProductListBannerRepresentation;

import com.betall.core.retail.productbannercontext.repositories.QueryProductBannerRepository;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;

class QueryProductBannerServiceTest {
    private QueryProductBannerService service;
    private QueryProductBannerRepository query;

    private List<ProductBannerInfo> productBanners = ProductBannerContextDataFactory.initProductBanners(3);
    private ProductBannerInfo productBannerInfo = ProductBannerContextDataFactory.initProductBanner();

    @BeforeEach
    void setUp() {
        query = mock(QueryProductBannerRepository.class);
        service = mock(QueryProductBannerService.class, withSettings().useConstructor(query));

        when(service.findAll(anyInt(), anyInt())).thenCallRealMethod();
        when(query.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(productBanners));

        when(service.findById(anyLong())).thenCallRealMethod();
        when(query.findById(anyLong())).thenReturn(Optional.of(productBannerInfo));
    }

    @Test
    void testFindAll() {
        final ProductListBannerRepresentation responses = service.findAll(0, 10);
        assertNotNull(responses);
        assertNotNull(responses.getData());
        assertEquals(3, responses.getData().getLstBanner().size());
    }

    @Test
    void testFindAllNull() {
        when(query.findAll(any(Pageable.class))).thenReturn(null);

        final ProductListBannerRepresentation responses = service.findAll(0, 10);
        assertNotNull(responses);
        assertNotNull(responses.getData());
        assertEquals(0, responses.getData().getLstBanner().size());
    }

    @Test
    void testFindAllNoData() {
        when(query.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(new ArrayList<>()));

        final ProductListBannerRepresentation responses = service.findAll(0, 10);
        assertNotNull(responses);
        assertNotNull(responses.getData());
        assertEquals(0, responses.getData().getLstBanner().size());
    }

    @Test
    void testFindById() {
        final ProductBannerRepresentation response = service.findById(1L);
        assertNotNull(response);
        assertNotNull(response.getData());
    }

    @Test
    void testFindByIdNoData() {
        when(query.findById(anyLong())).thenReturn(Optional.empty());

        final ProductBannerRepresentation response = service.findById(1L);
        assertNotNull(response);
        assertNotNull(response.getData());
        assertNull(response.getData().getId());
    }
}
