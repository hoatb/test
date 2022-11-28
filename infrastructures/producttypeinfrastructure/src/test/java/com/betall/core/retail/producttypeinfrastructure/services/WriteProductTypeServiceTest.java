package com.betall.core.retail.producttypeinfrastructure.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Optional;

import com.betall.core.retail.producttypecontext.models.ProductTypeInfo;

import com.betall.core.retail.producttypeinfrastructure.models.ProductType;
import com.betall.core.retail.producttypecontext.responses.ProductTypeRepresentation;
import com.betall.core.retail.producttypeinfrastructure.ProductTypeInfrastructureDataFactory;

import com.betall.core.retail.producttypeinfrastructure.repositories.ReadProductTypeRepository;
import com.betall.core.retail.producttypeinfrastructure.repositories.WriteProductTypeRepository;

import com.betall.core.retail.shared_kernels.exceptions.DataViolationException;
import com.betall.core.retail.shared_kernels.exceptions.DatabasePopulateException;
import com.betall.core.retail.shared_kernels.exceptions.ResourceNotFoundException;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WriteProductTypeServiceTest {
    private WriteProductTypeService service;
    private WriteProductTypeRepository command;
    private ReadProductTypeRepository query;

    private ProductTypeInfo productTypeInfo = ProductTypeInfrastructureDataFactory.initProductTypeInfo();
    private ProductType productType = ProductTypeInfrastructureDataFactory.initProductType();

    @BeforeEach
    void setUp() {
        command = mock(WriteProductTypeRepository.class);
        query = mock(ReadProductTypeRepository.class);
        service = mock(WriteProductTypeService.class, withSettings().useConstructor(command, query));

        when(service.save(any(ProductTypeInfo.class))).thenCallRealMethod();
        when(command.save(any(ProductType.class))).thenReturn(productType);

        when(service.update(any(ProductTypeInfo.class))).thenCallRealMethod();
        when(query.findById(anyLong())).thenReturn(Optional.of(productType));

        when(service.delete(anyLong())).thenCallRealMethod();
    }

    @Test
    void testSave() {
        final ProductTypeRepresentation response = service.save(productTypeInfo);
        assertNotNull(response);
        assertNotNull(response.getData());
        assertNotNull(response.getData().getId());
    }

    @Test
    void testSaveWithNameIsNull() {
        productTypeInfo.setName(null);

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(productTypeInfo),
            "ProductTypeInfo name must not be null or empty."
        );

        assertTrue(exception.getMessage().contains("ProductTypeInfo name must not be null or empty."));
    }

    @Test
    void testSaveWithEntityIdIsNull() {
        productType.setId(null);
        productTypeInfo.setId(null);
        when(command.save(any(ProductType.class))).thenReturn(productType);

        DatabasePopulateException exception = assertThrows(
            DatabasePopulateException.class,
            () -> service.save(productTypeInfo),
            "Can't save product type"
        );

        assertTrue(exception.getMessage().contains("Can't save product type"));
    }

    @Test
    void testUpdate() {
        final ProductTypeRepresentation response = service.update(productTypeInfo);
        assertNotNull(response);
        assertNotNull(response.getData());
        assertNotNull(response.getData().getId());
    }

    @Test
    void testUpdateWithIdNotFound() {
        when(query.findById(anyLong())).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
            ResourceNotFoundException.class,
            () -> service.update(productTypeInfo),
            String.format("Can't find product type with id %s", productTypeInfo.getId())
        );

        assertTrue(exception.getMessage().contains("Can't find product type with id"));
    }

    @Test
    void testDelete() {
        final ProductTypeRepresentation response = service.delete(1L);
        assertNotNull(response);
        assertNotNull(response.getData());
        assertNotNull(response.getData().getId());
    }

    @Test
    void testDeleteWithIdNotFound() {
        when(query.findById(anyLong())).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
            ResourceNotFoundException.class,
            () -> service.delete(1L),
            String.format("Can't find product type with id %s", productTypeInfo.getId())
        );

        assertTrue(exception.getMessage().contains("Can't find product type with id"));
    }
}
