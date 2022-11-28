package com.betall.core.retail.productcontext.responses;

import lombok.*;
import lombok.experimental.SuperBuilder;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.betall.core.retail.productcontext.models.ProductDetail;

import com.betall.core.retail.shared_kernels.responses.BaseRepresentation;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailRepresentation extends BaseRepresentation {
    @JsonProperty("data")
    private ProductDetail data;
}
