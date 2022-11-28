package com.betall.core.retail.productbannerinfrastructure.services;

import lombok.extern.slf4j.Slf4j;

import com.betall.core.retail.productbannercontext.models.ProductBannerInfo;
import com.betall.core.retail.productbannercontext.responses.ProductBannerRepresentation;

import com.betall.core.retail.productbannerinfrastructure.utils.EntityMapper;
import com.betall.core.retail.productbannerinfrastructure.models.ProductBanner;

import com.betall.core.retail.productbannercontext.repositories.CommandProductBannerRepository;

import com.betall.core.retail.productbannerinfrastructure.repositories.ReadProductBannerRepository;
import com.betall.core.retail.productbannerinfrastructure.repositories.WriteProductBannerRepository;

import com.betall.core.retail.shared_kernels.exceptions.DatabasePopulateException;
import com.betall.core.retail.shared_kernels.exceptions.ResourceNotFoundException;

@Slf4j
public class WriteProductBannerService implements CommandProductBannerRepository {
    private WriteProductBannerRepository command;
    private ReadProductBannerRepository query;

    public WriteProductBannerService(final WriteProductBannerRepository command, final ReadProductBannerRepository query) {
        this.command = command;
        this.query = query;
    }

    @Override
    public ProductBannerRepresentation save(final ProductBannerInfo productBannerInfo) {
        productBannerInfo.validate();
        final EntityMapper converter = new EntityMapper();
        final ProductBanner entity = converter.toProductBanner(productBannerInfo);
        final ProductBanner saved = command.save(entity);
        if (saved.getId() != null) {
            return converter.toProductBannerRepresentation(saved);
        }

        log.error("Can't save product banner {}", productBannerInfo);
        throw new DatabasePopulateException(String.format("Can't save product banner %s", productBannerInfo));
    }

    @Override
    public ProductBannerRepresentation update(final ProductBannerInfo productBannerInfo) {
        final ProductBanner entity = query.findById(productBannerInfo.getId()).orElseThrow(
            () -> new ResourceNotFoundException(String.format("Can't find product banner with id %s", productBannerInfo.getId()))
        );
        final EntityMapper converter = new EntityMapper();
        converter.update(entity, productBannerInfo);

        final ProductBanner saved = command.save(entity);
        return converter.toProductBannerRepresentation(saved);
    }

    @Override
    public ProductBannerRepresentation delete(final Long id) {
        final ProductBanner entity = query.findById(id).orElseThrow(
            () -> new ResourceNotFoundException(String.format("Can't find product banner with id %s", id))
        );
        entity.setIsActive(0);
        final EntityMapper converter = new EntityMapper();
        final ProductBanner saved = command.save(entity);
        return converter.toProductBannerRepresentation(saved);
    }
}
