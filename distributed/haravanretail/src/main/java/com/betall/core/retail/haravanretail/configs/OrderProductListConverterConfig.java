package com.betall.core.retail.haravanretail.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;

import com.betall.core.retail.productcontext.requests.OrderProductList;

@Configuration
public class OrderProductListConverterConfig {
    @Bean
    public AbstractHttpMessageConverter<OrderProductList> createOrderProductListConverter() {
        final AbstractHttpMessageConverter<OrderProductList> orderProductListConverter = new OrderProductListConverter();
        orderProductListConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_FORM_URLENCODED));
        return orderProductListConverter;
    }
}