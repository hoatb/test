package com.betall.core.retail.productgroupinfrastructure.repositories;

import org.springframework.stereotype.Repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.betall.core.retail.productgroupinfrastructure.models.ProductGroup;

/**
 * Define query functions for repository (relation database)
 */

@Repository
public interface ReadProductGroupRepository extends JpaRepository<ProductGroup, Long> {
    Page<ProductGroup> findAll(final Pageable page);
    Optional<ProductGroup> findById(final Long id);
}
