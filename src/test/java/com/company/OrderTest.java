package com.company;

import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Checks that the order functionality is as we want it.
 * @author Frank Karlstrøm - frank.karlstrom@gmail.com - fk@databaseforum.no
 */
public class OrderTest {

    private int customerId = 12;
    private int waitressId = 2;
    private int restaurantId = 7;
    /**
     * Tests that it is possible to create an order with valid date
     */
    @Test
    public void createOrder() {
        LocalDateTime dateTime = LocalDateTime.of(2015, 5, 20, 19, 35, 0, 0);
        Order order = new Order(customerId,waitressId,restaurantId, dateTime);
        OrderService orderService = new OrderService();
        orderService.addOrder(order);
        Order retrievedOrder = orderService.getOrder(order.getId());
        assertThat(retrievedOrder.getId(), is(equalTo(order.getId())));
    }

    /**
     * Tests that it is not possible to create an order in the future
     */
    @Test(expected = IllegalArgumentException.class)
    public void createFutureOrder() {
        LocalDateTime dateTime = LocalDateTime.of(2017, 5, 20, 19, 35, 0, 0);
        Order order = new Order(customerId,waitressId,restaurantId, dateTime);
        OrderService orderService = new OrderService();
        orderService.addOrder(order);
    }

    /**
     * Tests that it is not possible to create an order if the date is before the restaurants opening date.
     */
    @Test(expected = IllegalArgumentException.class)
    public void createOldOrder() {
        LocalDateTime dateTime = LocalDateTime.of(2011, 5, 20, 19, 35, 0, 0);
        Order order = new Order(customerId,waitressId,restaurantId, dateTime);
        OrderService orderService = new OrderService();
        orderService.addOrder(order);
    }

    /**
     * Tests that the required details for an order is set and retrieved sucessfully. Such as customerid (required),
     * waitressId (required), restaurantId (required).
     */
    @Test
    public void setGetRequiredOrderDetails() {
        LocalDateTime dateTime = LocalDateTime.of(2015, 5, 20, 19, 35, 0, 0);
        Order order = new Order(customerId,waitressId,restaurantId, dateTime);
        OrderService orderService = new OrderService();
        orderService.addOrder(order);
        Order retrievedOrder = orderService.getOrder(order.getId());

        //Verify ids
        assertThat(retrievedOrder.getCustomerId(), is(equalTo(order.getCustomerId())));
        assertThat(retrievedOrder.getWaitressId(), is(equalTo(order.getWaitressId())));
        assertThat(retrievedOrder.getRestaurantId(), is(equalTo(order.getRestaurantId())));

        //Change ids
        long newWaitressId = 6;
        long newRestaurantId = 5;
        order.setWaitressId(newWaitressId);
        order.setRestaurantId(newRestaurantId);

        //Verify new id
        assertThat(retrievedOrder.getWaitressId(), is(equalTo(newWaitressId)));
        assertThat(retrievedOrder.getRestaurantId(), is(equalTo(newRestaurantId)));
    }

    /**
     * Tests that orderlines can ble added and retrieved from the order. OrderLines are the items an order consists of.
     * Since this order is places on a restaurant, the order lines consists of the meals and beveragers ordered.
     */
    @Test
    public void addGetOrderLines() {
        LocalDateTime dateTime = LocalDateTime.of(2015, 5, 20, 19, 35, 0, 0);
        Order order = new Order(customerId,waitressId,restaurantId, dateTime);

        //Taking a list of orderlines
        List<String> orderItems = asList("beer","friedChicken","beef");
        OrderService orderService = new OrderService();

        //Adding orderlines to order
        for(String item : orderItems){
            order.addLine(item, 6);
        }

        orderService.addOrder(order);
        Order retrievedOrder = orderService.getOrder(order.getId());

        //Verify that orderlines from order
        assertEquals(retrievedOrder.getMenuItems(),orderItems);
    }
}