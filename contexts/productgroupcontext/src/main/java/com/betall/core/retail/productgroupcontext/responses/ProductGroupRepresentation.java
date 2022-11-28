package com.betall.core.retail.productgroupcontext.responses;

import lombok.*;
import lombok.experimental.SuperBuilder;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.betall.core.retail.productgroupcontext.models.ProductGroupInfo;
import com.betall.core.retail.shared_kernels.responses.BaseRepresentation;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ProductGroupRepresentation extends BaseRepresentation {
    @JsonProperty("data")
    private ProductGroupInfo data;
}
