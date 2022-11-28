package com.betall.core.retail.productcontext.services;

import com.betall.core.retail.productcontext.models.ProductDetailInfo;
import com.betall.core.retail.productcontext.models.ProductDetailList;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

import com.betall.core.retail.productcontext.responses.ProductDetailRepresentation;

import com.betall.core.retail.productcontext.repositories.CommandProductRepository;

/**
 * Implementation of command ProductDetailInfo Info repository.
 */
public class CommandProductService {
    private CommandProductRepository command;

    public CommandProductService(CommandProductRepository command) {
        this.command = command;
    }

    /**
     * Save a product info
     * @param productDetailInfo
     * @return ProductDetailRepresentation if successful
     */
    @Transactional
    public ProductDetailRepresentation save(ProductDetailInfo productDetailInfo) {
        productDetailInfo.validate();
        return command.save(productDetailInfo);
    }

    /**
     * Save all products info
     * @param productDetailList
     * @return HttpStatus.ok if successful
     */
    @Transactional
    public HttpStatus saveAll(ProductDetailList productDetailList) {
        for(ProductDetailInfo productInfo : productDetailList.getProductsDetail()) {
            productInfo.validate();
        }
        return command.saveAll(productDetailList);
    }

    /**
     * Update a product info
     * @param productDetailInfo
     * @return ProductDetailRepresentation if successful
     */
    @Transactional
    public ProductDetailRepresentation update(ProductDetailInfo productDetailInfo) {
        productDetailInfo.validate();
        return command.update(productDetailInfo);
    }

    /**
     * Soft delete a product info
     * @param id
     * @return Integer if successful
     */
    @Transactional
    public ProductDetailRepresentation delete(Long id) {
        return command.delete(id);
    }

}
