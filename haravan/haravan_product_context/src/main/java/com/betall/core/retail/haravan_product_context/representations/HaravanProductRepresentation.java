package com.betall.core.retail.haravan_product_context.representations;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.betall.core.retail.haravan_product_context.models.HaravanProductInfo;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HaravanProductRepresentation {
    @JsonProperty("product")
    private HaravanProductInfo haravanProduct;
}
