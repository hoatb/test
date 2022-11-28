package com.betall.core.retail.addressinfrastructure.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.betall.core.retail.addressinfrastructure.services.ReadAddressService;
import com.betall.core.retail.addressinfrastructure.services.WriteAddressService;
import com.betall.core.retail.addressinfrastructure.services.ReadProvinceService;
import com.betall.core.retail.addressinfrastructure.services.WriteProvinceService;

import com.betall.core.retail.addresscontext.repositories.QueryAddressRepository;
import com.betall.core.retail.addresscontext.repositories.QueryProvinceRepository;
import com.betall.core.retail.addresscontext.repositories.CommandAddressRepository;
import com.betall.core.retail.addresscontext.repositories.CommandProvinceRepository;

import com.betall.core.retail.addressinfrastructure.repositories.ReadAddressRepository;
import com.betall.core.retail.addressinfrastructure.repositories.WriteAddressRepository;
import com.betall.core.retail.addressinfrastructure.repositories.ReadProvinceRepository;
import com.betall.core.retail.addressinfrastructure.repositories.WriteProvinceRepository;

@Configuration
@EntityScan("com.betall.core.retail.addressinfrastructure.models")
@EnableJpaRepositories("com.betall.core.retail.addressinfrastructure.repositories")
public class AddressInfrastructureConfig {
    @Bean
    public QueryProvinceRepository queryProvinceRepository(final ReadProvinceRepository query) {
        return new ReadProvinceService(query);
    }

    @Bean
    public CommandProvinceRepository commandProvinceRepository(final WriteProvinceRepository command, final ReadProvinceRepository query) {
        return new WriteProvinceService(command, query);
    }

    @Bean
    public QueryAddressRepository queryAddressRepository(final ReadAddressRepository query) {
        return new ReadAddressService(query);
    }

    @Bean
    public CommandAddressRepository commandAddressRepository(final WriteAddressRepository command, final ReadAddressRepository query) {
        return new WriteAddressService(command, query);
    }
}
