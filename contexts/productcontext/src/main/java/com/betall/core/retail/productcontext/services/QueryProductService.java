package com.betall.core.retail.productcontext.services;

import java.util.Optional;

import com.betall.core.retail.productcontext.models.*;
import com.betall.core.retail.productcontext.requests.ProductListRequest;
import com.betall.core.retail.productcontext.responses.ProductInfoRepresentation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.betall.core.retail.productcontext.responses.ProductDetailRepresentation;

import com.betall.core.retail.productcontext.repositories.QueryProductRepository;

/**
 * Implementation of query ProductDetailInfo Info repository.
 */
public class QueryProductService {
    private QueryProductRepository query;

    public QueryProductService(QueryProductRepository query) {
        this.query = query;
    }

    /**
     * Change Page<> to ProductDetailRepresentation
     * @param pages
     * @return ProductDetailRepresentation
     */
    private ProductInfoRepresentation processProductListInfo(final Page<ProductInfo> pages) {
        if (pages == null || !pages.hasContent()) {
            return ProductInfoRepresentation.builder()
                .status(0)
                .message(null)
                .data(null)
                .build();
        }
        return ProductInfoRepresentation.builder()
            .status(0)
            .message(null)
            .data(
                ProductInfoList.builder()
                    .total(pages.getSize())
                    .lstProduct(pages.getContent())
                    .build()
            )
            .build();
    }

    /**
     * Find all product info (OLD API product-list)
     * @param request
     * @return ProductListHasTotalInfoRepresentation if successful
     */
    public ProductInfoRepresentation findAll(final ProductListRequest request) {
        Page<ProductInfo> pages = query.findAll(PageRequest.of(request.getPage(), request.getPageSize()));
        return processProductListInfo(pages);
    }

    /**
     * Find product detail info by id
     * @param id
     * @return ProductDetailRepresentation if successful
     */
    public ProductDetailRepresentation findById(final Long id) {
        final Optional<ProductDetailInfo> productDetailInfo = query.findById(id);
        if (productDetailInfo.isEmpty()) {
            return ProductDetailRepresentation.builder()
                .status(0)
                .message(null)
                .data(null)
                .build();
        }
        return ProductDetailRepresentation.builder()
            .status(0)
            .message(null)
            .data(
                ProductDetail.builder()
                    .productDetailInfo(productDetailInfo.get())
                    .build()
            )
            .build();
    }
}
