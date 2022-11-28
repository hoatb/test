package com.betall.core.retail.productbannercontext.models;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductListBannerInfo {
    @JsonProperty("lstBanner")
    private List<ProductBannerInfo> lstBanner;
}
