package com.betall.core.retail.productinfrastructure.repositories;

import com.betall.core.retail.productinfrastructure.models.ProductColor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReadProductColorRepository extends JpaRepository<ProductColor, Long> {
    Optional<ProductColor> findByProductIdAndColorId(final @Param("productId") Long productId, final @Param("colorId") Long colorId);
}
