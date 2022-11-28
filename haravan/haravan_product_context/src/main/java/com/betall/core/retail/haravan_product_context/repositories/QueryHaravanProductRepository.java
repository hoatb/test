package com.betall.core.retail.haravan_product_context.repositories;

import java.util.List;
import java.util.Optional;
import com.betall.core.retail.haravan_product_context.models.HaravanProductInfo;

public interface QueryHaravanProductRepository {
    /**
     * Find all products
     * @return List<HaravanProductInfo>
     */
    List<HaravanProductInfo> findAllProducts();

    /**
     * Find product by id
     * @param id product's id
     * @return Optional<HaravanProductInfo>
     */
    Optional<HaravanProductInfo> findProductById(Long id);
}
