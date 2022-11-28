package com.betall.core.retail.haravanretail.configs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.betall.core.retail.productcontext.requests.OrderProductInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;

import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import com.betall.core.retail.productcontext.requests.OrderProductList;
import org.springframework.util.MultiValueMap;

public class OrderProductListConverter extends AbstractHttpMessageConverter<OrderProductList> {
    private static final FormHttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected boolean supports(final Class<?> clazz) {
        return (OrderProductList.class == clazz);
    }

    @Override
    protected OrderProductList readInternal(final Class<? extends OrderProductList> clazz, final HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        final MultiValueMap<String, String> formBodyMap = formHttpMessageConverter.read(null, inputMessage);
        final String formAsJson = formBodyMap.toString();
        final boolean isJson = isValidJson(formAsJson);
        if (isJson) {
            return new Gson().fromJson(formAsJson, OrderProductList.class);
        }

        return toOrderProductList(formBodyMap.toSingleValueMap());
    }

    @Override
    protected void writeInternal(final OrderProductList orderProductList, final HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {

    }

    private boolean isValidJson(final String json) {
        try {
            JsonParser.parseString(json);
        } catch (JsonSyntaxException e) {
            return false;
        }
        return true;
    }

    private OrderProductList toOrderProductList(final Map<String, String> formBodyMap) {
        Integer numOfFields = 0;
        OrderProductInfo orderProductInfo = OrderProductInfo.builder().build();
        final List<OrderProductInfo> lstOrderProduct = new ArrayList<>();
        for(Map.Entry<String, String> entry : formBodyMap.entrySet()) {
            final String suffix = entry.getKey().substring(0, entry.getKey().indexOf("."));
            if (numOfFields >= 2) {
                lstOrderProduct.add(orderProductInfo);

                orderProductInfo = OrderProductInfo.builder().build();
                numOfFields = 0;
            }
            final String key = entry.getKey().substring(suffix.length() + 1);
            if ("colorId".equals(key)) {
                orderProductInfo.setColorId(Long.valueOf(entry.getValue()));
            } else if ("productId".equals(key)) {
                orderProductInfo.setProductId(Long.valueOf(entry.getValue()));
            }
            numOfFields++;
        }
        if (numOfFields > 0) {
            lstOrderProduct.add(orderProductInfo);
        }

        return OrderProductList.builder().lstOrderProduct(lstOrderProduct).build();
    }
}
