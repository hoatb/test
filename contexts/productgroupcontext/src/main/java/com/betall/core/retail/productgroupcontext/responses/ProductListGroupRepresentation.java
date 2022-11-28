package com.betall.core.retail.productgroupcontext.responses;

import lombok.*;
import lombok.experimental.SuperBuilder;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.betall.core.retail.shared_kernels.responses.BaseRepresentation;
import com.betall.core.retail.productgroupcontext.models.ProductListGroupInfo;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ProductListGroupRepresentation extends BaseRepresentation {
    @JsonProperty("data")
    private ProductListGroupInfo data;
}
