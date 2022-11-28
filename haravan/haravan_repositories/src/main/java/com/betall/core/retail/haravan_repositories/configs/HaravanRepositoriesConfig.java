package com.betall.core.retail.haravan_repositories.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.client.RestTemplate;

import com.betall.core.retail.haravan_repositories.services.HaravanHttpProductService;
import com.betall.core.retail.haravan_repositories.services.HaravanHttpAddressService;

import com.betall.core.retail.haravan_repositories.repositories.HaravanHttpAddressRepository;
import com.betall.core.retail.haravan_repositories.repositories.HaravanHttpProductRepository;

@Configuration
public class HaravanRepositoriesConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public HaravanHttpProductRepository haravanHttpProductRepository() {
        return new HaravanHttpProductService();
    }

    @Bean
    public HaravanHttpAddressRepository haravanHttpAddressRepository() {
        return new HaravanHttpAddressService();
    }
}
