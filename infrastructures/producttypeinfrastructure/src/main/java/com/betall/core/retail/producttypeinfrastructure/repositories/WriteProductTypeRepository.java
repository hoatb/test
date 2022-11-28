package com.betall.core.retail.producttypeinfrastructure.repositories;

import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betall.core.retail.producttypeinfrastructure.models.ProductType;

/**
 * Define functions for write repository (relation database)
 */
@Repository
public interface WriteProductTypeRepository extends JpaRepository<ProductType, Long> {
}
