package com.betall.core.retail.haravan_repositories.services;

import com.betall.core.retail.haravan_product_context.models.HaravanProductInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.betall.core.retail.shared_kernels.configs.HaravanServiceInfo;

import com.betall.core.retail.haravan_product_context.representations.HaravanProductRepresentation;
import com.betall.core.retail.haravan_product_context.representations.HaravanProductsRepresentation;

import com.betall.core.retail.haravan_repositories.repositories.HaravanHttpProductRepository;

import com.betall.core.retail.shared_kernels.exceptions.HaravanRequestException;

@Slf4j
public class HaravanHttpProductService implements HaravanHttpProductRepository {
    @Autowired
    private RestTemplate restTemplate;

    /**
     * Get all products from http haravan
     * @param serviceInfo
     * @return List<HaravanProductInfo>
     */
    @Override
    public List<HaravanProductInfo> getAllProducts(final HaravanServiceInfo serviceInfo) {
        final HttpEntity<Void> httpEntity = new HttpEntity<>(serviceInfo.getHttpHeaders());
        try {
            final ResponseEntity<HaravanProductsRepresentation> response = restTemplate.exchange(
                serviceInfo.getProductsEndpoint(),
                HttpMethod.GET,
                httpEntity,
                HaravanProductsRepresentation.class
            );

            if (response.getStatusCode() == HttpStatus.OK) {
                final HaravanProductsRepresentation haravanProductsRepresentation = response.getBody();
                if (haravanProductsRepresentation != null) {
                    return haravanProductsRepresentation.getHaravanProducts();
                }
            }

            return new ArrayList<>();
        } catch(HttpClientErrorException e) {
            log.info("Request products exception => {}", e.getMessage());
            throw new HaravanRequestException(String.format("Request products exception => %s", e.getMessage()));
        }
    }

    /**
     * Get product by id from http haravan
     * @param serviceInfo
     * @param id
     * @return Optional<HaravanProductInfo>
     */
    @Override
    public Optional<HaravanProductInfo> getProductById(final HaravanServiceInfo serviceInfo, final Long id) {
        final HttpEntity<Void> httpEntity = new HttpEntity<>(serviceInfo.getHttpHeaders());
        try {
            final ResponseEntity<HaravanProductRepresentation> response = restTemplate.exchange(
                String.format(serviceInfo.getProductEndpoint(), id),
                HttpMethod.GET,
                httpEntity,
                HaravanProductRepresentation.class
            );

            if (response.getStatusCode() == HttpStatus.OK) {
                final HaravanProductRepresentation haravanProductRepresentation = response.getBody();
                if (haravanProductRepresentation != null) {
                    return Optional.of(haravanProductRepresentation.getHaravanProduct());
                }
            }
            return Optional.empty();
        } catch (HttpClientErrorException e) {
            log.info("Request product exception => {}", e.getMessage());
            throw new HaravanRequestException(String.format("Request product exception => %s", e.getMessage()));
        }
    }
}
