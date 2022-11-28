package com.betall.core.retail.productbannercontext.services;

import java.util.Optional;
import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.betall.core.retail.productbannercontext.models.ProductBannerInfo;
import com.betall.core.retail.productbannercontext.models.ProductListBannerInfo;
import com.betall.core.retail.productbannercontext.responses.ProductBannerRepresentation;
import com.betall.core.retail.productbannercontext.responses.ProductListBannerRepresentation;

import com.betall.core.retail.productbannercontext.repositories.QueryProductBannerRepository;

/**
 * Implementation for query product banner repository
 */
public class QueryProductBannerService {
    private QueryProductBannerRepository query;

    public QueryProductBannerService(final QueryProductBannerRepository query) {
        this.query = query;
    }

    private ProductListBannerRepresentation processProductBanner(final Page<ProductBannerInfo> pages) {
        if (pages == null || !pages.hasContent()) {
            return ProductListBannerRepresentation.builder()
                .status(0)
                .message(null)
                .data(
                    ProductListBannerInfo.builder()
                        .lstBanner(new ArrayList<>())
                        .build()
                )
                .build();
        }
        return ProductListBannerRepresentation.builder()
            .status(0)
            .message(null)
            .data(
                ProductListBannerInfo.builder()
                    .lstBanner(pages.getContent())
                    .build()
                )
            .build();
    }

    /**
     * Find all product banner
     *
     * @return ProductListBannerRepresentation
     */
    public ProductListBannerRepresentation findAll(final Integer pageNo, final Integer pageSize) {
        final Page<ProductBannerInfo> pages = query.findAll(PageRequest.of(pageNo, pageSize));
        return processProductBanner(pages);
    }

    /**
     * Find product banner by id
     *
     * @return ProductBannerRepresentation
     */
    public ProductBannerRepresentation findById(final Long id) {
        final Optional<ProductBannerInfo> productGroupInfo = query.findById(id);
        if (productGroupInfo.isEmpty()) {
            return ProductBannerRepresentation.builder()
                .status(0)
                .message(null)
                .data(ProductBannerInfo.builder().build())
                .build();
        }
        return ProductBannerRepresentation.builder()
            .status(0)
            .message(null)
            .data(productGroupInfo.get())
            .build();
    }
}
