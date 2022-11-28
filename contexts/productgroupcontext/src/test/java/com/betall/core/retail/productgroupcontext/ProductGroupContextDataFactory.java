package com.betall.core.retail.productgroupcontext;

import java.util.List;
import java.util.ArrayList;

import com.betall.core.retail.productgroupcontext.models.ProductGroupInfo;
import com.betall.core.retail.productgroupcontext.responses.ProductGroupRepresentation;

public class ProductGroupContextDataFactory {
    public static ProductGroupInfo initProductGroup() {
        return ProductGroupInfo.builder()
            .id(1L)
            .name("Product Group 1")
            .priority(1)
            .isActive(1)
            .iconUrl("http://google.com/icons/icon1.png")
            .build();
    }

    public static List<ProductGroupInfo> initProductGroups(int size) {
        final List<ProductGroupInfo> productGroupInfos = new ArrayList<>();
        for(int i=0; i<size; i++) {
            final ProductGroupInfo productGroupInfo = ProductGroupInfo.builder()
                .id(i + 1L)
                .name("Product Group " + i)
                .priority(1)
                .isActive(1)
                .iconUrl("http://google.com/icons/icon1.png")
                .build();
            productGroupInfos.add(productGroupInfo);
        }
        return productGroupInfos;
    }

    public static ProductGroupRepresentation initProductGroupRepresentation() {
        return ProductGroupRepresentation.builder()
            .data(initProductGroup())
            .build();
    }

    public static ProductGroupRepresentation initProductGroupRepresentationDelete() {
        return ProductGroupRepresentation.builder()
            .data(ProductGroupInfo.builder()
                .id(2L)
                .name("Product Group 2")
                .priority(1)
                .isActive(0)
                .iconUrl("http://google.com/icons/icon1.png")
                .build())
            .build();
    }
}
