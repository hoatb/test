package com.betall.core.retail.haravanaddresscontext.representations;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import com.betall.core.retail.haravanaddresscontext.models.HaravanWardInfo;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HaravanWardsRepresentation {
    @JsonProperty("wards")
    private List<HaravanWardInfo> wards;
}
