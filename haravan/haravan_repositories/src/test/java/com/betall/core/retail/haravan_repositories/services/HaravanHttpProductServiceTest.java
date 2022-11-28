package com.betall.core.retail.haravan_repositories.services;

import com.betall.core.retail.haravan_product_context.models.HaravanProductInfo;
import com.betall.core.retail.haravan_product_context.representations.HaravanProductRepresentation;
import com.betall.core.retail.haravan_product_context.representations.HaravanProductsRepresentation;
import com.betall.core.retail.shared_kernels.exceptions.HaravanRequestException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.net.URI;
import java.util.List;
import java.net.URISyntaxException;
import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

import com.betall.core.retail.shared_kernels.configs.HaravanServiceInfo;
import com.betall.core.retail.haravan_repositories.HaravanHttpProductDataFactory;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

class HaravanHttpProductServiceTest {
    private RestTemplate restTemplate = new RestTemplate();
    private HaravanHttpProductService service = new HaravanHttpProductService();

    private MockRestServiceServer mockServer;
    private ObjectMapper mapper = new ObjectMapper();

    private HaravanServiceInfo serviceInfo = new HaravanServiceInfo();

    private HaravanProductsRepresentation representations = HaravanHttpProductDataFactory.initProductRepresentation(5);
    private HaravanProductsRepresentation representationsNoData = HaravanHttpProductDataFactory.initProductRepresentation(0);
    private HaravanProductRepresentation representation = HaravanHttpProductDataFactory.initProductRepresentation();
    private HaravanProductRepresentation representationNoData = HaravanHttpProductDataFactory.initProductRepresentationNoData();

    @BeforeEach
    void init() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
        ReflectionTestUtils.setField(service, "restTemplate", restTemplate);
        serviceInfo = HaravanServiceInfo.builder()
            .accessToken("sdfasdfasdfdfasfgdfgsdfgfhdghsadf")
            .productsEndpoint("https://apis.haravan.com/com/products.json")
            .productEndpoint("https://apis.haravan.com/com/products/%s.json")
            .build();
    }

    private void initServer(final String endpoint, final HttpStatus status, final Object response) throws URISyntaxException, JsonProcessingException {
        mockServer.expect(
            ExpectedCount.once(),
            requestTo(new URI(endpoint)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(status)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(mapper.writeValueAsString(response))
                );
    }

    private void initServerResponseBodyNull(final String endpoint, final HttpStatus status) throws URISyntaxException {
        mockServer.expect(
            ExpectedCount.once(),
            requestTo(new URI(endpoint)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(status)
                    .contentType(MediaType.APPLICATION_JSON)
                );
    }

    @Test
    void testGetAllProducts() throws URISyntaxException, JsonProcessingException {
        initServer(serviceInfo.getProductsEndpoint(), HttpStatus.OK, representations);

        final List<HaravanProductInfo> responses = service.getAllProducts(serviceInfo);

        mockServer.verify();
        assertNotNull(responses);
        assertEquals(5, responses.size());
    }

    @Test
    void testGetAllProductsWithHttpNotSuccess() throws URISyntaxException {
        initServerResponseBodyNull(serviceInfo.getProductsEndpoint(), HttpStatus.UNAUTHORIZED);

        final HaravanRequestException exception = assertThrows(
            HaravanRequestException.class,
            () -> service.getAllProducts(serviceInfo)
        );
        assertTrue(exception.getMessage().contains("Request products exception"));
    }

    @Test
    void testGetAllProductsWithHttpNotOK() throws URISyntaxException {
        initServerResponseBodyNull(serviceInfo.getProductsEndpoint(), HttpStatus.CREATED);

        final List<HaravanProductInfo> responses = service.getAllProducts(serviceInfo);
        mockServer.verify();
        assertNotNull(responses);
        assertEquals(0, responses.size());
    }

    @Test
    void testGetAllProductsWithBodyNull() throws URISyntaxException {
        initServerResponseBodyNull(serviceInfo.getProductsEndpoint(), HttpStatus.OK);

        final List<HaravanProductInfo> responses = service.getAllProducts(serviceInfo);
        mockServer.verify();
        assertNotNull(responses);
        assertEquals(0, responses.size());
    }

    @Test
    void testGetAllProductsNoData() throws URISyntaxException, JsonProcessingException {
        initServer(serviceInfo.getProductsEndpoint(), HttpStatus.OK, representationsNoData);

        final List<HaravanProductInfo> responses = service.getAllProducts(serviceInfo);

        mockServer.verify();
        assertNotNull(responses);
        assertEquals(0, responses.size());
    }

    @Test
    void testGetProductById() throws URISyntaxException, JsonProcessingException {
        initServer(String.format(serviceInfo.getProductEndpoint(), 1), HttpStatus.OK, representation);

        final Optional<HaravanProductInfo> response = service.getProductById(serviceInfo, 1L);

        mockServer.verify();
        assertNotNull(response);
        assertTrue(response.isPresent());
    }

    @Test
    void testGetProductByIdWithHttpNotSuccess() throws URISyntaxException {
        initServerResponseBodyNull(String.format(serviceInfo.getProductEndpoint(), 1), HttpStatus.UNAUTHORIZED);

        final HaravanRequestException exception = assertThrows(
                HaravanRequestException.class,
                () -> service.getProductById(serviceInfo, 1L)
        );
        assertTrue(exception.getMessage().contains("Request product exception"));
    }

    @Test
    void testGetProductByIdWithHttpNotOK() throws URISyntaxException, JsonProcessingException {
        initServerResponseBodyNull(String.format(serviceInfo.getProductEndpoint(), 1), HttpStatus.CREATED);

        final Optional<HaravanProductInfo> response = service.getProductById(serviceInfo, 1L);
        mockServer.verify();
        assertNotNull(response);
        assertTrue(response.isEmpty());
    }

    @Test
    void testGetProductByIdNoData() throws URISyntaxException {
        initServerResponseBodyNull(String.format(serviceInfo.getProductEndpoint(), 1), HttpStatus.OK);

        final Optional<HaravanProductInfo> response = service.getProductById(serviceInfo, 1L);

        mockServer.verify();
        assertNotNull(response);
        assertTrue(response.isEmpty());
    }
}
