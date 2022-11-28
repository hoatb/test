package com.betall.core.retail.productcontext.repositories;

import com.betall.core.retail.productcontext.models.ProductDetailInfo;
import com.betall.core.retail.productcontext.models.ProductDetailList;
import com.betall.core.retail.productcontext.responses.ProductDetailRepresentation;
import org.springframework.http.HttpStatus;

public interface CommandProductRepository {
    ProductDetailRepresentation save(final ProductDetailInfo productDetailInfo);
    HttpStatus saveAll(final ProductDetailList productDetailList);
    ProductDetailRepresentation update(final ProductDetailInfo productDetailInfo);
    ProductDetailRepresentation delete(final Long id);
}
