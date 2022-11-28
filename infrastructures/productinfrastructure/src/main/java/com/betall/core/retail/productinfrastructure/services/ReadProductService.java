package com.betall.core.retail.productinfrastructure.services;

import java.util.Optional;

import com.betall.core.retail.productcontext.models.ProductDetailInfo;
import com.betall.core.retail.productcontext.models.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.betall.core.retail.productinfrastructure.models.Product;
import com.betall.core.retail.productinfrastructure.utils.EntityMapper;

import com.betall.core.retail.productcontext.repositories.QueryProductRepository;

import com.betall.core.retail.productinfrastructure.repositories.ReadProductRepository;

import com.betall.core.retail.shared_kernels.exceptions.ResourceNotFoundException;

public class ReadProductService implements QueryProductRepository {
    private ReadProductRepository query;

    public ReadProductService(ReadProductRepository query) {
        this.query = query;
    }

    @Override
    public Page<ProductInfo> findAll(final Pageable page) {
        final Page<Product> product = query.findAll(page);
        EntityMapper mapper = new EntityMapper();
        return mapper.convertPage(product, page, Long.valueOf(product.getSize()));
    }

    @Override
    public Optional<ProductDetailInfo> findById(final Long id) {
        final Product product = query.findById(id).orElseThrow(
            () -> new ResourceNotFoundException(String.format("Not found product with id: %s", id))
        );

        EntityMapper mapper = new EntityMapper();
        return Optional.of(mapper.toProductDetailInfo(product));
    }
}
