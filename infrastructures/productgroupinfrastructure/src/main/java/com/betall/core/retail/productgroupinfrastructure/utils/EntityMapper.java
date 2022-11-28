package com.betall.core.retail.productgroupinfrastructure.utils;

import com.betall.core.retail.productgroupcontext.models.ProductGroupInfo;
import com.betall.core.retail.productgroupcontext.responses.ProductGroupRepresentation;
import com.betall.core.retail.productgroupinfrastructure.models.ProductGroup;
import org.springframework.beans.BeanUtils;

public class EntityMapper {
    public ProductGroup toProductGroup(final ProductGroupInfo productGroupInfo) {
        final ProductGroup productGroup = ProductGroup.builder().build();
        BeanUtils.copyProperties(productGroupInfo, productGroup);
        return productGroup;
    }

    public ProductGroupRepresentation toProductGroupRepresentation(final ProductGroup productGroup) {
        final ProductGroupInfo productGroupInfo = ProductGroupInfo.builder().build();
        BeanUtils.copyProperties(productGroup, productGroupInfo);

        return ProductGroupRepresentation.builder()
            .status(0)
            .message(null)
            .data(productGroupInfo)
            .build();
    }

    public void update(final ProductGroup productGroup, final ProductGroupInfo productGroupInfo) {
        BeanUtils.copyProperties(productGroupInfo, productGroup);
    }
}
