package com.betall.core.retail.haravanretail.configs;

import com.betall.core.retail.productcontext.repositories.QueryProductColorRepository;
import com.betall.core.retail.productcontext.services.CommandProductService;
import com.betall.core.retail.productcontext.services.QueryProductColorService;
import com.betall.core.retail.productcontext.services.QueryProductService;
import com.betall.core.retail.productinfrastructure.configs.ProductInfrastuctureConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Configuration;

import com.betall.core.retail.producttypeinfrastructure.configs.ProductTypeConfig;
import com.betall.core.retail.productgroupinfrastructure.configs.ProductGroupConfig;
import com.betall.core.retail.productbannerinfrastructure.configs.ProductBannerConfig;
import com.betall.core.retail.haravan_repositories.configs.HaravanRepositoriesConfig;
import com.betall.core.retail.addressinfrastructure.configs.AddressInfrastructureConfig;
import com.betall.core.retail.haravan_infrastructure.configs.HaravanInfrastructureConfig;

import com.betall.core.retail.haravan_product_context.services.QueryHaravanProductService;

import com.betall.core.retail.addresscontext.services.QueryAddressService;
import com.betall.core.retail.addresscontext.services.QueryProvinceService;
import com.betall.core.retail.addresscontext.services.CommandAddressService;
import com.betall.core.retail.addresscontext.services.CommandProvinceService;

import com.betall.core.retail.productgroupcontext.services.QueryProductGroupService;
import com.betall.core.retail.productgroupcontext.services.CommandProductGroupService;

import com.betall.core.retail.productbannercontext.services.QueryProductBannerService;
import com.betall.core.retail.productbannercontext.services.CommandProductBannerService;

import com.betall.core.retail.producttypecontext.services.QueryProductTypeService;
import com.betall.core.retail.producttypecontext.services.CommandProductTypeService;

import com.betall.core.retail.haravanaddresscontext.services.QueryHaravanAddressService;

import com.betall.core.retail.haravan_product_context.repositories.QueryHaravanProductRepository;

import com.betall.core.retail.addresscontext.repositories.QueryAddressRepository;
import com.betall.core.retail.addresscontext.repositories.QueryProvinceRepository;
import com.betall.core.retail.addresscontext.repositories.CommandAddressRepository;
import com.betall.core.retail.addresscontext.repositories.CommandProvinceRepository;

import com.betall.core.retail.productgroupcontext.repositories.QueryProductGroupRepository;
import com.betall.core.retail.productgroupcontext.repositories.CommandProductGroupRepository;

import com.betall.core.retail.productbannercontext.repositories.QueryProductBannerRepository;
import com.betall.core.retail.productbannercontext.repositories.CommandProductBannerRepository;

import com.betall.core.retail.producttypecontext.repositories.QueryProductTypeRepository;
import com.betall.core.retail.producttypecontext.repositories.CommandProductTypeRepository;

import com.betall.core.retail.haravanaddresscontext.repositories.QueryHaravanAddressRepository;

import com.betall.core.retail.productcontext.repositories.CommandProductRepository;
import com.betall.core.retail.productcontext.repositories.QueryProductRepository;

@Configuration
@Import({
    HaravanInfrastructureConfig.class,
    HaravanRepositoriesConfig.class,
    ProductGroupConfig.class,
    ProductBannerConfig.class,
    ProductTypeConfig.class,
    AddressInfrastructureConfig.class,
    ProductInfrastuctureConfig.class
})
public class ServiceConfig {
    @Bean
    public QueryHaravanProductService queryHaravanProductService(final QueryHaravanProductRepository query) {
        return new QueryHaravanProductService(query);
    }

    @Bean
    public QueryHaravanAddressService queryHaravanAddressService(final QueryHaravanAddressRepository query) {
        return new QueryHaravanAddressService(query);
    }

    /**
     * Initial query product group service instance.
     * <p>
     * Inject query repository during instantiate query service.
     *
     * @return QueryProductGroupService
     */
    @Bean
    public QueryProductGroupService queryProductGroupService(final QueryProductGroupRepository query) {
        return new QueryProductGroupService(query);
    }

    /**
     * Initial command product group service instance.
     * <p>
     * Inject command repository and query repository during instantiate command service.
     *
     * @return CommandProductGroupService
     */
    @Bean
    public CommandProductGroupService commandProductGroupService(final CommandProductGroupRepository command) {
        return new CommandProductGroupService(command);
    }

    /**
     * Initial query product banner service instance.
     * <p>
     * Inject query repository during instantiate query service.
     *
     * @return QueryProductBannerService
     */
    @Bean
    public QueryProductBannerService queryProductBannerService(final QueryProductBannerRepository query) {
        return new QueryProductBannerService(query);
    }

    /**
     * Initial command product banner service instance.
     * <p>
     * Inject command repository and query repository during instantiate command service.
     *
     * @return CommandProductBannerService
     */
    @Bean
    public CommandProductBannerService commandProductBannerService(final CommandProductBannerRepository command) {
        return new CommandProductBannerService(command);
    }

    /**
     * Initial query product type service instance.
     * <p>
     * Inject query repository during instantiate query service.
     *
     * @return QueryProductTypeService
     */
    @Bean
    public QueryProductTypeService queryProductTypeService(final QueryProductTypeRepository query) {
        return new QueryProductTypeService(query);
    }

    /**
     * Initial command product type service instance.
     * <p>
     * Inject command repository and query repository during instantiate command service.
     *
     * @return CommandProductTypeService
     */
    @Bean
    public CommandProductTypeService commandProductTypeService(final CommandProductTypeRepository command) {
        return new CommandProductTypeService(command);
    }

    /**
     * Initial query province service instance.
     * <p>
     * Inject query repository during instantiate query service.
     *
     * @return QueryProvinceService
     */
    @Bean
    public QueryProvinceService queryProvinceService(final QueryProvinceRepository query) {
        return new QueryProvinceService(query);
    }

    /**
     * Initial command province service instance.
     * <p>
     * Inject command repository and query repository during instantiate command service.
     *
     * @return CommandProvinceService
     */
    @Bean
    public CommandProvinceService commandProvinceService(final CommandProvinceRepository command) {
        return new CommandProvinceService(command);
    }

    /**
     * Initial query address service instance.
     * <p>
     * Inject query repository during instantiate query service.
     *
     * @return QueryAddressService
     */
    @Bean
    public QueryAddressService queryAddressService(final QueryAddressRepository query) {
        return new QueryAddressService(query);
    }

    /**
     * Initial command address service instance.
     * <p>
     * Inject command repository and query repository during instantiate command service.
     *
     * @return CommandAddressService
     */
    @Bean
    public CommandAddressService commandAddressService(final CommandAddressRepository command) {
        return new CommandAddressService(command);
    }

    /**
     * Initial query product service instance.
     * <p>
     * Inject query repository during instantiate query service.
     *
     * @return QueryProductService
     */
    @Bean
    public QueryProductService queryProductService(final QueryProductRepository query) {
        return new QueryProductService(query);
    }

    /**
     * Initial command product service instance.
     * <p>
     * Inject command repository and query repository during instantiate command service.
     *
     * @return CommandProductService
     */
    @Bean
    public CommandProductService commandProductService(final CommandProductRepository command) {
        return new CommandProductService(command);
    }

    /**
     * Initial query product color service instance.
     * <p>
     * Inject query repository during instantiate query service
     *
     * @return QueryProductColorService
     */
    @Bean
    public QueryProductColorService queryProductColorService(final QueryProductColorRepository query) {
        return new QueryProductColorService(query);
    }
}
