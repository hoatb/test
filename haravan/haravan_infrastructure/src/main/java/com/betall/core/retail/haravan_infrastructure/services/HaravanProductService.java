package com.betall.core.retail.haravan_infrastructure.services;

import com.betall.core.retail.haravan_product_context.models.HaravanProductInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import com.betall.core.retail.shared_kernels.configs.HaravanServiceInfo;

import com.betall.core.retail.haravan_repositories.repositories.HaravanHttpProductRepository;
import com.betall.core.retail.haravan_product_context.repositories.QueryHaravanProductRepository;

public class HaravanProductService implements QueryHaravanProductRepository {
    @Autowired
    private HaravanHttpProductRepository query;

    @Autowired
    private HaravanServiceInfo serviceInfo;

    @Override
    public List<HaravanProductInfo> findAllProducts() {
        return query.getAllProducts(serviceInfo);
    }

    @Override
    public Optional<HaravanProductInfo> findProductById(final Long id) {
        return query.getProductById(serviceInfo, id);
    }
}
