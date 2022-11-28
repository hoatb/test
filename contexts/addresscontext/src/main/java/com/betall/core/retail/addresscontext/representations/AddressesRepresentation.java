package com.betall.core.retail.addresscontext.representations;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.betall.core.retail.addresscontext.models.AddressListInfo;
import com.betall.core.retail.shared_kernels.responses.BaseRepresentation;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AddressesRepresentation extends BaseRepresentation {
    @JsonProperty("data")
    private AddressListInfo data;
}
