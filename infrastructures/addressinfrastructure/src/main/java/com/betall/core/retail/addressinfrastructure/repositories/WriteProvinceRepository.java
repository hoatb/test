package com.betall.core.retail.addressinfrastructure.repositories;

import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betall.core.retail.addressinfrastructure.models.Province;

/**
 * Define functions for write repository (relation database)
 */
@Repository
public interface WriteProvinceRepository extends JpaRepository<Province, Integer> {
}
