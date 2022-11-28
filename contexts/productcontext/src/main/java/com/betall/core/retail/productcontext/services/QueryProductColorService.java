package com.betall.core.retail.productcontext.services;

import com.betall.core.retail.productcontext.models.ProductInfoList;
import com.betall.core.retail.productcontext.repositories.QueryProductColorRepository;
import com.betall.core.retail.productcontext.requests.OrderProductList;
import com.betall.core.retail.productcontext.responses.ProductInfoRepresentation;

public class QueryProductColorService {
    private QueryProductColorRepository query;

    public QueryProductColorService(QueryProductColorRepository query) {
        this.query = query;
    }

    /**
     * Find product info by OrderProductInfo
     * @param orderProductList
     * @return ProductInfoRepresentation
     */
    public ProductInfoRepresentation findProductListById(final OrderProductList orderProductList) {
        orderProductList.validate();
        ProductInfoList productInfoList = query.findProductListById(orderProductList);
        if (productInfoList.getLstProduct().isEmpty()) {
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
                    .total(null)
                    .lstProduct(productInfoList.getLstProduct())
                    .build()
            )
            .build();
    }
}
