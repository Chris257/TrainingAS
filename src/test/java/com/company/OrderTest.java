package com.company;

import org.junit.Test;

/**
 * Checks that the order functionality is as we want it.
 * @author Frank Karlstrøm - frank.karlstrom@gmail.com - fk@databaseforum.no
 */
public class OrderTest {
    /**
     * Tests that it is possible to create an order, but not if the date(time) is in the future, and not if the date is
     * before the restaurants opening date.
     */
    @Test
    public void createOrder() {
    }

    /**
     * Tests that the required details for an order is set and retrieved sucessfully. Such as customerid (required),
     * waitressId (required), restaurantId (required).
     */
    @Test
    public void setGetRequiredOrderDetails() {

    }

    /**
     * Tests that orderlines can ble added and retrieved from the order. OrderLines are the items an order consists of.
     * Since this order is places on a restaurant, the order lines consists of the meals and beveragers ordered.
     */
    @Test
    public void addGetOrderLines() {

    }
}