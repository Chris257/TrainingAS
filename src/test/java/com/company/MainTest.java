package com.company;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Tests the Main class.
 * @author Frank Karlstrøm - frank.karlstrom@gmail.com - fk@databaseforum.no
 */
public class MainTest {
    /**
     * Tests that it is possible to add an order to the system, and retrieve it afterwards.
     * Quite simple for now, as we are just getting started.
     */
    @Test
    public void addOrder() {
        // => Setup, create necessary objects
        // Create an iniitial test order
        Order order = new Order();
        // Start up the orderService.
        OrderService orderService = new OrderService();

        // => Act, execute the add and get commands
        orderService.addOrder(order);
        Order retrievedOrder = orderService.getOrder(order.getId());

        // => Assert, verify that the id of the retrieved order is the same as the order sent in.
        assertThat(retrievedOrder.getId(), is(equalTo(order.getId())));
    }
}