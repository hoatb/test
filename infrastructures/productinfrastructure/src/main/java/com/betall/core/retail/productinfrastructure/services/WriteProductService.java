package com.betall.core.retail.productinfrastructure.services;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.ArrayList;

import org.springframework.http.HttpStatus;

import com.betall.core.retail.productcontext.models.ProductDetailInfo;
import com.betall.core.retail.productcontext.models.ProductDetailList;

import com.betall.core.retail.productcontext.responses.ProductDetailRepresentation;

import com.betall.core.retail.productinfrastructure.models.Product;
import com.betall.core.retail.productinfrastructure.utils.EntityMapper;

import com.betall.core.retail.productcontext.repositories.CommandProductRepository;

import com.betall.core.retail.productinfrastructure.repositories.ReadProductRepository;
import com.betall.core.retail.productinfrastructure.repositories.WriteProductRepository;

import com.betall.core.retail.shared_kernels.exceptions.ResourceNotFoundException;

@Slf4j
public class WriteProductService implements CommandProductRepository {
    private WriteProductRepository command;
    private ReadProductRepository query;

    public WriteProductService(WriteProductRepository command, ReadProductRepository query) {
        this.command = command;
        this.query = query;
    }

    @Override
    public ProductDetailRepresentation save(final ProductDetailInfo productDetailInfo) {
        EntityMapper mapper = new EntityMapper();
        final Product entity = mapper.toProduct(productDetailInfo);
        final Product saved = command.save(entity);
        return mapper.toProductDetailRepresentation(saved);
    }

    @Override
    public HttpStatus saveAll(final ProductDetailList productDetailList) {
        deleteAll();
        final List<Product> products = new ArrayList<>();
        final EntityMapper mapper = new EntityMapper();
        for(ProductDetailInfo productInfo : productDetailList.getProductsDetail()) {
            final Product entity = mapper.toProduct(productInfo);
            products.add(entity);
        }
        final List<Product> saved = command.saveAll(products);
        log.info(String.format("Successful saved %s product(s).", saved.size()));
        return HttpStatus.OK;
    }


    @Override
    public ProductDetailRepresentation update(final ProductDetailInfo productDetailInfo) {
        EntityMapper mapper = new EntityMapper();
        final Product entity = query.findById(productDetailInfo.getProductId()).orElseThrow(
            () -> new ResourceNotFoundException(String.format("Can't find product with id %s", productDetailInfo.getProductId()))
        );
        mapper.update(entity, productDetailInfo);
        final Product saved = command.save(entity);
        return mapper.toProductDetailRepresentation(saved);
    }

    @Override
    public ProductDetailRepresentation delete(final Long id) {
        final Product entity = query.findById(id).orElseThrow(
            () -> new ResourceNotFoundException(String.format("Can't find product with id %s", id))
        );
        EntityMapper mapper = new EntityMapper();
        entity.setIsActive(false);
        final Product saved = command.save(entity);
        return mapper.toProductDetailRepresentation(saved);
    }

    private void deleteAll() {
        final List<Product> products = query.findAll();
        command.deleteAll(products);
    }
}
