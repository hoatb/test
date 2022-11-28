package com.betall.core.retail.productcontext.repositories;

import java.util.Optional;

import com.betall.core.retail.productcontext.models.ProductDetailInfo;
import com.betall.core.retail.productcontext.models.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QueryProductRepository {
    Page<ProductInfo> findAll(final Pageable page);
    Optional<ProductDetailInfo> findById(final Long id);
}
