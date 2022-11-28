package com.betall.core.retail.productgroupcontext.services;

import org.springframework.transaction.annotation.Transactional;

import com.betall.core.retail.productgroupcontext.models.ProductGroupInfo;
import com.betall.core.retail.productgroupcontext.responses.ProductGroupRepresentation;

import com.betall.core.retail.productgroupcontext.repositories.CommandProductGroupRepository;

/**
 * Implementation for command product group repository
 */
public class CommandProductGroupService {
    private CommandProductGroupRepository command;

    public CommandProductGroupService(final CommandProductGroupRepository command) {
        this.command = command;
    }

    /**
     * Save a product group
     * @param productGroupInfo
     *
     * @return ProductGroupRepresentation if successful
     */
    @Transactional
    public ProductGroupRepresentation save(final ProductGroupInfo productGroupInfo) {
        productGroupInfo.validate();
        return command.save(productGroupInfo);
    }

    /**
     * Update a product group
     * @param productGroupInfo
     *
     * @return ProductGroupRepresentation if successful
     */
    @Transactional
    public ProductGroupRepresentation update(final ProductGroupInfo productGroupInfo) {
        productGroupInfo.validate();
        return command.update(productGroupInfo);
    }

    /**
     * Soft delete a product group
     * @param id
     *
     * @return Integer if successful
     */
    @Transactional
    public ProductGroupRepresentation delete(final Long id) {
        return command.delete(id);
    }
}
