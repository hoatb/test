package com.betall.core.retail.addresscontext.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.betall.core.retail.addresscontext.responses.AddressResponse;

/**
 * Declare query actions
 */
public interface QueryAddressRepository {
    Page<AddressResponse> findAll(final Pageable page);
    Optional<AddressResponse> findById(final Integer id);
}
