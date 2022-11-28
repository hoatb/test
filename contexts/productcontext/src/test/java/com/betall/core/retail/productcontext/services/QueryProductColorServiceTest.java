package com.betall.core.retail.productcontext.services;

import com.betall.core.retail.productcontext.ProductContextDataFactory;
import com.betall.core.retail.productcontext.models.ProductInfoList;
import com.betall.core.retail.productcontext.repositories.QueryProductColorRepository;
import com.betall.core.retail.productcontext.requests.OrderProductList;
import com.betall.core.retail.productcontext.responses.ProductInfoRepresentation;
import com.betall.core.retail.shared_kernels.exceptions.DataViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class QueryProductColorServiceTest {
    private QueryProductColorService service;
    private QueryProductColorRepository query;

    private ProductInfoList productInfoList = ProductContextDataFactory.initProductInfoList(5L);
    private OrderProductList orderProductList = ProductContextDataFactory.initLstOrderProduct(5L);

    @BeforeEach
    void setUp() {
        query = mock(QueryProductColorRepository.class);
        service = mock(QueryProductColorService.class, withSettings().useConstructor(query));

        when(service.findProductListById(any(OrderProductList.class))).thenCallRealMethod();
        when(query.findProductListById(any(OrderProductList.class))).thenReturn(productInfoList);
    }

    @Test
    void testFindProductListById() {
        ProductInfoRepresentation response = service.findProductListById(orderProductList);
        assertNotNull(response);
        assertNotNull(response.getData());
        assertNull(response.getData().getTotal());
        assertNotNull(response.getData().getLstProduct());
        assertFalse(response.getData().getLstProduct().isEmpty());
    }

    @Test
    void testFindProductListByIdtWithNoData() {
        when(query.findProductListById(any())).thenReturn(ProductInfoList.builder().lstProduct(new ArrayList<>()).build());
        final ProductInfoRepresentation response = service.findProductListById(orderProductList);
        assertNotNull(response);
        assertNull(response.getData());
    }

    @Test
    void testFindProductListByIdWithLstOrderProductNull() {
        orderProductList.setLstOrderProduct(null);
        final DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.findProductListById(orderProductList),
            "OrderProductList's lstOrderProduct must not be null"
        );
        assertTrue(exception.getMessage().contains("OrderProductList's lstOrderProduct must not be null"));
    }

    @Test
    void testFindProductListByIdWithLstOrderProductEmpty() {
        orderProductList.setLstOrderProduct(new ArrayList<>());
        final DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.findProductListById(orderProductList),
            "OrderProductList's lstOrderProduct must not be null or empty."
        );
        assertTrue(exception.getMessage().contains("OrderProductList's lstOrderProduct must not be null or empty."));
    }

    @Test
    void testFindProductListByIdWithProductIdNull() {
        orderProductList.getLstOrderProduct().get(0).setProductId(null);
        final DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.findProductListById(orderProductList),
            "OrderProductInfo's productId must not be null."
        );
        assertTrue(exception.getMessage().contains("OrderProductInfo's productId must not be null."));
    }

    @Test
    void testFindProductListByIdWithColorIdNull() {
        orderProductList.getLstOrderProduct().get(0).setColorId(null);
        final DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.findProductListById(orderProductList),
            "OrderProductInfo's colorId must not be null."
        );
        assertTrue(exception.getMessage().contains("OrderProductInfo's colorId must not be null."));
    }
}
