package com.betall.core.retail.addresscontext.models;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import com.betall.core.retail.addresscontext.responses.AddressResponse;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressListInfo {
    @JsonProperty("lstAddress")
    private List<AddressResponse> lstAddress;
}
