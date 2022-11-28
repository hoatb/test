package com.betall.core.retail.productcontext.requests;

import com.betall.core.retail.shared_kernels.exceptions.DataViolationException;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductList {
    @JsonProperty("lstOrderProduct")
    private List<OrderProductInfo> lstOrderProduct;

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public void validate() {
        if (lstOrderProduct == null || lstOrderProduct.isEmpty()) {
            throw new DataViolationException("OrderProductList's lstOrderProduct must not be null or empty.");
        }

        for (OrderProductInfo orderProductInfo : lstOrderProduct) {
            orderProductInfo.validate();
        }
    }
}
