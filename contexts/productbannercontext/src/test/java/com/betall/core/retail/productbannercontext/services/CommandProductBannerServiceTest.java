package com.betall.core.retail.productbannercontext.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import com.betall.core.retail.productbannercontext.models.ProductBannerInfo;
import com.betall.core.retail.productbannercontext.ProductBannerContextDataFactory;
import com.betall.core.retail.productbannercontext.responses.ProductBannerRepresentation;

import com.betall.core.retail.productbannercontext.repositories.CommandProductBannerRepository;

import com.betall.core.retail.shared_kernels.exceptions.DataViolationException;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;

class CommandProductBannerServiceTest {
    private CommandProductBannerService service;
    private CommandProductBannerRepository command;

    private ProductBannerInfo productBannerInfo = ProductBannerContextDataFactory.initProductBanner();
    private ProductBannerRepresentation productBannerRepresentation = ProductBannerContextDataFactory.initProductBannerRepresentation();
    private ProductBannerRepresentation productBannerDelete = ProductBannerContextDataFactory.initProductBannerRepresentationDelete();

    @BeforeEach
    void setUp() {
        command = mock(CommandProductBannerRepository.class);
        service = mock(CommandProductBannerService.class, withSettings().useConstructor(command));

        when(service.save(any(ProductBannerInfo.class))).thenCallRealMethod();
        when(command.save(any(ProductBannerInfo.class))).thenReturn(productBannerRepresentation);

        when(service.update(any(ProductBannerInfo.class))).thenCallRealMethod();
        when(command.update(any(ProductBannerInfo.class))).thenReturn(productBannerRepresentation);

        when(service.delete(anyLong())).thenCallRealMethod();
        when(command.delete(anyLong())).thenReturn(productBannerDelete);
    }

    @Test
    void testSave() {
        final ProductBannerRepresentation response = service.save(productBannerInfo);
        assertNotNull(response);
        assertNotNull(response.getData());
        assertNotNull(response.getData().getId());
    }

    @Test
    void testSaveWithImageUrlIsNull() {
        productBannerInfo.setImageUrl(null);

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(productBannerInfo),
            "ProductBannerInfo imageUrl must not be null or empty"
        );

        assertTrue(exception.getMessage().contains("ProductBannerInfo imageUrl must not be null or empty"));
    }

    @Test
    void testSaveWithImageUrlIsEmpty() {
        productBannerInfo.setImageUrl(" ");

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(productBannerInfo),
            "ProductBannerInfo imageUrl must not be null or empty"
        );

        assertTrue(exception.getMessage().contains("ProductBannerInfo imageUrl must not be null or empty"));
    }

    @Test
    void testSaveWithUrlIsNull() {
        productBannerInfo.setUrl(null);

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(productBannerInfo),
            "ProductBannerInfo url must not be null or empty"
        );

        assertTrue(exception.getMessage().contains("ProductBannerInfo url must not be null or empty"));
    }

    @Test
    void testSaveWithUrlIsEmpty() {
        productBannerInfo.setUrl(" ");

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(productBannerInfo),
            "ProductBannerInfo url must not be null or empty"
        );

        assertTrue(exception.getMessage().contains("ProductBannerInfo url must not be null or empty"));
    }

    @Test
    void testUpdate() {
        final ProductBannerRepresentation response = service.update(productBannerInfo);
        assertNotNull(response);
        assertNotNull(response.getData());
        assertNotNull(response.getData().getId());
    }

    @Test
    void testUpdateWithImageUrlIsNull() {
        productBannerInfo.setImageUrl(null);

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.update(productBannerInfo),
            "ProductBannerInfo imageUrl must not be null or empty"
        );

        assertTrue(exception.getMessage().contains("ProductBannerInfo imageUrl must not be null or empty"));
    }

    @Test
    void testUpdateWithUrlIsEmpty() {
        productBannerInfo.setUrl("");

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.update(productBannerInfo),
            "ProductBannerInfo url must not be null or empty"
        );

        assertTrue(exception.getMessage().contains("ProductBannerInfo url must not be null or empty"));
    }

    @Test
    void testDelete() {
        final ProductBannerRepresentation response = service.delete(1L);
        assertNotNull(response);
        assertNotNull(response.getData());
        assertEquals(0, response.getData().getIsActive());
    }
}
