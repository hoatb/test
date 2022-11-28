package com.betall.core.retail.haravanordercontext.models;

import com.google.gson.Gson;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientDetails {
    @JsonProperty("accept_language")
    private String acceptLanguage;

    @JsonProperty("browser_ip")
    private String browserIp;

    @JsonProperty("session_hash")
    private String sessionHash;

    @JsonProperty("user_agent")
    private String userAgent;

    @JsonProperty("browser_height")
    private Double browserHeight;

    @JsonProperty("browser_width")
    private Double browserWidth;

    public String toString() {
        return new Gson().toJson(this);
    }
}
