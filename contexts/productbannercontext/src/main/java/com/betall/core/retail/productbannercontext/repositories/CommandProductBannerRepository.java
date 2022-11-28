package com.betall.core.retail.productbannercontext.repositories;

import com.betall.core.retail.productbannercontext.models.ProductBannerInfo;
import com.betall.core.retail.productbannercontext.responses.ProductBannerRepresentation;

/**
 * Declare command actions
 */
public interface CommandProductBannerRepository {
    ProductBannerRepresentation save(final ProductBannerInfo productBannerInfo);
    ProductBannerRepresentation update(final ProductBannerInfo productBannerInfo);
    ProductBannerRepresentation delete(final Long id);
}
