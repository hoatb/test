package com.betall.core.retail.productgroupinfrastructure;

import com.betall.core.retail.productgroupcontext.models.ProductGroupInfo;
import com.betall.core.retail.productgroupinfrastructure.models.ProductGroup;
import com.betall.core.retail.productgroupcontext.responses.ProductGroupRepresentation;

import java.util.ArrayList;
import java.util.List;

public class ProductGroupInfrastructureDataFactory {
    public static ProductGroupInfo initProductGroupInfo() {
        return ProductGroupInfo.builder()
            .id(1L)
            .name("Product Group 1")
            .priority(1)
            .isActive(1)
            .iconUrl("https://google.com/icons/icon1.png")
            .build();
    }

    public static ProductGroup initProductGroup() {
        return ProductGroup.builder()
            .id(1L)
            .name("Product Group 1")
            .priority(1)
            .isActive(1)
            .iconUrl("https://google.com/icons/icon1.png")
            .build();
    }

    public static ProductGroupRepresentation initRepresentation() {
        return ProductGroupRepresentation.builder()
            .status(0)
            .message(null)
            .data(initProductGroupInfo())
            .build();
    }

    public static List<ProductGroup> initProductGroups(int size) {
        final List<ProductGroup> productGroups = new ArrayList<>();
        for(int i=0; i<size; i++) {
            final ProductGroup productGroup = ProductGroup.builder()
                .id(i + 1L)
                .name("Product Group " + i)
                .priority(1)
                .isActive(1)
                .iconUrl("https://google.com/icons/icon1.png")
                .build();
            productGroups.add(productGroup);
        }
        return productGroups;
    }
}
