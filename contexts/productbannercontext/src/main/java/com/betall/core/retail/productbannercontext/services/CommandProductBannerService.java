package com.betall.core.retail.productbannercontext.services;

import org.springframework.transaction.annotation.Transactional;

import com.betall.core.retail.productbannercontext.models.ProductBannerInfo;
import com.betall.core.retail.productbannercontext.responses.ProductBannerRepresentation;

import com.betall.core.retail.productbannercontext.repositories.CommandProductBannerRepository;

/**
 * Implementation for command product banner repository
 */
public class CommandProductBannerService {
    private CommandProductBannerRepository command;

    public CommandProductBannerService(final CommandProductBannerRepository command) {
        this.command = command;
    }

    /**
     * Save a product banner
     * @param productBannerInfo
     *
     * @return ProductBannerRepresentation if successful
     */
    @Transactional
    public ProductBannerRepresentation save(final ProductBannerInfo productBannerInfo) {
        productBannerInfo.validate();
        return command.save(productBannerInfo);
    }

    /**
     * Update a product banner
     * @param productBannerInfo
     *
     * @return ProductBannerRepresentation if successful
     */
    @Transactional
    public ProductBannerRepresentation update(final ProductBannerInfo productBannerInfo) {
        productBannerInfo.validate();
        return command.update(productBannerInfo);
    }

    /**
     * Soft delete a product banner
     * @param id
     *
     * @return Integer if successful
     */
    @Transactional
    public ProductBannerRepresentation delete(final Long id) {
        return command.delete(id);
    }
}
