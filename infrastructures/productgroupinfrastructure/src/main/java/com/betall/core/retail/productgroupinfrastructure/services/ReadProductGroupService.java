package com.betall.core.retail.productgroupinfrastructure.services;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.betall.core.retail.productgroupcontext.models.ProductGroupInfo;
import com.betall.core.retail.productgroupinfrastructure.models.ProductGroup;

import com.betall.core.retail.productgroupcontext.repositories.QueryProductGroupRepository;
import com.betall.core.retail.productgroupinfrastructure.repositories.ReadProductGroupRepository;

import com.betall.core.retail.shared_kernels.exceptions.ResourceNotFoundException;

public class ReadProductGroupService implements QueryProductGroupRepository {
    private ReadProductGroupRepository query;

    public ReadProductGroupService(final ReadProductGroupRepository query) {
        this.query = query;
    }

    /**
     * Convert list ProductGroup entity to ProductGroupInfo with page
     * @param productGroups
     *
     * @return Page<ProductGroupInfo>
     */
    private Page<ProductGroupInfo> processResponses(final Page<ProductGroup> productGroups, final Pageable page, final Long total) {
        if (productGroups.hasContent()) {
            final List<ProductGroupInfo> responses = productGroups.stream().map(this::processResponse).collect(Collectors.toList());
            return new PageImpl<>(responses, page, total);
        }
        return new PageImpl<>(new ArrayList<>());
    }

    private ProductGroupInfo processResponse(final ProductGroup productGroup) {
        final ProductGroupInfo response = ProductGroupInfo.builder().build();
        BeanUtils.copyProperties(productGroup, response);
        return response;
    }

    @Override
    public Page<ProductGroupInfo> findAll(final Pageable page) {
        final Page<ProductGroup> productGroups = query.findAll(page);
        return processResponses(productGroups, page, Long.valueOf(productGroups.getSize()));
    }

    @Override
    public Optional<ProductGroupInfo> findById(final Long id) {
        final ProductGroup productGroup = query.findById(id).orElseThrow(
            () -> new ResourceNotFoundException(String.format("Can't find product group with id %s", id))
        );
        return Optional.of(processResponse(productGroup));
    }
}
