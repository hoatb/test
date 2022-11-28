package com.betall.core.retail.producttypeinfrastructure.services;

import lombok.extern.slf4j.Slf4j;

import com.betall.core.retail.producttypecontext.models.ProductTypeInfo;
import com.betall.core.retail.producttypecontext.responses.ProductTypeRepresentation;

import com.betall.core.retail.producttypecontext.repositories.CommandProductTypeRepository;

import com.betall.core.retail.producttypeinfrastructure.utils.EntityMapper;
import com.betall.core.retail.producttypeinfrastructure.models.ProductType;

import com.betall.core.retail.producttypeinfrastructure.repositories.ReadProductTypeRepository;
import com.betall.core.retail.producttypeinfrastructure.repositories.WriteProductTypeRepository;

import com.betall.core.retail.shared_kernels.exceptions.DatabasePopulateException;
import com.betall.core.retail.shared_kernels.exceptions.ResourceNotFoundException;

@Slf4j
public class WriteProductTypeService implements CommandProductTypeRepository {
    private WriteProductTypeRepository command;
    private ReadProductTypeRepository query;

    public WriteProductTypeService(final WriteProductTypeRepository command, final ReadProductTypeRepository query) {
        this.command = command;
        this.query = query;
    }

    @Override
    public ProductTypeRepresentation save(final ProductTypeInfo productTypeInfo) {
        productTypeInfo.validate();
        final EntityMapper converter = new EntityMapper();
        final ProductType entity = converter.toProductType(productTypeInfo);
        final ProductType saved = command.save(entity);
        if (saved.getId() != null) {
            return converter.toProductTypeRepresentation(saved);
        }

        log.error("Can't save product type {}", productTypeInfo);
        throw new DatabasePopulateException(String.format("Can't save product type %s", productTypeInfo));
    }

    @Override
    public ProductTypeRepresentation update(final ProductTypeInfo productTypeInfo) {
        final ProductType entity = query.findById(productTypeInfo.getId()).orElseThrow(
            () -> new ResourceNotFoundException(String.format("Can't find product type with id %s", productTypeInfo.getId()))
        );
        final EntityMapper converter = new EntityMapper();
        converter.update(entity, productTypeInfo);

        final ProductType saved = command.save(entity);
        return converter.toProductTypeRepresentation(saved);
    }

    @Override
    public ProductTypeRepresentation delete(final Long id) {
        final ProductType entity = query.findById(id).orElseThrow(
            () -> new ResourceNotFoundException(String.format("Can't find product type with id %s", id))
        );
        entity.setIsActive(0);
        final EntityMapper converter = new EntityMapper();
        final ProductType saved = command.save(entity);
        return converter.toProductTypeRepresentation(saved);
    }
}
