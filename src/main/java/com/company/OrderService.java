package com.company;

import java.math.BigDecimal;
import java.util.*;

/**
 * Service class that contains all the business logic for adding and fetching orders.
 * @author Frank Karlstrøm - frank.karlstrom@gmail.com - fk@databaseforum.no
 */
class OrderService {
    // TODO we need a repository/map/collection/database of some kind in this service now.
    private Map<Order, Integer> orders = new LinkedHashMap<Order, Integer>();

    void addOrder(Order order) {
        // TODO the incoming order, should probably be stored in the repository thingy mentioned above.
        this.orders.put(order,1);
    }

    Order getOrder(BigDecimal orderId) {
        //TODO this will not work anymore, as the tests do now use random dates.
        // TODO if you implement the repository/map/list/db as mentioned above, this method will be quite simpler.
        for (Order order : this.orders.keySet()) {
            order.getId();
            if(order.getId().equals(orderId)){
                return order;
            }
        }
        return null;
    }
    /** Should get ALL the orders that have been added to this Service.*/
    List<Order> getAllOrders() {
        List<Order> returnValue = new ArrayList<>();
        for (Order order : this.orders.keySet()) {
            returnValue.add(order);
        }
        return returnValue;
    }
}
