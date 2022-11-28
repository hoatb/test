package com.betall.core.retail.productcontext.models;

import lombok.*;
import lombok.experimental.SuperBuilder;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailList {
    @JsonProperty("productsDetail")
    private List<ProductDetailInfo> productsDetail;
}
