package com.betall.core.retail.productbannerinfrastructure.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Optional;

import com.betall.core.retail.productbannercontext.models.ProductBannerInfo;
import com.betall.core.retail.productbannercontext.responses.ProductBannerRepresentation;

import com.betall.core.retail.productbannerinfrastructure.models.ProductBanner;
import com.betall.core.retail.productbannerinfrastructure.ProductBannerInfrastructureDataFactory;

import com.betall.core.retail.productbannerinfrastructure.repositories.ReadProductBannerRepository;
import com.betall.core.retail.productbannerinfrastructure.repositories.WriteProductBannerRepository;

import com.betall.core.retail.shared_kernels.exceptions.DataViolationException;
import com.betall.core.retail.shared_kernels.exceptions.DatabasePopulateException;
import com.betall.core.retail.shared_kernels.exceptions.ResourceNotFoundException;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WriteProductBannerServiceTest {
    private WriteProductBannerService service;
    private WriteProductBannerRepository command;
    private ReadProductBannerRepository query;

    private ProductBannerInfo productBannerInfo = ProductBannerInfrastructureDataFactory.initProductBannerInfo();
    private ProductBanner productBanner = ProductBannerInfrastructureDataFactory.initProductBanner();

    @BeforeEach
    void setUp() {
        command = mock(WriteProductBannerRepository.class);
        query = mock(ReadProductBannerRepository.class);
        service = mock(WriteProductBannerService.class, withSettings().useConstructor(command, query));

        when(service.save(any(ProductBannerInfo.class))).thenCallRealMethod();
        when(command.save(any(ProductBanner.class))).thenReturn(productBanner);

        when(service.update(any(ProductBannerInfo.class))).thenCallRealMethod();
        when(query.findById(anyLong())).thenReturn(Optional.of(productBanner));

        when(service.delete(anyLong())).thenCallRealMethod();
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
            "ProductBannerInfo imageUrl must not be null or empty."
        );

        assertTrue(exception.getMessage().contains("ProductBannerInfo imageUrl must not be null or empty."));
    }

    @Test
    void testSaveWithImageUrlIsEmpty() {
        productBannerInfo.setImageUrl("");

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(productBannerInfo),
            "ProductBannerInfo imageUrl must not be null or empty."
        );

        assertTrue(exception.getMessage().contains("ProductBannerInfo imageUrl must not be null or empty."));
    }

    @Test
    void testSaveWithUrlIsNull() {
        productBannerInfo.setUrl(null);

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(productBannerInfo),
            "ProductBannerInfo url must not be null or empty."
        );

        assertTrue(exception.getMessage().contains("ProductBannerInfo url must not be null or empty."));
    }

    @Test
    void testSaveWithUrlIsEmpty() {
        productBannerInfo.setUrl("");

        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(productBannerInfo),
            "ProductBannerInfo url must not be null or empty."
        );

        assertTrue(exception.getMessage().contains("ProductBannerInfo url must not be null or empty."));
    }

    @Test
    void testSaveWithEntityIdIsNull() {
        productBanner.setId(null);
        productBannerInfo.setId(null);
        when(command.save(any(ProductBanner.class))).thenReturn(productBanner);

        DatabasePopulateException exception = assertThrows(
            DatabasePopulateException.class,
            () -> service.save(productBannerInfo),
            "Can't save product banner"
        );

        assertTrue(exception.getMessage().contains("Can't save product banner"));
    }

    @Test
    void testUpdate() {
        final ProductBannerRepresentation response = service.update(productBannerInfo);
        assertNotNull(response);
        assertNotNull(response.getData());
        assertNotNull(response.getData().getId());
    }

    @Test
    void testUpdateWithIdNotFound() {
        when(query.findById(anyLong())).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
            ResourceNotFoundException.class,
            () -> service.update(productBannerInfo),
            String.format("Can't find product banner with id %s", productBannerInfo.getId())
        );

        assertTrue(exception.getMessage().contains("Can't find product banner with id"));
    }

    @Test
    void testDelete() {
        final ProductBannerRepresentation response = service.delete(1L);
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
            String.format("Can't find product banner with id %s", productBannerInfo.getId())
        );

        assertTrue(exception.getMessage().contains("Can't find product banner with id"));
    }
}
