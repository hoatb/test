package com.betall.core.retail.productbannerinfrastructure.services;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.betall.core.retail.productbannercontext.models.ProductBannerInfo;
import com.betall.core.retail.productbannerinfrastructure.models.ProductBanner;

import com.betall.core.retail.productbannercontext.repositories.QueryProductBannerRepository;
import com.betall.core.retail.productbannerinfrastructure.repositories.ReadProductBannerRepository;

import com.betall.core.retail.shared_kernels.exceptions.ResourceNotFoundException;

public class ReadProductBannerService implements QueryProductBannerRepository {
    private ReadProductBannerRepository query;

    public ReadProductBannerService(final ReadProductBannerRepository query) {
        this.query = query;
    }

    /**
     * Convert list ProductBanner entity to ProductBannerInfo with page
     * @param productBanners
     *
     * @return Page<ProductBannerInfo>
     */
    private Page<ProductBannerInfo> processResponses(final Page<ProductBanner> productBanners, final Pageable page, final Long total) {
        if (productBanners.hasContent()) {
            final List<ProductBannerInfo> responses = productBanners.stream().map(this::processResponse).collect(Collectors.toList());
            return new PageImpl<>(responses, page, total);
        }
        return new PageImpl<>(new ArrayList<>());
    }

    private ProductBannerInfo processResponse(final ProductBanner productBanner) {
        final ProductBannerInfo response = ProductBannerInfo.builder().build();
        BeanUtils.copyProperties(productBanner, response);
        return response;
    }

    @Override
    public Page<ProductBannerInfo> findAll(final Pageable page) {
        final Page<ProductBanner> productGroups = query.findAll(page);
        return processResponses(productGroups, page, Long.valueOf(productGroups.getSize()));
    }

    @Override
    public Optional<ProductBannerInfo> findById(final Long id) {
        final ProductBanner productGroup = query.findById(id).orElseThrow(
            () -> new ResourceNotFoundException(String.format("Can't find product banner with id %s", id))
        );
        return Optional.of(processResponse(productGroup));
    }
}
