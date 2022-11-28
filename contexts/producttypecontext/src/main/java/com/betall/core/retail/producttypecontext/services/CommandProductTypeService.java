package com.betall.core.retail.producttypecontext.services;

import org.springframework.transaction.annotation.Transactional;

import com.betall.core.retail.producttypecontext.models.ProductTypeInfo;
import com.betall.core.retail.producttypecontext.responses.ProductTypeRepresentation;

import com.betall.core.retail.producttypecontext.repositories.CommandProductTypeRepository;

/**
 * Implementation for command product type repository
 */
public class CommandProductTypeService {
    private CommandProductTypeRepository command;

    public CommandProductTypeService(final CommandProductTypeRepository command) {
        this.command = command;
    }

    /**
     * Save a product type
     * @param productTypeInfo
     *
     * @return ProductTypeRepresentation if successful
     */
    @Transactional
    public ProductTypeRepresentation save(final ProductTypeInfo productTypeInfo) {
        productTypeInfo.validate();
        return command.save(productTypeInfo);
    }

    /**
     * Update a product type
     * @param productTypeInfo
     *
     * @return ProductTypeRepresentation if successful
     */
    @Transactional
    public ProductTypeRepresentation update(final ProductTypeInfo productTypeInfo) {
        productTypeInfo.validate();
        return command.update(productTypeInfo);
    }

    /**
     * Soft delete a product type
     * @param id
     *
     * @return Integer if successful
     */
    @Transactional
    public ProductTypeRepresentation delete(final Long id) {
        return command.delete(id);
    }
}
