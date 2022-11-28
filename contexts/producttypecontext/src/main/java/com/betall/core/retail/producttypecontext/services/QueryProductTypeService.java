package com.betall.core.retail.producttypecontext.services;

import java.util.Optional;
import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.betall.core.retail.producttypecontext.models.ProductTypeInfo;
import com.betall.core.retail.producttypecontext.models.ProductListTypeInfo;

import com.betall.core.retail.producttypecontext.responses.ProductTypeRepresentation;
import com.betall.core.retail.producttypecontext.responses.ProductListTypeRepresentation;

import com.betall.core.retail.producttypecontext.repositories.QueryProductTypeRepository;

/**
 * Implementation for query product type repository
 */
public class QueryProductTypeService {
    private QueryProductTypeRepository query;

    public QueryProductTypeService(final QueryProductTypeRepository query) {
        this.query = query;
    }

    private ProductListTypeRepresentation processResponses(final Page<ProductTypeInfo> pages) {
        if (pages == null || !pages.hasContent()) {
            return ProductListTypeRepresentation.builder()
                .status(0)
                .message(null)
                .data(
                    ProductListTypeInfo.builder()
                        .lstType(new ArrayList<>())
                        .build()
                )
                .build();
        }
        return ProductListTypeRepresentation.builder()
            .status(0)
            .message(null)
            .data(
                ProductListTypeInfo.builder()
                    .lstType(pages.getContent())
                    .build()
            )
            .build();
    }

    /**
     * Find all product type
     *
     * @return ProductListTypeRepresentation
     */
    public ProductListTypeRepresentation findAll(final Integer pageNo, final Integer pageSize) {
        final Page<ProductTypeInfo> pages = query.findAll(PageRequest.of(pageNo, pageSize));
        return processResponses(pages);
    }

    /**
     * Find product group by id
     *
     * @return ProductTypeRepresentation
     */
    public ProductTypeRepresentation findById(final Long id) {
        final Optional<ProductTypeInfo> productTypeInfo = query.findById(id);
        if (productTypeInfo.isEmpty()) {
            return ProductTypeRepresentation.builder()
                .status(0)
                .message(null)
                .data(ProductTypeInfo.builder().build())
                .build();
        }
        return ProductTypeRepresentation.builder()
            .status(0)
            .message(null)
            .data(productTypeInfo.get())
            .build();
    }
}
