package com.betall.core.retail.haravanordercontext.services;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import com.betall.core.retail.haravanordercontext.models.OrderInfo;
import com.betall.core.retail.haravanordercontext.repositories.QueryHaravanOrderRepository;

import com.betall.core.retail.shared_kernels.exceptions.ResourceNotFoundException;

public class QueryHaravanOrderService {
    private QueryHaravanOrderRepository query;

    /**
     * Constructor
     *
     * @param query
     */
    public QueryHaravanOrderService(final QueryHaravanOrderRepository query) {
        this.query = query;
    }

    /**
     * Find all products
     *
     * @return List<ProductInfo>
     */
    public List<OrderInfo> findAllOrders() {
        return query.findAllOrders();
    }

    /**
     * Lookup order by id
     *
     * @param id
     * @return OrderInfo
     */
    public OrderInfo findOrderById(final Long id) {
        final OrderInfo order = query.findOrderById(id).orElseThrow(
            () -> new ResourceNotFoundException(String.format("Order not found with id %s", id))
        );

        return order;
    }
}
