package com.company;

import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        int waitressId = 2;
        int restaurantId = 7;
        LocalDateTime dateTime = LocalDateTime.of(2016, 5, 20, 19, 35, 0, 0);
        Order order = createTestOrder(customerId, waitressId, restaurantId, dateTime);
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

    /**
     * Tests that it is possible to add several orders, also that when the orders are retrieved back, we get the added
     * orders.
     */
    @Test
    public void addAndRetrieveMultipleOrders() {
        // Start up the orderService.
        OrderService orderService = new OrderService();
        List<Order> addedOrders = new ArrayList<>();

        Random random = new Random();
        int waitressId = 2;
        int restaurantId = 7;

        // => create some orders on random dates for some customers.
        int customerCount = random.nextInt(1000) + 1;
        for (int customerId = 1; customerId < customerCount; customerId++) {
            // for each customer
            List<LocalDateTime> randomDates = getRandomDates();
            for (LocalDateTime randomDate : randomDates) {
                //create an order and add to the orderService.
                Order testOrder = createTestOrder(customerId,waitressId,restaurantId,randomDate);
                orderService.addOrder(testOrder);
                addedOrders.add(testOrder);
            }
        }

        // Now verify that all orders are retrievable by orderService.
        List<Order> retrievedOrders = orderService.getAllOrders();
        assertThat(retrievedOrders.size(), is(equalTo(addedOrders.size())));

        // Also verify that all the orders in the tests cached list is present in the orderService.
        addedOrders.forEach(order -> {
            assertThat(retrievedOrders.contains(order), is(true));
        });
    }

    private Order createTestOrder(int customerId, int waitressId, int restaurantId, LocalDateTime dateTime) {
        return new Order(customerId,waitressId,restaurantId, dateTime);
    }

    /** Gets some random dates. The number of dates is random between 1 and 100. */
    private List<LocalDateTime> getRandomDates() {
        List<LocalDateTime> returnValue = new ArrayList<>();
        Random random = new Random();

        LocalDateTime now = LocalDateTime.now();
        int numberOfDates = random.nextInt(100) + 1;
        for (int i = 0; i < numberOfDates; i++) {
            int hours = random.nextInt();
            returnValue.add(now.minusHours(hours));
        }
        return returnValue;
    }
}