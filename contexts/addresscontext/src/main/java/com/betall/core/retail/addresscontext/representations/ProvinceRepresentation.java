package com.betall.core.retail.addresscontext.representations;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.betall.core.retail.addresscontext.models.ProvinceInfo;

import com.betall.core.retail.shared_kernels.responses.BaseRepresentation;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ProvinceRepresentation extends BaseRepresentation {
    @JsonProperty("data")
    private ProvinceInfo data;
}
