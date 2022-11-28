package com.betall.core.retail.productcontext.services;

import com.betall.core.retail.productcontext.ProductContextDataFactory;
import com.betall.core.retail.productcontext.models.ProductDetailInfo;
import com.betall.core.retail.productcontext.models.ProductInfoList;
import com.betall.core.retail.productcontext.repositories.QueryProductRepository;
import com.betall.core.retail.productcontext.requests.OrderProductList;
import com.betall.core.retail.productcontext.requests.ProductListRequest;
import com.betall.core.retail.productcontext.responses.ProductDetailRepresentation;
import com.betall.core.retail.productcontext.responses.ProductInfoRepresentation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class QueryProductServiceTest {
    private QueryProductService service;
    private QueryProductRepository query;

    private ProductDetailInfo product = ProductContextDataFactory.initProductDetailInfo(1L);
    private ProductInfoList productInfoList = ProductContextDataFactory.initProductInfoList(5L);
    private OrderProductList orderProductList = ProductContextDataFactory.initLstOrderProduct(5L);
    private ProductListRequest productListRequest = ProductContextDataFactory.initProductListRequest();

    @BeforeEach
    void setUp() {
        query = mock(QueryProductRepository.class);
        service = mock(QueryProductService.class, withSettings().useConstructor(query));

        when(service.findAll(any(ProductListRequest.class))).thenCallRealMethod();
        when(query.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(productInfoList.getLstProduct()));

        when(service.findById(anyLong())).thenCallRealMethod();
        when(query.findById(anyLong())).thenReturn(Optional.of(product));
    }

    @Test
    void testFindAll() {
        final ProductInfoRepresentation response = service.findAll(productListRequest);
        assertNotNull(response);
        assertNotNull(response.getData());
        assertNotNull(response.getData().getTotal());
        assertNotNull(response.getData().getLstProduct());
        assertFalse(response.getData().getLstProduct().isEmpty());
    }

    @Test
    void testFindAllWithEmptyData() {
        when(query.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(List.of()));
        final ProductInfoRepresentation response = service.findAll(productListRequest);
        assertNotNull(response);
        assertNull(response.getData());
    }

    @Test
    void testFindAllWithNullData() {
        when(query.findAll(any(Pageable.class))).thenReturn(null);
        final ProductInfoRepresentation response = service.findAll(productListRequest);
        assertNotNull(response);
        assertNull(response.getData());
    }

    @Test
    void testFindById(){
        final ProductDetailRepresentation response = service.findById(1L);
        assertNotNull(response);
        assertNotNull(response.getData());
        assertNotNull(response.getData().getProductDetailInfo());
        assertNotNull(response.getData().getProductDetailInfo().getProductId());
    }

    @Test
    void testFindByIdReturnNoData(){
        when(query.findById(anyLong())).thenReturn(Optional.empty());
        final ProductDetailRepresentation response = service.findById(1L);
        assertNotNull(response);
        assertNull(response.getData());
    }
}
