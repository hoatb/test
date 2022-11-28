package com.betall.core.retail.productbannercontext.responses;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.betall.core.retail.shared_kernels.responses.BaseRepresentation;
import com.betall.core.retail.productbannercontext.models.ProductBannerInfo;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ProductBannerRepresentation extends BaseRepresentation {
    @JsonProperty("data")
    private ProductBannerInfo data;
}
