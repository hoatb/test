package com.betall.core.retail.shared_kernels.configs;

import lombok.*;

import org.springframework.context.annotation.Configuration;
import org.springframework.boot.context.properties.ConfigurationProperties;

import org.springframework.http.HttpHeaders;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Configuration
@ConfigurationProperties(prefix = "haravan")
public class HaravanServiceInfo {
    private String accessToken;
    private String productsEndpoint;
    private String productEndpoint;
    private String provincesEndpoint;
    private String districtsEndpoint;
    private String wardsEndpoint;

    public HttpHeaders getHttpHeaders() {
        final HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        return headers;
    }
}
