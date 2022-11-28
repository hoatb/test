package com.betall.core.retail.productinfrastructure.repositories;

import org.springframework.stereotype.Repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.betall.core.retail.productinfrastructure.models.Product;

@Repository
public interface ReadProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAll(final Pageable page);
    Optional<Product> findById(final Long id);
}
