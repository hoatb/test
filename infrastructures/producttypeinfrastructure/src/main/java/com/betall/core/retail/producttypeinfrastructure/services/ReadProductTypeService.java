package com.betall.core.retail.producttypeinfrastructure.services;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.betall.core.retail.producttypecontext.models.ProductTypeInfo;

import com.betall.core.retail.producttypeinfrastructure.models.ProductType;

import com.betall.core.retail.producttypecontext.repositories.QueryProductTypeRepository;
import com.betall.core.retail.producttypeinfrastructure.repositories.ReadProductTypeRepository;

import com.betall.core.retail.shared_kernels.exceptions.ResourceNotFoundException;

public class ReadProductTypeService implements QueryProductTypeRepository {
    private ReadProductTypeRepository query;

    public ReadProductTypeService(final ReadProductTypeRepository query) {
        this.query = query;
    }

    /**
     * Convert list ProductType entity to ProductTypeInfo with page
     * @param productTypes
     *
     * @return Page<ProductTypeInfo>
     */
    private Page<ProductTypeInfo> processResponses(final Page<ProductType> productTypes, final Pageable page, final Long total) {
        if (productTypes.hasContent()) {
            final List<ProductTypeInfo> responses = productTypes.stream().map(this::processResponse).collect(Collectors.toList());
            return new PageImpl<>(responses, page, total);
        }
        return new PageImpl<>(new ArrayList<>());
    }

    private ProductTypeInfo processResponse(final ProductType productType) {
        final ProductTypeInfo response = ProductTypeInfo.builder().build();
        BeanUtils.copyProperties(productType, response);
        return response;
    }

    @Override
    public Page<ProductTypeInfo> findAll(final Pageable page) {
        final Page<ProductType> productTypes = query.findAll(page);
        return processResponses(productTypes, page, Long.valueOf(productTypes.getSize()));
    }

    @Override
    public Optional<ProductTypeInfo> findById(final Long id) {
        final ProductType productType = query.findById(id).orElseThrow(
            () -> new ResourceNotFoundException(String.format("Can't find product type with id %s", id))
        );
        return Optional.of(processResponse(productType));
    }
}
