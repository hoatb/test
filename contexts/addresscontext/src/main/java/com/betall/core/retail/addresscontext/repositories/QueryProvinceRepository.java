package com.betall.core.retail.addresscontext.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.betall.core.retail.addresscontext.models.ProvinceInfo;

/**
 * Declare query actions
 */
public interface QueryProvinceRepository {
    Page<ProvinceInfo> findAll(final Pageable page);
    Optional<ProvinceInfo> findById(final Integer id);
}
