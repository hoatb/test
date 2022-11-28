package com.betall.core.retail.producttypecontext.repositories;

import com.betall.core.retail.producttypecontext.models.ProductTypeInfo;
import com.betall.core.retail.producttypecontext.responses.ProductTypeRepresentation;

/**
 * Declare command actions
 */
public interface CommandProductTypeRepository {
    ProductTypeRepresentation save(final ProductTypeInfo productTypeInfo);
    ProductTypeRepresentation update(final ProductTypeInfo productTypeInfo);
    ProductTypeRepresentation delete(final Long id);
}
