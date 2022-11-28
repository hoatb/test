package com.betall.core.retail.haravan_product_context.models;


import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HaravanInventoryAdvanceInfo {
    @JsonProperty("qty_available")
    private Integer qtyAvailable;
    @JsonProperty("qty_onhand")
    private Integer qtyOnhand;
    @JsonProperty("qty_commited")
    private Integer qtyCommited;
    @JsonProperty("qty_incoming")
    private Integer qtyIncoming;
}
