package com.betall.core.retail.productbannerinfrastructure.utils;

import org.springframework.beans.BeanUtils;
import com.betall.core.retail.productbannercontext.models.ProductBannerInfo;
import com.betall.core.retail.productbannerinfrastructure.models.ProductBanner;
import com.betall.core.retail.productbannercontext.responses.ProductBannerRepresentation;

public class EntityMapper {
    public ProductBanner toProductBanner(final ProductBannerInfo productBannerInfo) {
        final ProductBanner productBanner = ProductBanner.builder().build();
        BeanUtils.copyProperties(productBannerInfo, productBanner);
        return productBanner;
    }

    public ProductBannerRepresentation toProductBannerRepresentation(final ProductBanner productBanner) {
        final ProductBannerInfo productBannerInfo = ProductBannerInfo.builder().build();
        BeanUtils.copyProperties(productBanner, productBannerInfo);

        return ProductBannerRepresentation.builder()
            .status(0)
            .message(null)
            .data(productBannerInfo)
            .build();
    }

    public void update(final ProductBanner productBanner, final ProductBannerInfo productBannerInfo) {
        BeanUtils.copyProperties(productBannerInfo, productBanner);
    }
}
