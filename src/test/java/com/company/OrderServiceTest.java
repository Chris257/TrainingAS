package com.company;

import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Tests the Main class.
 * @author Frank Karlstrøm - frank.karlstrom@gmail.com - fk@databaseforum.no
 */
public class OrderServiceTest {
    /**
     * Tests that it is possible to add an order to the system, and retrieve it afterwards.
     * Quite simple for now, as we are just getting started.
     */
    @Test
    public void addSingleOrder() {
        // => Setup, create necessary objects
        // Create an initial test order for a fictive customer id at a specified point in time.
        int customerId = 12;
        LocalDateTime dateTime = LocalDateTime.of(2016, 5, 20, 19, 35, 0, 0);
        Order order = createTestOrder(customerId, dateTime);
        // Start up the orderService.
        OrderService orderService = new OrderService();

        // => Act, execute the add and get commands
        orderService.addOrder(order);
        Order retrievedOrder = orderService.getOrder(order.getId());

        // => Assert, verify that the id of the retrieved order is the same as the order sent in.
        assertThat(retrievedOrder.getId(), is(equalTo(order.getId())));

        // Verify the the id we get back is in the expected format:yyyyMMddHHmmssSSS+customerId
        assertThat(retrievedOrder.getId(), is(equalTo(new BigDecimal("2016052019350000012"))));
    }

    private Order createTestOrder(int customerId, LocalDateTime dateTime) {
        return new Order(customerId, dateTime);
    }
}