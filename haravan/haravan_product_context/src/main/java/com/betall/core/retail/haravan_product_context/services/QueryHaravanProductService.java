package com.betall.core.retail.haravan_product_context.services;

import com.betall.core.retail.haravan_product_context.models.HaravanProductInfo;

import java.util.List;

import com.betall.core.retail.haravan_product_context.repositories.QueryHaravanProductRepository;

import com.betall.core.retail.shared_kernels.exceptions.ResourceNotFoundException;

public class QueryHaravanProductService {
    private QueryHaravanProductRepository query;

    public QueryHaravanProductService(final QueryHaravanProductRepository query) {
        this.query = query;
    }

    /**
     * Find all products
     * @return List<HaravanProductInfo>
     */
    public List<HaravanProductInfo> findAllProducts() {
        return query.findAllProducts();
    }

    /**
     * Find product by id
     * @param id
     * @return HaravanProductInfo
     */
    public HaravanProductInfo findProductById(final Long id) {
        return query.findProductById(id).orElseThrow(
            () -> new ResourceNotFoundException(String.format("Not found product with id %s", id)));
    }
}
