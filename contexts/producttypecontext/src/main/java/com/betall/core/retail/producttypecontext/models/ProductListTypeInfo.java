package com.betall.core.retail.producttypecontext.models;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductListTypeInfo {
    @JsonProperty("lstType")
    private List<ProductTypeInfo> lstType;
}
