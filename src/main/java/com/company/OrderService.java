package com.company;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Service class that contains all the business logic for adding and fetching orders.
 * @author Frank Karlstrøm - frank.karlstrom@gmail.com - fk@databaseforum.no
 */
class OrderService {
    // TODO we need a repository/map/collection/database of some kind in this service now.

    void addOrder(Order order) {
        // TODO the incoming order, should probably be stored in the repository thingy mentioned above.
    }

    Order getOrder(BigDecimal orderId) {
        //TODO this will not work anymore, as the tests do now use random dates.
        // TODO if you implement the repository/map/list/db as mentioned above, this method will be quite simpler.
        LocalDateTime dateTime = LocalDateTime.of(2016, 5, 20, 19, 35, 0, 0);
        Order order = new Order(12, dateTime);
        if (order.getId().equals(orderId)) {
            return order;
        } else {
            return null;
        }
    }
    /** Should get ALL the orders that have been added to this Service.*/
    List<Order> getAllOrders() {
        return null;
    }
}
