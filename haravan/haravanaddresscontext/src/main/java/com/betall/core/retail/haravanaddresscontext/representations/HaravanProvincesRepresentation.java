package com.betall.core.retail.haravanaddresscontext.representations;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import com.betall.core.retail.haravanaddresscontext.models.HaravanProvinceInfo;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HaravanProvincesRepresentation {
    @JsonProperty("provinces")
    private List<HaravanProvinceInfo> provinces;
}
