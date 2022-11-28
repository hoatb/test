package com.betall.core.retail.producttypeinfrastructure.utils;

import org.springframework.beans.BeanUtils;

import com.betall.core.retail.producttypecontext.models.ProductTypeInfo;
import com.betall.core.retail.producttypecontext.responses.ProductTypeRepresentation;

import com.betall.core.retail.producttypeinfrastructure.models.ProductType;

public class EntityMapper {
    public ProductType toProductType(final ProductTypeInfo productGroupInfo) {
        final ProductType productGroup = ProductType.builder().build();
        BeanUtils.copyProperties(productGroupInfo, productGroup);
        return productGroup;
    }

    public ProductTypeRepresentation toProductTypeRepresentation(final ProductType productType) {
        final ProductTypeInfo productTypeInfo = ProductTypeInfo.builder().build();
        BeanUtils.copyProperties(productType, productTypeInfo);

        return ProductTypeRepresentation.builder()
            .status(0)
            .message(null)
            .data(productTypeInfo)
            .build();
    }

    public void update(final ProductType productType, final ProductTypeInfo productTypeInfo) {
        BeanUtils.copyProperties(productTypeInfo, productType);
    }
}
