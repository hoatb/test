package com.betall.core.retail.shared_kernels.responses;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseRepresentation {
    @JsonProperty("status")
    private Integer status;
    @JsonProperty("message")
    private String message;
}