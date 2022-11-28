package com.betall.core.retail.productcontext.models;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetail {
    @JsonProperty("productDetail")
    private ProductDetailInfo productDetailInfo;
}
