package com.company;

import java.math.BigDecimal;
import java.util.*;

/**
 * Service class that contains all the business logic for adding and fetching orders.
 * @author Frank Karlstrøm - frank.karlstrom@gmail.com - fk@databaseforum.no
 */
class OrderService {
    private Map<BigDecimal, Order> orders = new HashMap<>();

    void addOrder(Order order) {
        this.orders.put(order.getId(), order);
    }

    Order getOrder(BigDecimal orderId) {
        return orders.get(orderId);
    }
    /** Should get ALL the orders that have been added to this Service.*/
    List<Order> getAllOrders() {
        return new ArrayList<>(orders.values());
    }
}
