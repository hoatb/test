package com.betall.core.retail.producttypecontext.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.betall.core.retail.producttypecontext.models.ProductTypeInfo;

/**
 * Declare query actions
 */
public interface QueryProductTypeRepository {
    Page<ProductTypeInfo> findAll(final Pageable page);
    Optional<ProductTypeInfo> findById(final Long id);
}
