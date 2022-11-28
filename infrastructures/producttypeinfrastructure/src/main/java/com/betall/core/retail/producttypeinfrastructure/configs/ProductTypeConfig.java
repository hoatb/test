package com.betall.core.retail.producttypeinfrastructure.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.betall.core.retail.producttypeinfrastructure.services.ReadProductTypeService;
import com.betall.core.retail.producttypeinfrastructure.services.WriteProductTypeService;

import com.betall.core.retail.producttypecontext.repositories.QueryProductTypeRepository;
import com.betall.core.retail.producttypecontext.repositories.CommandProductTypeRepository;

import com.betall.core.retail.producttypeinfrastructure.repositories.ReadProductTypeRepository;
import com.betall.core.retail.producttypeinfrastructure.repositories.WriteProductTypeRepository;

@Configuration
@EntityScan("com.betall.core.retail.producttypeinfrastructure.models")
@EnableJpaRepositories("com.betall.core.retail.producttypeinfrastructure.repositories")
public class ProductTypeConfig {
    @Bean
    public QueryProductTypeRepository queryProductTypeRepository(final ReadProductTypeRepository query) {
        return new ReadProductTypeService(query);
    }

    @Bean
    public CommandProductTypeRepository commandProductTypeRepository(final WriteProductTypeRepository command, final ReadProductTypeRepository query) {
        return new WriteProductTypeService(command, query);
    }
}
