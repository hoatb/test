package com.betall.core.retail.productgroupinfrastructure.services;

import lombok.extern.slf4j.Slf4j;

import com.betall.core.retail.productgroupcontext.models.ProductGroupInfo;
import com.betall.core.retail.productgroupinfrastructure.utils.EntityMapper;
import com.betall.core.retail.productgroupinfrastructure.models.ProductGroup;
import com.betall.core.retail.productgroupcontext.responses.ProductGroupRepresentation;

import com.betall.core.retail.productgroupcontext.repositories.CommandProductGroupRepository;
import com.betall.core.retail.productgroupinfrastructure.repositories.ReadProductGroupRepository;
import com.betall.core.retail.productgroupinfrastructure.repositories.WriteProductGroupRepository;

import com.betall.core.retail.shared_kernels.exceptions.DatabasePopulateException;
import com.betall.core.retail.shared_kernels.exceptions.ResourceNotFoundException;

@Slf4j
public class WriteProductGroupService implements CommandProductGroupRepository {
    private WriteProductGroupRepository command;
    private ReadProductGroupRepository query;

    public WriteProductGroupService(final WriteProductGroupRepository command, final ReadProductGroupRepository query) {
        this.command = command;
        this.query = query;
    }

    @Override
    public ProductGroupRepresentation save(final ProductGroupInfo productGroupInfo) {
        productGroupInfo.validate();
        final EntityMapper converter = new EntityMapper();
        final ProductGroup entity = converter.toProductGroup(productGroupInfo);
        final ProductGroup saved = command.save(entity);
        if (saved.getId() != null) {
            return converter.toProductGroupRepresentation(saved);
        }

        log.error("Can't save product group {}", productGroupInfo);
        throw new DatabasePopulateException(String.format("Can't save product group %s", productGroupInfo));
    }

    @Override
    public ProductGroupRepresentation update(final ProductGroupInfo productGroupInfo) {
        final ProductGroup entity = query.findById(productGroupInfo.getId()).orElseThrow(
            () -> new ResourceNotFoundException(String.format("Can't find product group with id %s", productGroupInfo.getId()))
        );
        final EntityMapper converter = new EntityMapper();
        converter.update(entity, productGroupInfo);

        final ProductGroup saved = command.save(entity);
        return converter.toProductGroupRepresentation(saved);
    }

    @Override
    public ProductGroupRepresentation delete(final Long id) {
        final ProductGroup entity = query.findById(id).orElseThrow(
            () -> new ResourceNotFoundException(String.format("Can't find product group with id %s", id))
        );
        entity.setIsActive(0);
        final EntityMapper converter = new EntityMapper();
        final ProductGroup saved = command.save(entity);
        return converter.toProductGroupRepresentation(saved);
    }
}
