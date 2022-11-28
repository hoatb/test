package com.betall.core.retail.haravanordercontext.models;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import com.google.gson.Gson;

import com.betall.core.retail.shared_kernels.exceptions.DataViolationException;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefundInfo {
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("note")
    private String note;

    @JsonProperty("refund_line_items")
    private List<RefundLineItemInfo> refundLineItems;

    @JsonProperty("restock")
    private Boolean restock;

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("order_id")
    private Long orderId;

    @JsonProperty("location_id")
    private Long locationId;

    @JsonProperty("transactions")
    private List<TransactionInfo> transactions;

    public void validate() {
        if (id == null || id <= 0) {
            throw new DataViolationException("RefundInfo id must be greater than 0.");
        }

        if (orderId == null || orderId <= 0) {
            throw new DataViolationException("RefundInfo orderId must be greater than 0.");
        }
    }

    public String toString() {
        return new Gson().toJson(this);
    }
}
