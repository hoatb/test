package com.betall.core.retail.haravan_infrastructure.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Configuration;

import com.betall.core.retail.shared_kernels.configs.HaravanServiceInfo;

import com.betall.core.retail.haravan_repositories.configs.HaravanRepositoriesConfig;

import com.betall.core.retail.haravan_infrastructure.services.HaravanAddressService;
import com.betall.core.retail.haravan_infrastructure.services.HaravanProductService;

import com.betall.core.retail.haravanaddresscontext.repositories.QueryHaravanAddressRepository;
import com.betall.core.retail.haravan_product_context.repositories.QueryHaravanProductRepository;

@Configuration
@Import({HaravanServiceInfo.class, HaravanRepositoriesConfig.class})
public class HaravanInfrastructureConfig {
    @Bean
    public QueryHaravanProductRepository queryHaravanProductRepository() {
        return new HaravanProductService();
    }

    @Bean
    public QueryHaravanAddressRepository queryHaravanAddressRepository() {
        return new HaravanAddressService();
    }
}
