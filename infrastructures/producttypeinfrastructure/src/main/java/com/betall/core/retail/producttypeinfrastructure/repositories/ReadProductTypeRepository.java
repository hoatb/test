package com.betall.core.retail.producttypeinfrastructure.repositories;

import org.springframework.stereotype.Repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.betall.core.retail.producttypeinfrastructure.models.ProductType;

/**
 * Define query functions for repository (relation database)
 */
@Repository
public interface ReadProductTypeRepository extends JpaRepository<ProductType, Long> {
    Page<ProductType> findAll(final Pageable page);
    Optional<ProductType> findById(final Long id);
}
