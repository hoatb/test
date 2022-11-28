package com.betall.core.retail.productgroupcontext.models;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductListGroupInfo {
    @JsonProperty("lstGroup")
    private List<ProductGroupInfo> lstGroup;
}
