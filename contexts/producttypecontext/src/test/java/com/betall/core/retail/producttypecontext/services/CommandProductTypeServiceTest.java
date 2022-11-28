package com.betall.core.retail.producttypecontext.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import com.betall.core.retail.producttypecontext.models.ProductTypeInfo;
import com.betall.core.retail.producttypecontext.ProductTypeContextDataFactory;
import com.betall.core.retail.producttypecontext.responses.ProductTypeRepresentation;

import com.betall.core.retail.producttypecontext.repositories.CommandProductTypeRepository;

import com.betall.core.retail.shared_kernels.exceptions.DataViolationException;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;

class CommandProductTypeServiceTest {
    private CommandProductTypeService service;
    private CommandProductTypeRepository command;

    private ProductTypeInfo productTypeInfo = ProductTypeContextDataFactory.initProductType();
    private ProductTypeRepresentation productTypeRepresentation = ProductTypeContextDataFactory.initProductTypeRepresentation();
    private ProductTypeRepresentation productTypeDelete = ProductTypeContextDataFactory.initProductTypeRepresentationDelete();

    @BeforeEach
    void setUp() {
        command = mock(CommandProductTypeRepository.class);
        service = mock(CommandProductTypeService.class, withSettings().useConstructor(command));

        when(service.save(any(ProductTypeInfo.class))).thenCallRealMethod();
        when(command.save(any(ProductTypeInfo.class))).thenReturn(productTypeRepresentation);

        when(service.update(any(ProductTypeInfo.class))).thenCallRealMethod();
        when(command.update(any(ProductTypeInfo.class))).thenReturn(productTypeRepresentation);

        when(service.delete(anyLong())).thenCallRealMethod();
        when(command.delete(anyLong())).thenReturn(productTypeDelete);
    }

    @Test
    void testSave() {
        final ProductTypeRepresentation response = service.save(productTypeInfo);
        assertNotNull(response);
        assertNotNull(response.getData());
        assertNotNull(response.getData().getId());
    }

    @Test
    void testSaveWithTypeNameIsNull() {
        productTypeInfo.setName(null);

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(productTypeInfo),
            "ProductTypeInfo name must not be null or empty"
        );

        assertTrue(exception.getMessage().contains("ProductTypeInfo name must not be null or empty"));
    }

    @Test
    void testSaveWithTypeNameIsEmpty() {
        productTypeInfo.setName(" ");

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(productTypeInfo),
            "ProductTypeInfo name must not be null or empty"
        );

        assertTrue(exception.getMessage().contains("ProductTypeInfo name must not be null or empty"));
    }

    @Test
    void testUpdate() {
        final ProductTypeRepresentation response = service.update(productTypeInfo);
        assertNotNull(response);
        assertNotNull(response.getData());
        assertNotNull(response.getData().getId());
    }

    @Test
    void testUpdateWithTypeNameIsNull() {
        productTypeInfo.setName(null);

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.update(productTypeInfo),
            "ProductTypeInfo name must not be null or empty"
        );

        assertTrue(exception.getMessage().contains("ProductTypeInfo name must not be null or empty"));
    }

    @Test
    void testUpdateWithTypeNameIsEmpty() {
        productTypeInfo.setName("");

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.update(productTypeInfo),
            "ProductTypeInfo name must not be null or empty"
        );

        assertTrue(exception.getMessage().contains("ProductTypeInfo name must not be null or empty"));
    }

    @Test
    void testDelete() {
        final ProductTypeRepresentation response = service.delete(1L);
        assertNotNull(response);
        assertNotNull(response.getData());
        assertEquals(0, response.getData().getIsActive());
    }
}
