package com.betall.core.retail.haravanordercontext.representations;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import com.betall.core.retail.haravanordercontext.models.OrderInfo;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdersRepresentation {
    @JsonProperty("orders")
    private List<OrderInfo> orders;
}
