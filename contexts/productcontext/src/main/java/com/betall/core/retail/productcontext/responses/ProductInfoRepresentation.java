package com.betall.core.retail.productcontext.responses;

import com.betall.core.retail.productcontext.models.ProductInfoList;
import com.betall.core.retail.shared_kernels.responses.BaseRepresentation;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ProductInfoRepresentation extends BaseRepresentation {
    @JsonProperty("data")
    private ProductInfoList data;
}
