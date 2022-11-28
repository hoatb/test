package com.betall.core.retail.producttypecontext;

import com.betall.core.retail.producttypecontext.models.ProductTypeInfo;
import com.betall.core.retail.producttypecontext.responses.ProductTypeRepresentation;

import java.util.ArrayList;
import java.util.List;

public class ProductTypeContextDataFactory {
    public static ProductTypeInfo initProductType() {
        return ProductTypeInfo.builder()
            .id(1L)
            .name("Product Type 1")
            .priority(1)
            .isActive(1)
            .build();
    }

    public static List<ProductTypeInfo> initProductTypes(int size) {
        final List<ProductTypeInfo> productTypeInfos = new ArrayList<>();
        for(int i=0; i<size; i++) {
            final ProductTypeInfo productTypeInfo = ProductTypeInfo.builder()
                .id(i + 1L)
                .name("Product Type " + i)
                .priority(1)
                .isActive(1)
                .build();
            productTypeInfos.add(productTypeInfo);
        }
        return productTypeInfos;
    }

    public static ProductTypeRepresentation initProductTypeRepresentation() {
        return ProductTypeRepresentation.builder()
            .data(initProductType())
            .build();
    }

    public static ProductTypeRepresentation initProductTypeRepresentationDelete() {
        return ProductTypeRepresentation.builder()
            .data(ProductTypeInfo.builder()
                .id(2L)
                .name("Product Type 2")
                .priority(1)
                .isActive(0)
                .build()
            )
            .build();
    }
}
