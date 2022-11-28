package com.betall.core.retail.addressinfrastructure.repositories;

import org.springframework.stereotype.Repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.betall.core.retail.addressinfrastructure.models.Province;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Define query functions for repository (relation database)
 */
@Repository
public interface ReadProvinceRepository extends JpaRepository<Province, Integer> {
    Page<Province> findAll(final Pageable page);
    Optional<Province> findById(final Integer id);
}
