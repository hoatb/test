package com.betall.core.retail.haravan_repositories.repositories;

import com.betall.core.retail.haravan_product_context.models.HaravanProductInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import com.betall.core.retail.shared_kernels.configs.HaravanServiceInfo;

@Repository
public interface HaravanHttpProductRepository {
    List<HaravanProductInfo> getAllProducts(final HaravanServiceInfo serviceInfo);
    Optional<HaravanProductInfo> getProductById(final HaravanServiceInfo serviceInfo, final Long id);
}
