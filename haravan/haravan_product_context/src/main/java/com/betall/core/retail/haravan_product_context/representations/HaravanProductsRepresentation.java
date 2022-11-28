package com.betall.core.retail.haravan_product_context.representations;

import com.betall.core.retail.haravan_product_context.models.HaravanProductInfo;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HaravanProductsRepresentation {
    @JsonProperty("products")
    private List<HaravanProductInfo> haravanProducts;
}
