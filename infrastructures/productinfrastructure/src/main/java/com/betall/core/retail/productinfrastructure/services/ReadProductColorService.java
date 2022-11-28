package com.betall.core.retail.productinfrastructure.services;

import java.util.List;
import java.util.ArrayList;

import com.betall.core.retail.productcontext.models.ProductInfo;
import com.betall.core.retail.productcontext.models.ProductInfoList;

import com.betall.core.retail.productcontext.requests.OrderProductInfo;
import com.betall.core.retail.productcontext.requests.OrderProductList;

import com.betall.core.retail.productinfrastructure.utils.EntityMapper;
import com.betall.core.retail.productinfrastructure.models.ProductColor;

import com.betall.core.retail.productcontext.repositories.QueryProductColorRepository;
import com.betall.core.retail.productinfrastructure.repositories.ReadProductColorRepository;

import com.betall.core.retail.shared_kernels.exceptions.ResourceNotFoundException;

public class ReadProductColorService implements QueryProductColorRepository {
    private ReadProductColorRepository query;


    public ReadProductColorService(ReadProductColorRepository query) {
        this.query = query;
    }

    @Override
    public ProductInfoList findProductListById(OrderProductList orderProductList) {
        EntityMapper mapper = new EntityMapper();
        List<ProductInfo> productInfos = new ArrayList<>();
        for (OrderProductInfo orderProductInfo : orderProductList.getLstOrderProduct()) {
            final Long productId = orderProductInfo.getProductId();
            final Long colorId = orderProductInfo.getColorId();

            final ProductColor productColor = query.findByProductIdAndColorId(productId, colorId).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Not Found productId %s has colorId %s", productId, colorId))
            );

            ProductInfo productInfo = mapper.toProductInfo(productColor.getProduct(), productColor);
            productInfos.add(productInfo);
        }

        return ProductInfoList.builder().lstProduct(productInfos).build();
    }
}
