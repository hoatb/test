package com.betall.core.retail.haravanordercontext.models;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.google.gson.Gson;

import com.betall.core.retail.shared_kernels.exceptions.DataViolationException;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefundLineItemInfo {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("line_item")
    private LineItemInfo lineItem;

    @JsonProperty("line_item_id")
    private Long lineItemId;

    @JsonProperty("quantity")
    private Integer quantity;

    public void validate() {
        if (id == null || id <= 0) {
            throw new DataViolationException("RefundLineItemInfo id must be greater than 0.");
        }

        if (lineItemId == null || lineItemId <= 0) {
            throw new DataViolationException("RefundLineItemInfo lineItemId must be greater than 0.");
        }

        if (quantity == null || quantity < 0) {
            throw new DataViolationException("RefundLineItemInfo quantity must be greater than or equals 0.");
        }
    }

    public String toString() {
        return new Gson().toJson(this);
    }
}
