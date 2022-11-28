package com.betall.core.retail.productcontext.requests;

import com.betall.core.retail.shared_kernels.exceptions.DataViolationException;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductInfo {
    @JsonProperty("colorId")
    private Long colorId;
    @JsonProperty("productId")
    private Long productId;

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public void validate() {
        if (productId == null) {
            throw new DataViolationException("OrderProductInfo's productId must not be null.");
        }
        if (colorId == null) {
            throw new DataViolationException("OrderProductInfo's colorId must not be null.");
        }
    }
}
