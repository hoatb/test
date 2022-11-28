package com.betall.core.retail.productgroupcontext.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import com.betall.core.retail.productgroupcontext.models.ProductGroupInfo;
import com.betall.core.retail.productgroupcontext.ProductGroupContextDataFactory;
import com.betall.core.retail.productgroupcontext.responses.ProductGroupRepresentation;

import com.betall.core.retail.productgroupcontext.repositories.CommandProductGroupRepository;

import com.betall.core.retail.shared_kernels.exceptions.DataViolationException;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class CommandProductGroupServiceTest {
    private CommandProductGroupService service;
    private CommandProductGroupRepository command;

    private ProductGroupInfo productGroupInfo = ProductGroupContextDataFactory.initProductGroup();
    private ProductGroupRepresentation productGroupRepresentation = ProductGroupContextDataFactory.initProductGroupRepresentation();
    private ProductGroupRepresentation productGroupDelete = ProductGroupContextDataFactory.initProductGroupRepresentationDelete();

    @BeforeEach
    void setUp() {
        command = mock(CommandProductGroupRepository.class);
        service = mock(CommandProductGroupService.class, withSettings().useConstructor(command));

        when(service.save(any(ProductGroupInfo.class))).thenCallRealMethod();
        when(command.save(any(ProductGroupInfo.class))).thenReturn(productGroupRepresentation);

        when(service.update(any(ProductGroupInfo.class))).thenCallRealMethod();
        when(command.update(any(ProductGroupInfo.class))).thenReturn(productGroupRepresentation);

        when(service.delete(anyLong())).thenCallRealMethod();
        when(command.delete(anyLong())).thenReturn(productGroupDelete);
    }

    @Test
    void testSave() {
        final ProductGroupRepresentation response = service.save(productGroupInfo);
        assertNotNull(response);
        assertNotNull(response.getData());
        assertNotNull(response.getData().getId());
    }

    @Test
    void testSaveWithGroupNameIsNull() {
        productGroupInfo.setName(null);

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(productGroupInfo),
            "ProductGroupInfo name must not be null or empty"
        );

        assertTrue(exception.getMessage().contains("ProductGroupInfo name must not be null or empty"));
    }

    @Test
    void testSaveWithGroupNameIsEmpty() {
        productGroupInfo.setName(" ");

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(productGroupInfo),
            "ProductGroupInfo name must not be null or empty"
        );

        assertTrue(exception.getMessage().contains("ProductGroupInfo name must not be null or empty"));
    }

    @Test
    void testUpdate() {
        final ProductGroupRepresentation response = service.update(productGroupInfo);
        assertNotNull(response);
        assertNotNull(response.getData());
        assertNotNull(response.getData().getId());
    }

    @Test
    void testUpdateWithGroupNameIsNull() {
        productGroupInfo.setName(null);

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.update(productGroupInfo),
            "ProductGroupInfo name must not be null or empty"
        );

        assertTrue(exception.getMessage().contains("ProductGroupInfo name must not be null or empty"));
    }

    @Test
    void testUpdateWithGroupNameIsEmpty() {
        productGroupInfo.setName("");

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(productGroupInfo),
            "ProductGroupInfo name must not be null or empty"
        );

        assertTrue(exception.getMessage().contains("ProductGroupInfo name must not be null or empty"));
    }

    @Test
    void testDelete() {
        final ProductGroupRepresentation response = service.delete(1L);
        assertNotNull(response);
        assertNotNull(response.getData());
        assertEquals(0, response.getData().getIsActive());
    }
}
