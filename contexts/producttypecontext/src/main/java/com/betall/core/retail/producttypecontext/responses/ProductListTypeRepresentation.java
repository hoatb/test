package com.betall.core.retail.producttypecontext.responses;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.betall.core.retail.producttypecontext.models.ProductListTypeInfo;

import com.betall.core.retail.shared_kernels.responses.BaseRepresentation;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ProductListTypeRepresentation extends BaseRepresentation {
    @JsonProperty("data")
    private ProductListTypeInfo data;
}
