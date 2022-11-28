package com.betall.core.retail.productinfrastructure.services;

import com.betall.core.retail.productcontext.models.ProductInfoList;
import com.betall.core.retail.productcontext.requests.OrderProductList;
import com.betall.core.retail.productinfrastructure.ProductInfrastructureDataFactory;
import com.betall.core.retail.productinfrastructure.models.Product;
import com.betall.core.retail.productinfrastructure.models.ProductColor;
import com.betall.core.retail.productinfrastructure.repositories.ReadProductColorRepository;
import com.betall.core.retail.shared_kernels.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReadProductColorServiceTest {
    private ReadProductColorService service;
    private ReadProductColorRepository query;

    private List<Product> productList = ProductInfrastructureDataFactory.initProductList(5L);
    private ProductColor productColor = productList.get(0).getProductColors().iterator().next();
    private OrderProductList orderProductList = ProductInfrastructureDataFactory.initOrderProductList(5L);

    @BeforeEach
    void setUp(){
        query = mock(ReadProductColorRepository.class);
        service = mock(ReadProductColorService.class, withSettings().useConstructor(query));

        when(service.findProductListById(any(OrderProductList.class))).thenCallRealMethod();
        when(query.findByProductIdAndColorId(anyLong(), anyLong())).thenReturn(Optional.of(productColor));
    }

    @Test
    void testFindProductListById() {
        ProductInfoList response = service.findProductListById(orderProductList);
        assertNotNull(response);
        assertNull(response.getTotal());
        assertNotNull(response.getLstProduct());
        assertFalse(response.getLstProduct().isEmpty());
    }

    @Test
    void testFindProductListByIdWithNotFoundOneProduct() {
        when(query.findByProductIdAndColorId(anyLong(), anyLong())).thenReturn(Optional.empty());
        ResourceNotFoundException exception = assertThrows(
            ResourceNotFoundException.class,
            () -> service.findProductListById(orderProductList),
            String.format(
                "Not Found productId %s has colorId %s",
                orderProductList.getLstOrderProduct().get(0).getProductId(),
                orderProductList.getLstOrderProduct().get(0).getColorId())
        );
        assertTrue(exception.getMessage().contains(String.format(
            "Not Found productId %s has colorId %s",
            orderProductList.getLstOrderProduct().get(0).getProductId(),
            orderProductList.getLstOrderProduct().get(0).getColorId()))
        );
    }
}
