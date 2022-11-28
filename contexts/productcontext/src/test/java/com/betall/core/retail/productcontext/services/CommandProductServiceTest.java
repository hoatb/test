package com.betall.core.retail.productcontext.services;

import com.betall.core.retail.productcontext.ProductContextDataFactory;
import com.betall.core.retail.productcontext.models.ProductDetailInfo;
import com.betall.core.retail.productcontext.models.ProductDetailList;
import com.betall.core.retail.productcontext.repositories.CommandProductRepository;
import com.betall.core.retail.productcontext.responses.ProductDetailRepresentation;
import com.betall.core.retail.shared_kernels.exceptions.DataViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CommandProductServiceTest {
    private CommandProductService service;
    private CommandProductRepository command;

    private ProductDetailInfo productDetailInfo = ProductContextDataFactory.initProductDetailInfo(1L);
    private ProductDetailRepresentation productDetailRepresentation = ProductContextDataFactory.initProductDetailRepresentation();
    private ProductDetailRepresentation productDetailRepresentationDelete = ProductContextDataFactory.initProductDetailRepresentationDelete();
    private ProductDetailList productDetailList = ProductContextDataFactory.initProductDetailList(5L);

    @BeforeEach
    void setUp() {
        command = mock(CommandProductRepository.class);
        service = mock(CommandProductService.class, withSettings().useConstructor(command));

        when(service.save(any(ProductDetailInfo.class))).thenCallRealMethod();
        when(command.save(any(ProductDetailInfo.class))).thenReturn(productDetailRepresentation);

        when(service.saveAll(any(ProductDetailList.class))).thenCallRealMethod();
        when(command.saveAll(any(ProductDetailList.class))).thenReturn(HttpStatus.OK);

        when(service.update(any(ProductDetailInfo.class))).thenCallRealMethod();
        when(command.update(any(ProductDetailInfo.class))).thenReturn(productDetailRepresentation);

        when(service.delete(anyLong())).thenCallRealMethod();
        when(command.delete(anyLong())).thenReturn(productDetailRepresentationDelete);
    }


    @Test
    void testSaveAll() {
        final HttpStatus response = service.saveAll(productDetailList);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response);
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
    void testSaveWithProductIdNull() {
        productDetailInfo.setProductId(null);
        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(productDetailInfo),
            "Product's productId must not be null"
        );
        assertTrue(exception.getMessage().contains("Product's productId must not be null"));
    }

    @Test
    void testSaveWithProductNameNull() {
        productDetailInfo.setProductName(null);
        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(productDetailInfo),
            "Product's productName must not be null"
        );
        assertTrue(exception.getMessage().contains("Product's productName must not be null"));
    }

    @Test
    void testSaveWithProductNameEmpty() {
        productDetailInfo.setProductName("");
        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(productDetailInfo),
            "Product's productName must not be null or empty"
        );
        assertTrue(exception.getMessage().contains("Product's productName must not be null or empty"));
    }

    @Test
    void testSaveWithLstProductColorNull() {
        productDetailInfo.setLstProductColor(null);
        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(productDetailInfo),
            "ProductDetailInfo's lstProductColor must not be null"
        );
        assertTrue(exception.getMessage().contains("ProductDetailInfo's lstProductColor must not be null"));
    }

    @Test
    void testSaveWithLstProductColorEmpty() {
        productDetailInfo.setLstProductColor(new ArrayList<>());
        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(productDetailInfo),
            "ProductDetailInfo's lstProductColor must not be null or empty"
        );
        assertTrue(exception.getMessage().contains("ProductDetailInfo's lstProductColor must not be null or empty"));
    }

    @Test
    void testSaveWithLstProductColorHasIdNull() {
        productDetailInfo.getLstProductColor().get(0).setId(null);
        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(productDetailInfo),
            "ProductColor's id must not be null"
        );
        assertTrue(exception.getMessage().contains("ProductColorInfo's id must not be null"));
    }

    @Test
    void testSaveWithLstProductColorHasProductIdNull() {
        productDetailInfo.getLstProductColor().get(0).setProductId(null);
        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(productDetailInfo),
            "ProductColor's productId must not be null"
        );
        assertTrue(exception.getMessage().contains("ProductColorInfo's productId must not be null"));
    }

    @Test
    void testSaveWithLstProductColorHasColorNameNull() {
        productDetailInfo.getLstProductColor().get(0).setColorName(null);
        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(productDetailInfo),
            "ProductColor's colorName must not be null"
        );
        assertTrue(exception.getMessage().contains("ProductColorInfo's colorName must not be null"));
    }

    @Test
    void testSaveWithLstProductColorHasColorNameEmpty() {
        productDetailInfo.getLstProductColor().get(0).setColorName("");
        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(productDetailInfo),
            "ProductColor's colorName must not be null or empty"
        );
        assertTrue(exception.getMessage().contains("ProductColorInfo's colorName must not be null or empty"));
    }

    @Test
    void testSaveWithLstProductAdvertisementHasIdNull() {
        productDetailInfo.getLstProductAdvertisement().get(0).setId(null);
        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(productDetailInfo),
            "ProductAdvertisementInfo's id must not be null"
        );
        assertTrue(exception.getMessage().contains("ProductAdvertisementInfo's id must not be null"));
    }

    @Test
    void testSaveWithLstProductAdvertisementHasProductIdNull() {
        productDetailInfo.getLstProductAdvertisement().get(0).setProductId(null);
        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(productDetailInfo),
            "ProductAdvertisementInfo's productId must not be null"
        );
        assertTrue(exception.getMessage().contains("ProductAdvertisementInfo's productId must not be null"));
    }

    @Test
    void testSaveWithLstProductAdvertisementHasTitleNull() {
        productDetailInfo.getLstProductAdvertisement().get(0).setTitle(null);
        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(productDetailInfo),
            "ProductAdvertisementInfo's title must not be null"
        );
        assertTrue(exception.getMessage().contains("ProductAdvertisementInfo's title must not be null"));
    }

    @Test
    void testSaveWithLstProductAdvertisementHasTitleEmpty() {
        productDetailInfo.getLstProductAdvertisement().get(0).setTitle("");
        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(productDetailInfo),
            "ProductAdvertisementInfo's title must not be null or empty"
        );
        assertTrue(exception.getMessage().contains("ProductAdvertisementInfo's title must not be null or empty"));
    }

    @Test
    void testSaveWithLstProductAdvertisementHasContentNull() {
        productDetailInfo.getLstProductAdvertisement().get(0).setContent(null);
        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(productDetailInfo),
            "ProductAdvertisementInfo's content must not be null"
        );
        assertTrue(exception.getMessage().contains("ProductAdvertisementInfo's content must not be null"));
    }

    @Test
    void testSaveWithLstProductAdvertisementHasContentEmpty() {
        productDetailInfo.getLstProductAdvertisement().get(0).setContent("");
        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(productDetailInfo),
            "ProductAdvertisementInfo's content must not be null or empty"
        );
        assertTrue(exception.getMessage().contains("ProductAdvertisementInfo's content must not be null or empty"));
    }

    @Test
    void testSaveWithLstProductSpecificationHasIdNull() {
        productDetailInfo.getLstProductSpecification().get(0).setId(null);
        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(productDetailInfo),
            "ProductSpecificationInfo's id must not be null"
        );
        assertTrue(exception.getMessage().contains("ProductSpecificationInfo's id must not be null"));
    }

    @Test
    void testSaveWithLstProductSpecificationHasProductIdNull() {
        productDetailInfo.getLstProductSpecification().get(0).setProductId(null);
        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(productDetailInfo),
            "ProductSpecificationInfo's productId must not be null"
        );
        assertTrue(exception.getMessage().contains("ProductSpecificationInfo's productId must not be null"));
    }

    @Test
    void testSaveWithLstProductSpecificationHasTitleNull() {
        productDetailInfo.getLstProductSpecification().get(0).setTitle(null);
        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(productDetailInfo),
            "ProductSpecificationInfo's title must not be null"
        );
        assertTrue(exception.getMessage().contains("ProductSpecificationInfo's title must not be null"));
    }

    @Test
    void testSaveWithLstProductSpecificationHasTitleEmpty() {
        productDetailInfo.getLstProductSpecification().get(0).setTitle("");
        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(productDetailInfo),
            "ProductSpecificationInfo's title must not be null or empty"
        );
        assertTrue(exception.getMessage().contains("ProductSpecificationInfo's title must not be null or empty"));
    }

    @Test
    void testSaveWithLstProductSpecificationHasContentNull() {
        productDetailInfo.getLstProductSpecification().get(0).setContent(null);
        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(productDetailInfo),
            "ProductSpecificationInfo's content must not be null"
        );
        assertTrue(exception.getMessage().contains("ProductSpecificationInfo's content must not be null"));
    }

    @Test
    void testSaveWithLstProductSpecificationHasContentEmpty() {
        productDetailInfo.getLstProductSpecification().get(0).setContent("");
        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(productDetailInfo),
            "ProductSpecificationInfo's content must not be null or empty"
        );
        assertTrue(exception.getMessage().contains("ProductSpecificationInfo's content must not be null or empty"));
    }

    @Test
    void testSaveWithImagesHasIdNull() {
        productDetailInfo.getImages().get(0).setId(null);
        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(productDetailInfo),
            "ProductImageInfo's id must not be null."
        );
        assertTrue(exception.getMessage().contains("ProductImageInfo's id must not be null."));
    }

    @Test
    void testSaveWithImagesHasProductIdNull() {
        productDetailInfo.getImages().get(0).setProductId(null);
        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(productDetailInfo),
            "ProductImageInfo's productId must not be null."
        );
        assertTrue(exception.getMessage().contains("ProductImageInfo's productId must not be null."));
    }

    @Test
    void testSaveWithImagesHasSrcNull() {
        productDetailInfo.getImages().get(0).setSrc(null);
        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(productDetailInfo),
            "ProductImageInfo's src must not be null"
        );
        assertTrue(exception.getMessage().contains("ProductImageInfo's src must not be null"));
    }

    @Test
    void testSaveWithImageHasSrcEmpty() {
        productDetailInfo.getImages().get(0).setSrc("");
        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.save(productDetailInfo),
            "ProductImageInfo's src must not be null or empty."
        );
        assertTrue(exception.getMessage().contains("ProductImageInfo's src must not be null or empty."));
    }

    @Test
    void testUpdate() {
        final ProductDetailRepresentation response = service.update(productDetailInfo);
        assertNotNull(response);
        assertNotNull(response.getData());
        assertNotNull(response.getData().getProductDetailInfo());
        assertNotNull(response.getData().getProductDetailInfo().getProductId());
    }

    @Test
    void testUpdateWithProductIdNull() {
        productDetailInfo.setProductId(null);
        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.update(productDetailInfo),
            "Product's productId must not be null"
        );
        assertTrue(exception.getMessage().contains("Product's productId must not be null"));
    }

    @Test
    void testUpdateWithProductNameNull() {
        productDetailInfo.setProductName(null);
        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.update(productDetailInfo),
            "Product's productName must not be null"
        );
        assertTrue(exception.getMessage().contains("Product's productName must not be null"));
    }

    @Test
    void testUpdateWithProductNameEmpty() {
        productDetailInfo.setProductName("");
        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.update(productDetailInfo),
            "Product's productName must not be null or empty"
        );
        assertTrue(exception.getMessage().contains("Product's productName must not be null or empty"));
    }

    @Test
    void testUpdateWithLstProductColorNull() {
        productDetailInfo.setLstProductColor(null);
        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.update(productDetailInfo),
            "ProductDetailInfo's lstProductColor must not be null"
        );
        assertTrue(exception.getMessage().contains("ProductDetailInfo's lstProductColor must not be null"));
    }

    @Test
    void testUpdateWithLstProductColorEmpty() {
        productDetailInfo.setLstProductColor(new ArrayList<>());
        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.update(productDetailInfo),
            "ProductDetailInfo's lstProductColor must not be null or empty"
        );
        assertTrue(exception.getMessage().contains("ProductDetailInfo's lstProductColor must not be null or empty"));
    }

    @Test
    void testUpdateWithLstProductColorHasIdNull() {
        productDetailInfo.getLstProductColor().get(0).setId(null);
        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.update(productDetailInfo),
            "ProductColorInfo's id must not be null"
        );
        assertTrue(exception.getMessage().contains("ProductColorInfo's id must not be null"));
    }

    @Test
    void testUpdateWithLstProductColorHasProductIdNull() {
        productDetailInfo.getLstProductColor().get(0).setProductId(null);
        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.update(productDetailInfo),
            "ProductColorInfo's productId must not be null"
        );
        assertTrue(exception.getMessage().contains("ProductColorInfo's productId must not be null"));
    }

    @Test
    void testUpdateWithLstProductColorHasColorNameNull() {
        productDetailInfo.getLstProductColor().get(0).setColorName(null);
        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.update(productDetailInfo),
            "ProductColorInfo's colorName must not be null"
        );
        assertTrue(exception.getMessage().contains("ProductColorInfo's colorName must not be null"));
    }

    @Test
    void testUpdateWithLstProductColorHasColorNameEmpty() {
        productDetailInfo.getLstProductColor().get(0).setColorName("");
        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.update(productDetailInfo),
            "ProductColorInfo's colorName must not be null or empty"
        );
        assertTrue(exception.getMessage().contains("ProductColorInfo's colorName must not be null or empty"));
    }

    @Test
    void testUpdateWithLstProductAdvertisementHasIdNull() {
        productDetailInfo.getLstProductAdvertisement().get(0).setId(null);
        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.update(productDetailInfo),
            "ProductAdvertisementInfo's id must not be null"
        );
        assertTrue(exception.getMessage().contains("ProductAdvertisementInfo's id must not be null"));
    }

    @Test
    void testUpdateWithLstProductAdvertisementHasProductIdNull() {
        productDetailInfo.getLstProductAdvertisement().get(0).setProductId(null);
        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.update(productDetailInfo),
            "ProductAdvertisementInfo's productId must not be null"
        );
        assertTrue(exception.getMessage().contains("ProductAdvertisementInfo's productId must not be null"));
    }

    @Test
    void testUpdateWithLstProductAdvertisementHasTitleNull() {
        productDetailInfo.getLstProductAdvertisement().get(0).setTitle(null);
        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.update(productDetailInfo),
            "ProductAdvertisementInfo's title must not be null"
        );
        assertTrue(exception.getMessage().contains("ProductAdvertisementInfo's title must not be null"));
    }

    @Test
    void testUpdateWithLstProductAdvertisementHasTitleEmpty() {
        productDetailInfo.getLstProductAdvertisement().get(0).setTitle("");
        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.update(productDetailInfo),
            "ProductAdvertisementInfo's title must not be null or empty"
        );
        assertTrue(exception.getMessage().contains("ProductAdvertisementInfo's title must not be null or empty"));
    }

    @Test
    void testUpdateWithLstProductAdvertisementHasContentNull() {
        productDetailInfo.getLstProductAdvertisement().get(0).setContent(null);
        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.update(productDetailInfo),
            "ProductAdvertisementInfo's content must not be null"
        );
        assertTrue(exception.getMessage().contains("ProductAdvertisementInfo's content must not be null"));
    }

    @Test
    void testUpdateWithLstProductAdvertisementHasContentEmpty() {
        productDetailInfo.getLstProductAdvertisement().get(0).setContent("");
        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.update(productDetailInfo),
            "ProductAdvertisementInfo's content must not be null or empty"
        );
        assertTrue(exception.getMessage().contains("ProductAdvertisementInfo's content must not be null or empty"));
    }

    @Test
    void testUpdateWithLstProductSpecificationHasIdNull() {
        productDetailInfo.getLstProductSpecification().get(0).setId(null);
        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.update(productDetailInfo),
            "ProductSpecificationInfo's id must not be null"
        );
        assertTrue(exception.getMessage().contains("ProductSpecificationInfo's id must not be null"));
    }

    @Test
    void testUpdateWithLstProductSpecificationHasProductIdNull() {
        productDetailInfo.getLstProductSpecification().get(0).setProductId(null);
        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.update(productDetailInfo),
            "ProductSpecificationInfo's productId must not be null"
        );
        assertTrue(exception.getMessage().contains("ProductSpecificationInfo's productId must not be null"));
    }

    @Test
    void testUpdateWithLstProductSpecificationHasTitleNull() {
        productDetailInfo.getLstProductSpecification().get(0).setTitle(null);
        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.update(productDetailInfo),
            "ProductSpecificationInfo's title must not be null"
        );
        assertTrue(exception.getMessage().contains("ProductSpecificationInfo's title must not be null"));
    }

    @Test
    void testUpdateWithLstProductSpecificationHasTitleEmpty() {
        productDetailInfo.getLstProductSpecification().get(0).setTitle("");
        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.update(productDetailInfo),
            "ProductSpecificationInfo's title must not be null or empty"
        );
        assertTrue(exception.getMessage().contains("ProductSpecificationInfo's title must not be null or empty"));
    }

    @Test
    void testUpdateWithLstProductSpecificationHasContentNull() {
        productDetailInfo.getLstProductSpecification().get(0).setContent(null);
        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.update(productDetailInfo),
            "ProductSpecificationInfo's content must not be null"
        );
        assertTrue(exception.getMessage().contains("ProductSpecificationInfo's content must not be null"));
    }

    @Test
    void testUpdateWithLstProductSpecificationHasContentEmpty() {
        productDetailInfo.getLstProductSpecification().get(0).setContent("");
        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.update(productDetailInfo),
            "ProductSpecificationInfo's content must not be null or empty"
        );
        assertTrue(exception.getMessage().contains("ProductSpecificationInfo's content must not be null or empty"));
    }

    @Test
    void testUpdateWithImagesHasIdNull() {
        productDetailInfo.getImages().get(0).setId(null);
        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.update(productDetailInfo),
            "ProductImageInfo's id must not be null."
        );
        assertTrue(exception.getMessage().contains("ProductImageInfo's id must not be null."));
    }

    @Test
    void testUpdateWithImagesHasProductIdNull() {
        productDetailInfo.getImages().get(0).setProductId(null);
        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.update(productDetailInfo),
            "ProductImageInfo's productId must not be null."
        );
        assertTrue(exception.getMessage().contains("ProductImageInfo's productId must not be null."));
    }

    @Test
    void testUpdateWithImagesHasSrcNull() {
        productDetailInfo.getImages().get(0).setSrc(null);
        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.update(productDetailInfo),
            "ProductImageInfo's src must not be null"
        );
        assertTrue(exception.getMessage().contains("ProductImageInfo's src must not be null"));
    }

    @Test
    void testUpdateWithImageHasSrcEmpty() {
        productDetailInfo.getImages().get(0).setSrc("");
        DataViolationException exception = assertThrows(
            DataViolationException.class,
            () -> service.update(productDetailInfo),
            "ProductImageInfo's src must not be null or empty."
        );
        assertTrue(exception.getMessage().contains("ProductImageInfo's src must not be null or empty."));
    }

    @Test
    void testDelete(){
        final ProductDetailRepresentation response = service.delete(1L);
        assertNotNull(response);
        assertNotNull(response.getData());
        assertNotNull(response.getData().getProductDetailInfo());
        assertFalse(response.getData().getProductDetailInfo().getIsActive());
    }
}
