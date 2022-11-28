package com.betall.core.retail.haravan_product_context.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import com.betall.core.retail.haravan_product_context.models.HaravanProductInfo;
import com.betall.core.retail.haravan_product_context.HaravanProductContextDataFactory;

import com.betall.core.retail.haravan_product_context.repositories.QueryHaravanProductRepository;

import com.betall.core.retail.shared_kernels.exceptions.ResourceNotFoundException;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class QueryHaravanProductServiceTest {
    private QueryHaravanProductService service;
    private QueryHaravanProductRepository query;

    private List<HaravanProductInfo> haravanProducts = HaravanProductContextDataFactory.initHaravanProducts(5);
    private HaravanProductInfo haravanProduct = HaravanProductContextDataFactory.initHaravanProduct();

    @BeforeEach
    void setUp() {
        query = mock(QueryHaravanProductRepository.class);
        service = mock(QueryHaravanProductService.class, withSettings().useConstructor(query));

        when(service.findAllProducts()).thenCallRealMethod();
        when(query.findAllProducts()).thenReturn(haravanProducts);

        when(service.findProductById(anyLong())).thenCallRealMethod();
        when(query.findProductById(anyLong())).thenReturn(Optional.of(haravanProduct));
    }

    @Test
    void testFindAll() {
        final List<HaravanProductInfo> response = service.findAllProducts();
        assertNotNull(response);
        assertEquals(5, response.size());
    }

    @Test
    void testFindAllNoData() {
        when(query.findAllProducts()).thenReturn(new ArrayList<>());

        final List<HaravanProductInfo> response = service.findAllProducts();
        assertNotNull(response);
        assertEquals(0, response.size());
    }

    @Test
    void testFindByProductId() {
        final HaravanProductInfo response = service.findProductById(5L);
        assertNotNull(response);
    }

    @Test
    void testFindByProductIdNoData() {
        when(query.findProductById(any())).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
            ResourceNotFoundException.class,
            () -> service.findProductById(43L),
            String.format("Not found product with id %s", 43L)
        );

        assertTrue(exception.getMessage().contains("Not found product with id "));
    }

    @Test
    void testFindByProductIdWithProductIdNull() {
        when(service.findProductById(any())).thenCallRealMethod();
        when(query.findProductById(any())).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
            ResourceNotFoundException.class,
            () -> service.findProductById(null),
            String.format("Not found product with id %s", null)
        );

        assertTrue(exception.getMessage().contains("Not found product with id "));
    }
}
