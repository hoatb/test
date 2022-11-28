package com.betall.core.retail.productgroupinfrastructure.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Optional;

import com.betall.core.retail.productgroupcontext.models.ProductGroupInfo;
import com.betall.core.retail.productgroupcontext.responses.ProductGroupRepresentation;

import com.betall.core.retail.productgroupinfrastructure.models.ProductGroup;
import com.betall.core.retail.productgroupinfrastructure.ProductGroupInfrastructureDataFactory;

import com.betall.core.retail.productgroupinfrastructure.repositories.ReadProductGroupRepository;
import com.betall.core.retail.productgroupinfrastructure.repositories.WriteProductGroupRepository;

import com.betall.core.retail.shared_kernels.exceptions.DataViolationException;
import com.betall.core.retail.shared_kernels.exceptions.DatabasePopulateException;
import com.betall.core.retail.shared_kernels.exceptions.ResourceNotFoundException;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class WriteProductGroupServiceTest {
    private WriteProductGroupService service;
    private WriteProductGroupRepository command;
    private ReadProductGroupRepository query;

    private ProductGroupInfo productGroupInfo = ProductGroupInfrastructureDataFactory.initProductGroupInfo();
    private ProductGroup productGroup = ProductGroupInfrastructureDataFactory.initProductGroup();

    @BeforeEach
    void setUp() {
        command = mock(WriteProductGroupRepository.class);
        query = mock(ReadProductGroupRepository.class);
        service = mock(WriteProductGroupService.class, withSettings().useConstructor(command, query));

        when(service.save(any(ProductGroupInfo.class))).thenCallRealMethod();
        when(command.save(any(ProductGroup.class))).thenReturn(productGroup);

        when(service.update(any(ProductGroupInfo.class))).thenCallRealMethod();
        when(query.findById(anyLong())).thenReturn(Optional.of(productGroup));

        when(service.delete(anyLong())).thenCallRealMethod();
    }

    @Test
    void testSave() {
        final ProductGroupRepresentation response = service.save(productGroupInfo);
        assertNotNull(response);
        assertNotNull(response.getData());
        assertNotNull(response.getData().getId());
    }

    @Test
    void testSaveWithNameIsNull() {
        productGroupInfo.setName(null);

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(productGroupInfo),
        "ProductGroupInfo name must not be null or empty."
        );

        assertTrue(exception.getMessage().contains("ProductGroupInfo name must not be null or empty."));
    }

    @Test
    void testSaveWithEntityIdIsNull() {
        productGroup.setId(null);
        productGroupInfo.setId(null);
        when(command.save(any(ProductGroup.class))).thenReturn(productGroup);

        DatabasePopulateException exception = assertThrows(
            DatabasePopulateException.class,
            () -> service.save(productGroupInfo),
            "Can't save product group"
        );

        assertTrue(exception.getMessage().contains("Can't save product group"));
    }

    @Test
    void testUpdate() {
        final ProductGroupRepresentation response = service.update(productGroupInfo);
        assertNotNull(response);
        assertNotNull(response.getData());
        assertNotNull(response.getData().getId());
    }

    @Test
    void testUpdateWithIdNotFound() {
        when(query.findById(anyLong())).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
            ResourceNotFoundException.class,
            () -> service.update(productGroupInfo),
            String.format("Can't find product group with id %s", productGroupInfo.getId())
        );

        assertTrue(exception.getMessage().contains("Can't find product group with id"));
    }

    @Test
    void testDelete() {
        final ProductGroupRepresentation response = service.delete(1L);
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
            String.format("Can't find product group with id %s", productGroupInfo.getId())
        );

        assertTrue(exception.getMessage().contains("Can't find product group with id"));
    }
}
