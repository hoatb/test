package com.betall.core.retail.productgroupcontext.repositories;

import com.betall.core.retail.productgroupcontext.models.ProductGroupInfo;
import com.betall.core.retail.productgroupcontext.responses.ProductGroupRepresentation;

/**
 * Declare command actions
 */
public interface CommandProductGroupRepository {
    ProductGroupRepresentation save(final ProductGroupInfo productGroupInfo);
    ProductGroupRepresentation update(final ProductGroupInfo productGroupInfo);
    ProductGroupRepresentation delete(final Long id);
}
