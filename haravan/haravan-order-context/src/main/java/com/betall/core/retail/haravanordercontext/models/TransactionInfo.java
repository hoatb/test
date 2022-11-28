package com.betall.core.retail.haravanordercontext.models;

import com.betall.core.retail.shared_kernels.exceptions.DataViolationException;
import com.google.gson.Gson;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionInfo {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("amount")
    private Double amount;

    @JsonProperty("authorization")
    private String authorization;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("device_id")
    private Long deviceId;

    @JsonProperty("gateway")
    private String gateway;

    @JsonProperty("kind")
    private String kind;

    @JsonProperty("order_id")
    private Long orderId;

    @JsonProperty("receipt")
    private String receipt;

    @JsonProperty("status")
    private String status;

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("location_id")
    private Long locationId;

    @JsonProperty("parent_id")
    private Long parentId;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("haravan_transaction_id")
    private Long haravanTransactionId;

    @JsonProperty("external_transaction_id")
    private Long externalTransactionId;

    public void validate() {
        if (id == null || id <= 0) {
            throw new DataViolationException("TransactionInfo id must be greater than 0.");
        }

        if (orderId == null || orderId <= 0) {
            throw new DataViolationException("TransactionInfo orderId must be greater than 0.");
        }

        if (amount == null || amount < 0) {
            throw new DataViolationException("TransactionInfo amount must be greater than or equals 0.");
        }
    }

    public String toString() {
        return new Gson().toJson(this);
    }
}
