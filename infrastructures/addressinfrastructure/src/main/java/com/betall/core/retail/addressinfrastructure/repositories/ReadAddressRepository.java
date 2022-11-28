package com.betall.core.retail.addressinfrastructure.repositories;

import org.springframework.stereotype.Repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betall.core.retail.addressinfrastructure.models.Address;

/**
 * Define query functions for repository (relation database)
 */
@Repository
public interface ReadAddressRepository extends JpaRepository<Address, Integer> {
    Page<Address> findAll(final Pageable page);
    Optional<Address> findById(final Integer id);
}
