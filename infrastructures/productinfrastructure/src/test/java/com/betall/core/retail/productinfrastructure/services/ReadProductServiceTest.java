package com.betall.core.retail.productinfrastructure.services;

import com.betall.core.retail.productcontext.models.ProductDetailInfo;
import com.betall.core.retail.productcontext.models.ProductInfo;
import com.betall.core.retail.productcontext.requests.OrderProductList;
import com.betall.core.retail.productinfrastructure.ProductInfrastructureDataFactory;
import com.betall.core.retail.productinfrastructure.models.Product;
import com.betall.core.retail.productinfrastructure.repositories.ReadProductRepository;
import com.betall.core.retail.shared_kernels.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReadProductServiceTest {
    private ReadProductService service;
    private ReadProductRepository query;

    private List<Product> productList = ProductInfrastructureDataFactory.initProductList(5L);
    private Product product = ProductInfrastructureDataFactory.initProduct(1L);
    private OrderProductList orderProductList = ProductInfrastructureDataFactory.initOrderProductList(5L);

    @BeforeEach
    void setUp() {
        query = mock(ReadProductRepository.class);
        service = mock(ReadProductService.class, withSettings().useConstructor(query));

        when(service.findAll(any(Pageable.class))).thenCallRealMethod();
        when(query.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(productList));

        when(service.findById(anyLong())).thenCallRealMethod();
        when(query.findById(anyLong())).thenReturn(Optional.of(product));

        //when(service.findProductListById(any(OrderProductList.class))).thenCallRealMethod();
        when(query.findAllById(any(Iterable.class))).thenReturn(productList);
    }

    @Test
    void testFindAll() {
        final Page<ProductInfo> response = service.findAll(PageRequest.of(0, 10));
        assertNotNull(response);
        assertTrue(response.hasContent());
    }

    @Test
    void testFindAllNoData() {
        when(query.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(new ArrayList<>()));
        final Page<ProductInfo> response = service.findAll(PageRequest.of(0, 10));
        assertNotNull(response);
        assertFalse(response.hasContent());
    }


    @Test
    void testFindById() {
        final Optional<ProductDetailInfo> response = service.findById(1L);
        assertNotNull(response);
        assertTrue(response.isPresent());
    }

    @Test
    void testFindByIdNoData() {
        when(query.findById(anyLong())).thenReturn(Optional.empty());
        ResourceNotFoundException exception = assertThrows(
            ResourceNotFoundException.class,
            () -> service.findById(1L),
            String.format("Not found product with id: %s", 1)
        );
        assertTrue(exception.getMessage().contains("Not found product with id:"));
    }

    @Test
    void testFindByIdWithIdNull(){
        when(service.findById(any())).thenCallRealMethod();
        when(query.findById(any())).thenReturn(Optional.empty());
        ResourceNotFoundException exception = assertThrows(
            ResourceNotFoundException.class,
            () -> service.findById(null),
            String.format("Not found product with id: %s", null)
        );
        assertTrue(exception.getMessage().contains("Not found product with id:"));
    }
}
