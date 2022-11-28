package com.betall.core.retail.productcontext.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductInfoList {
    @JsonProperty("total")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer total;

    @JsonProperty("lstProduct")
    private List<ProductInfo> lstProduct;
}
