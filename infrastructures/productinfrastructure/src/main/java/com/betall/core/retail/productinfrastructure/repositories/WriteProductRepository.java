package com.betall.core.retail.productinfrastructure.repositories;

import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betall.core.retail.productinfrastructure.models.Product;

@Repository
public interface WriteProductRepository extends JpaRepository<Product, Long> {
}
