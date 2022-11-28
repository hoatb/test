package com.betall.core.retail.productbannerinfrastructure.repositories;

import org.springframework.stereotype.Repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.betall.core.retail.productbannerinfrastructure.models.ProductBanner;

/**
 * Define query functions for repository (relation database)
 */
@Repository
public interface ReadProductBannerRepository extends JpaRepository<ProductBanner, Long> {
    Page<ProductBanner> findAll(final Pageable page);
    Optional<ProductBanner> findById(final Long id);
}
