package com.betall.core.retail.productbannerinfrastructure.repositories;

import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.betall.core.retail.productbannerinfrastructure.models.ProductBanner;

/**
 * Define functions for write repository (relation database)
 */
@Repository
public interface WriteProductBannerRepository extends JpaRepository<ProductBanner, Long> {
}
