package com.betall.core.retail.productbannercontext;

import java.util.List;
import java.util.ArrayList;

import com.betall.core.retail.productbannercontext.models.ProductBannerInfo;
import com.betall.core.retail.productbannercontext.responses.ProductBannerRepresentation;

public class ProductBannerContextDataFactory {
    public static ProductBannerInfo initProductBanner() {
        return ProductBannerInfo.builder()
            .id(1L)
            .priority(1)
            .isActive(1)
            .imageUrl("http://google.com/icons/icon1.png")
            .url("http://google.com/icons/icon1.png")
            .build();
    }

    public static List<ProductBannerInfo> initProductBanners(int size) {
        final List<ProductBannerInfo> productBannerInfos = new ArrayList<>();
        for(int i=0; i<size; i++) {
            final ProductBannerInfo productBannerInfo = ProductBannerInfo.builder()
                .id(i + 1L)
                .priority(1)
                .isActive(1)
                .imageUrl("http://google.com/icons/icon1.png")
                .url("http://google.com/icons/icon1.png")
                .build();
            productBannerInfos.add(productBannerInfo);
        }
        return productBannerInfos;
    }

    public static ProductBannerRepresentation initProductBannerRepresentation() {
        return ProductBannerRepresentation.builder()
            .data(initProductBanner())
            .build();
    }

    public static ProductBannerRepresentation initProductBannerRepresentationDelete() {
        return ProductBannerRepresentation.builder()
            .data(ProductBannerInfo.builder()
                .id(2L)
                .priority(1)
                .isActive(0)
                .imageUrl("http://google.com/icons/icon1.png")
                .url("http://google.com/icons/icon1.png")
                .build())
            .build();
    }
}
