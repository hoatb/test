package com.betall.core.retail.productgroupcontext.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.betall.core.retail.productgroupcontext.models.ProductGroupInfo;

/**
 * Declare query actions
 */
public interface QueryProductGroupRepository {
    Page<ProductGroupInfo> findAll(final Pageable page);
    Optional<ProductGroupInfo> findById(final Long id);
}
