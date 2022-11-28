package com.betall.core.retail.haravanordercontext.representations;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.betall.core.retail.haravanordercontext.models.OrderInfo;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRepresentation {
    @JsonProperty("order")
    private OrderInfo order;
}
