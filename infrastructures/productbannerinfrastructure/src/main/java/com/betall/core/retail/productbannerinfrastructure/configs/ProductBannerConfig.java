package com.betall.core.retail.productbannerinfrastructure.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.betall.core.retail.productbannerinfrastructure.services.ReadProductBannerService;
import com.betall.core.retail.productbannerinfrastructure.services.WriteProductBannerService;

import com.betall.core.retail.productbannercontext.repositories.QueryProductBannerRepository;
import com.betall.core.retail.productbannercontext.repositories.CommandProductBannerRepository;

import com.betall.core.retail.productbannerinfrastructure.repositories.ReadProductBannerRepository;
import com.betall.core.retail.productbannerinfrastructure.repositories.WriteProductBannerRepository;

@Configuration
@EntityScan("com.betall.core.retail.productbannerinfrastructure.models")
@EnableJpaRepositories("com.betall.core.retail.productbannerinfrastructure.repositories")
public class ProductBannerConfig {
    @Bean
    public QueryProductBannerRepository queryProductBannerRepository(final ReadProductBannerRepository query) {
        return new ReadProductBannerService(query);
    }

    @Bean
    public CommandProductBannerRepository commandProductBannerRepository(final WriteProductBannerRepository command, final ReadProductBannerRepository query) {
        return new WriteProductBannerService(command, query);
    }
}
