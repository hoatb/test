package com.betall.core.retail.addresscontext.representations;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

import com.betall.core.retail.addresscontext.models.ProvinceInfo;

import com.betall.core.retail.shared_kernels.responses.BaseRepresentation;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ProvincesRepresentation extends BaseRepresentation {
    private List<ProvinceInfo> data;
}
