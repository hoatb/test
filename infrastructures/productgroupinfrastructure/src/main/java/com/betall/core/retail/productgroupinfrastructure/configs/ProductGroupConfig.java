package com.betall.core.retail.productgroupinfrastructure.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.betall.core.retail.productgroupinfrastructure.services.ReadProductGroupService;
import com.betall.core.retail.productgroupinfrastructure.services.WriteProductGroupService;

import com.betall.core.retail.productgroupcontext.repositories.QueryProductGroupRepository;
import com.betall.core.retail.productgroupcontext.repositories.CommandProductGroupRepository;
import com.betall.core.retail.productgroupinfrastructure.repositories.ReadProductGroupRepository;
import com.betall.core.retail.productgroupinfrastructure.repositories.WriteProductGroupRepository;

@Configuration
@EntityScan("com.betall.core.retail.productgroupinfrastructure.models")
@EnableJpaRepositories("com.betall.core.retail.productgroupinfrastructure.repositories")
public class ProductGroupConfig {
    @Bean
    public QueryProductGroupRepository queryProductGroupRepository(final ReadProductGroupRepository query) {
        return new ReadProductGroupService(query);
    }

    @Bean
    public CommandProductGroupRepository commandProductGroupRepository(final WriteProductGroupRepository command, final ReadProductGroupRepository query) {
        return new WriteProductGroupService(command, query);
    }
}
