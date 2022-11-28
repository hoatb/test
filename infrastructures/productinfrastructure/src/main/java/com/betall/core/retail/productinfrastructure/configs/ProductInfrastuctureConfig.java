package com.betall.core.retail.productinfrastructure.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.betall.core.retail.productcontext.repositories.QueryProductRepository;
import com.betall.core.retail.productcontext.repositories.CommandProductRepository;

import com.betall.core.retail.productinfrastructure.repositories.ReadProductRepository;
import com.betall.core.retail.productinfrastructure.repositories.WriteProductRepository;

import com.betall.core.retail.productcontext.repositories.QueryProductColorRepository;
import com.betall.core.retail.productinfrastructure.repositories.ReadProductColorRepository;

import com.betall.core.retail.productinfrastructure.services.ReadProductService;
import com.betall.core.retail.productinfrastructure.services.WriteProductService;
import com.betall.core.retail.productinfrastructure.services.ReadProductColorService;

@Configuration
@EntityScan("com.betall.core.retail.productinfrastructure.models")
@EnableJpaRepositories("com.betall.core.retail.productinfrastructure.repositories")
public class ProductInfrastuctureConfig {
    @Bean
    public QueryProductRepository queryProductRepository(final ReadProductRepository query) {
        return new ReadProductService(query);
    }

    @Bean
    public CommandProductRepository commandProductRepository(final WriteProductRepository command, final ReadProductRepository query) {
        return new WriteProductService(command, query);
    }

    @Bean
    public QueryProductColorRepository queryProductColorRepository(final ReadProductColorRepository query) {
        return new ReadProductColorService(query);
    }
}
