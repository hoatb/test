package com.betall.core.retail.producttypeinfrastructure;

import java.util.List;
import java.util.ArrayList;

import com.betall.core.retail.producttypecontext.models.ProductTypeInfo;
import com.betall.core.retail.producttypecontext.responses.ProductTypeRepresentation;

import com.betall.core.retail.producttypeinfrastructure.models.ProductType;

public class ProductTypeInfrastructureDataFactory {
    public static ProductTypeInfo initProductTypeInfo() {
        return ProductTypeInfo.builder()
            .id(1L)
            .name("Product Type 1")
            .priority(1)
            .isActive(1)
            .build();
    }

    public static ProductType initProductType() {
        return ProductType.builder()
            .id(1L)
            .name("Product Type 1")
            .priority(1)
            .isActive(1)
            .build();
    }

    public static ProductTypeRepresentation initRepresentation() {
        return ProductTypeRepresentation.builder()
            .status(0)
            .message(null)
            .data(initProductTypeInfo())
            .build();
    }

    public static List<ProductType> initProductTypes(int size) {
        final List<ProductType> productTypes = new ArrayList<>();
        for(int i=0; i<size; i++) {
            final ProductType productType = ProductType.builder()
                .id(i + 1L)
                .name("Product Type " + i)
                .priority(1)
                .isActive(1)
                .build();
            productTypes.add(productType);
        }
        return productTypes;
    }
}
