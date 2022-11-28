package com.betall.core.retail.haravanretail.exceptions;

import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

@Getter
@Setter
public class ApiError {
    @Getter
    @Setter
    private class Metadata {
        @JsonProperty("created_date")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
        private LocalDateTime createdDate;
    }

    private Integer status;
    private String message;
    private Metadata metadata;

    public ApiError(final Integer status, final String message) {
        this.status = status;
        this.message = message;
        this.metadata = new Metadata();
        this.metadata.setCreatedDate(LocalDateTime.now());
    }
}
