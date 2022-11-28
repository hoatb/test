package com.betall.core.retail.productcontext.repositories;

import com.betall.core.retail.productcontext.models.ProductInfoList;
import com.betall.core.retail.productcontext.requests.OrderProductList;

public interface QueryProductColorRepository {
    ProductInfoList findProductListById(final OrderProductList orderProductList);
}
