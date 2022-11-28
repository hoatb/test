package com.betall.core.retail.haravanaddresscontext.representations;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import com.betall.core.retail.haravanaddresscontext.models.HaravanDistrictInfo;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HaravanDistrictsRepresentation {
    @JsonProperty("districts")
    private List<HaravanDistrictInfo> districts;
}
