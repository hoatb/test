package com.betall.core.retail.productbannerinfrastructure;

import java.util.List;
import java.util.ArrayList;

import com.betall.core.retail.productbannercontext.models.ProductBannerInfo;
import com.betall.core.retail.productbannerinfrastructure.models.ProductBanner;
import com.betall.core.retail.productbannercontext.responses.ProductBannerRepresentation;

public class ProductBannerInfrastructureDataFactory {
    public static ProductBannerInfo initProductBannerInfo() {
        return ProductBannerInfo.builder()
            .id(1L)
            .priority(1)
            .isActive(1)
            .imageUrl("https://google.com/icons/icon1.png")
            .url("https://google.com/icons/icon1.png")
            .build();
    }

    public static ProductBanner initProductBanner() {
        return ProductBanner.builder()
            .id(1L)
            .priority(1)
            .isActive(1)
            .imageUrl("https://google.com/icons/icon1.png")
            .url("https://google.com/icons/icon1.png")
            .build();
    }

    public static ProductBannerRepresentation initRepresentation() {
        return ProductBannerRepresentation.builder()
            .status(0)
            .message(null)
            .data(initProductBannerInfo())
            .build();
    }

    public static List<ProductBanner> initProductBanners(int size) {
        final List<ProductBanner> productBanners = new ArrayList<>();
        for(int i=0; i<size; i++) {
            final ProductBanner productBanner = ProductBanner.builder()
                .id(i + 1L)
                .priority(1)
                .isActive(1)
                .imageUrl("https://google.com/icons/icon1.png")
                .url("https://google.com/icons/icon1.png")
                .build();
            productBanners.add(productBanner);
        }
        return productBanners;
    }
}
