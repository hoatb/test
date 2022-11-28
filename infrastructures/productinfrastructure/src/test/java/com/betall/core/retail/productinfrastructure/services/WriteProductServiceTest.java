package com.betall.core.retail.productinfrastructure.services;

import com.betall.core.retail.productcontext.models.ProductDetailInfo;
import com.betall.core.retail.productcontext.models.ProductDetailList;
import com.betall.core.retail.productcontext.responses.ProductDetailRepresentation;
import com.betall.core.retail.productinfrastructure.ProductInfrastructureDataFactory;
import com.betall.core.retail.productinfrastructure.models.Product;
import com.betall.core.retail.productinfrastructure.repositories.ReadProductRepository;
import com.betall.core.retail.productinfrastructure.repositories.WriteProductRepository;
import com.betall.core.retail.shared_kernels.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WriteProductServiceTest {
    private WriteProductService service;
    private WriteProductRepository command;
    private ReadProductRepository query;

    private List<Product> products = ProductInfrastructureDataFactory.initProductList(5L);
    private ProductDetailList productDetailList = ProductInfrastructureDataFactory.initProductDetailList(5L);
    private Product product = ProductInfrastructureDataFactory.initProduct(1L);
    private ProductDetailInfo productDetailInfo = ProductInfrastructureDataFactory.initProductDetailInfo(1L);

    @BeforeEach
    void setUp(){
        command = mock(WriteProductRepository.class);
        query = mock(ReadProductRepository.class);
        service = mock(WriteProductService.class, withSettings().useConstructor(command, query));

        when(service.saveAll(any(ProductDetailList.class))).thenCallRealMethod();
        when(command.saveAll(anyList())).thenReturn(products);

        when(service.save(any(ProductDetailInfo.class))).thenCallRealMethod();
        when(command.save(any())).thenReturn(product);

        when(service.update(any(ProductDetailInfo.class))).thenCallRealMethod();
        when(query.findById(anyLong())).thenReturn(Optional.of(product));

        when(service.delete(anyLong())).thenCallRealMethod();
    }

    @Test
    void testSaveAll() {
        final HttpStatus response = service.saveAll(productDetailList);
        assertNotNull(response);
    }

    @Test
    void testSave() {
        final ProductDetailRepresentation response = service.save(productDetailInfo);
        assertNotNull(response);
        assertNotNull(response.getData());
        assertNotNull(response.getData().getProductDetailInfo());
        assertNotNull(response.getData().getProductDetailInfo().getProductId());
    }

    @Test
    void testUpdate() {
        final ProductDetailRepresentation response = service.update(productDetailInfo);
        assertNotNull(response);
        assertNotNull(response.getData());
        assertNotNull(response.getData().getProductDetailInfo());
    }

    @Test
    void testUpdateWithProductIdNotFound() {
        when(query.findById(anyLong())).thenReturn(Optional.empty());
        ResourceNotFoundException exception = assertThrows(
            ResourceNotFoundException.class,
            () -> service.update(productDetailInfo),
            String.format("Can't find product with id %s", productDetailInfo.getProductId())
        );
        assertTrue(exception.getMessage().contains(String.format("Can't find product with id %s", productDetailInfo.getProductId())));
    }

    @Test
    void testDelete() {
        final ProductDetailRepresentation response = service.delete(1L);
        assertNotNull(response);
        assertNotNull(response.getData());
        assertNotNull(response.getData().getProductDetailInfo());
    }

    @Test
    void testDeleteWithProductIdNotFound() {
        when(query.findById(anyLong())).thenReturn(Optional.empty());
        ResourceNotFoundException exception = assertThrows(
            ResourceNotFoundException.class,
            () -> service.delete(1L),
            String.format("Can't find product with id %s", 1)
        );
        assertTrue(exception.getMessage().contains(String.format("Can't find product with id %s", 1)));
    }

    @Test
    void testDeleteWithProductIdNull() {
        when(service.delete(any())).thenCallRealMethod();
        when(query.findById(any())).thenReturn(Optional.empty());
        ResourceNotFoundException exception = assertThrows(
            ResourceNotFoundException.class,
            () -> service.delete(null),
            String.format("Can't find product with id %s", null)
        );
        assertTrue(exception.getMessage().contains(String.format("Can't find product with id %s", null)));
    }
}
