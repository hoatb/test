package com.betall.core.retail.productgroupinfrastructure.repositories;

import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betall.core.retail.productgroupinfrastructure.models.ProductGroup;

/**
 * Define functions for write repository (relation database)
 */

@Repository
public interface WriteProductGroupRepository extends JpaRepository<ProductGroup, Long> {
}
