package com.betall.core.retail.productbannercontext.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import com.betall.core.retail.productbannercontext.models.ProductBannerInfo;

/**
 * Declare query actions
 */
public interface QueryProductBannerRepository {
    Page<ProductBannerInfo> findAll(final Pageable page);
    Optional<ProductBannerInfo> findById(final Long id);
}
