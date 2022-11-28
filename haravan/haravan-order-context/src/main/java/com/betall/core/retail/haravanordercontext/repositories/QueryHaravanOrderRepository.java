package com.betall.core.retail.haravanordercontext.repositories;

import com.betall.core.retail.haravanordercontext.models.OrderInfo;

import java.util.List;
import java.util.Optional;

public interface QueryHaravanOrderRepository {
    /**
     * Listing all orders by Haravan API
     * @return List<OrderInfo>
     */
    List<OrderInfo> findAllOrders();

    /**
     * Lookup an order by id
     * @param id
     * @return Optional<OrderInfo>
     */
    Optional<OrderInfo> findOrderById(final Long id);
}
