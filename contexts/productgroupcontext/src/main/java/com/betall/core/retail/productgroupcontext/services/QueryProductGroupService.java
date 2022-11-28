package com.betall.core.retail.productgroupcontext.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.betall.core.retail.productgroupcontext.models.ProductGroupInfo;
import com.betall.core.retail.productgroupcontext.models.ProductListGroupInfo;

import com.betall.core.retail.productgroupcontext.responses.ProductGroupRepresentation;
import com.betall.core.retail.productgroupcontext.responses.ProductListGroupRepresentation;

import com.betall.core.retail.productgroupcontext.repositories.QueryProductGroupRepository;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Implementation for query product group repository
 */
public class QueryProductGroupService {
    private QueryProductGroupRepository query;

    public QueryProductGroupService(final QueryProductGroupRepository query) {
        this.query = query;
    }

    private ProductListGroupRepresentation processProductGroup(final Page<ProductGroupInfo> pages) {
        if (pages == null || !pages.hasContent()) {
            return ProductListGroupRepresentation.builder()
                .status(0)
                .message(null)
                .data(
                    ProductListGroupInfo.builder()
                        .lstGroup(new ArrayList<>())
                        .build()
                )
                .build();
        }
        return ProductListGroupRepresentation.builder()
            .status(0)
            .message(null)
            .data(
                ProductListGroupInfo.builder()
                    .lstGroup(pages.getContent()
                ).build()
            )
            .build();
    }

    /**
     * Find all product group
     *
     * @return ProductListGroupRepresentation
     */
    public ProductListGroupRepresentation findAll(final Integer pageNo, final Integer pageSize) {
        final Page<ProductGroupInfo> pages = query.findAll(PageRequest.of(pageNo, pageSize));
        return processProductGroup(pages);
    }

    /**
     * Find product group by id
     *
     * @return ProductGroupRepresentation
     */
    public ProductGroupRepresentation findById(final Long id) {
        final Optional<ProductGroupInfo> productGroupInfo = query.findById(id);
        if (productGroupInfo.isEmpty()) {
            return ProductGroupRepresentation.builder()
                .status(0)
                .message(null)
                .data(ProductGroupInfo.builder().build())
                .build();
        }
        return ProductGroupRepresentation.builder()
            .status(0)
            .message(null)
            .data(productGroupInfo.get())
            .build();
    }
}
