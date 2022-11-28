package com.betall.core.retail.haravan_repositories.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;

import com.betall.core.retail.shared_kernels.configs.HaravanServiceInfo;

import com.betall.core.retail.haravanaddresscontext.representations.HaravanWardsRepresentation;
import com.betall.core.retail.haravanaddresscontext.representations.HaravanDistrictsRepresentation;
import com.betall.core.retail.haravanaddresscontext.representations.HaravanProvincesRepresentation;

import com.betall.core.retail.haravan_repositories.repositories.HaravanHttpAddressRepository;

import com.betall.core.retail.shared_kernels.exceptions.HaravanRequestException;

@Slf4j
public class HaravanHttpAddressService implements HaravanHttpAddressRepository {
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public HaravanProvincesRepresentation getAllProvinces(final HaravanServiceInfo serviceInfo) {
        final HttpEntity<Void> httpEntity = new HttpEntity<>(serviceInfo.getHttpHeaders());
        try {
            final ResponseEntity<HaravanProvincesRepresentation> response = restTemplate.exchange(
                serviceInfo.getProvincesEndpoint(),
                HttpMethod.GET,
                httpEntity,
                HaravanProvincesRepresentation.class
            );

            if (response.getStatusCode() == HttpStatus.OK) {
                final HaravanProvincesRepresentation provincesRepresentation = response.getBody();
                if (provincesRepresentation != null) {
                    return provincesRepresentation;
                }
            }

            return HaravanProvincesRepresentation.builder().build();
        } catch(HttpClientErrorException e) {
            log.info("Request provinces exception => {}", e.getMessage());
            throw new HaravanRequestException(String.format("Request provinces exception => %s", e.getMessage()));
        }
    }

    @Override
    public HaravanDistrictsRepresentation getAllDistrictsByProvince(final HaravanServiceInfo serviceInfo, final Integer provinceId) {
        final HttpEntity<Void> httpEntity = new HttpEntity<>(serviceInfo.getHttpHeaders());
        try {
            final ResponseEntity<HaravanDistrictsRepresentation> response = restTemplate.exchange(
                String.format(serviceInfo.getDistrictsEndpoint(), provinceId),
                HttpMethod.GET,
                httpEntity,
                HaravanDistrictsRepresentation.class
            );

            if (response.getStatusCode() == HttpStatus.OK) {
                final HaravanDistrictsRepresentation districtsRepresentation = response.getBody();
                if (districtsRepresentation != null) {
                    return districtsRepresentation;
                }
            }

            return HaravanDistrictsRepresentation.builder().build();
        } catch(HttpClientErrorException e) {
            log.info("Request districts exception => {}", e.getMessage());
            throw new HaravanRequestException(String.format("Request districts exception => %s", e.getMessage()));
        }
    }

    @Override
    public HaravanWardsRepresentation getAllWardsByDistrictId(final HaravanServiceInfo serviceInfo, final Integer districtId) {
        final HttpEntity<Void> httpEntity = new HttpEntity<>(serviceInfo.getHttpHeaders());
        try {
            final ResponseEntity<HaravanWardsRepresentation> response = restTemplate.exchange(
                String.format(serviceInfo.getWardsEndpoint(), districtId),
                HttpMethod.GET,
                httpEntity,
                HaravanWardsRepresentation.class
            );

            if (response.getStatusCode() == HttpStatus.OK) {
                final HaravanWardsRepresentation wardsRepresentation = response.getBody();
                if (wardsRepresentation != null) {
                    return wardsRepresentation;
                }
            }

            return HaravanWardsRepresentation.builder().build();
        } catch(HttpClientErrorException e) {
            log.info("Request wards exception => {}", e.getMessage());
            throw new HaravanRequestException(String.format("Request wards exception => %s", e.getMessage()));
        }
    }
}
