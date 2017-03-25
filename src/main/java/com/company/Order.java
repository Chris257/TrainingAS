package com.company;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an order of some kind in this system.
 * @author Frank Karlstrøm - frank.karlstrom@gmail.com - fk@databaseforum.no
 */
class Order {
    /** Immutable field, it should not be possible to change the customerId for an order. */
    private final long customerId;
    /** Immutable field, it should not be possible to change the timestamp for an order. */
    private final LocalDateTime datetime;

    /**
     * Creates a new order with the specified id.
     * The id for an order must be unique, thus we use a combination of date and time, and a customer id
     * as a unique identifier.
     */
    Order(long customerId, LocalDateTime datetime) {
        this.customerId = customerId;
        this.datetime = datetime;
    }

    /**
     * Returns a number representing this order.
     * The number consists of the date (represented as a number), and the customer id.
     * The date is formatted like this: yyyyMMddHHmmssSSS
     * the number is added to the last.
     * An example: Customer id 435 with an order at 2017-03-25 11:30:05.035
     * will be represented as the order id: 20170325113005035435
     * @return a BigDecimal representing the order id.
     */
    BigDecimal getId() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        BigDecimal id = new BigDecimal(dtf.format(this.datetime)+Long.toString(this.customerId));
        return id;
    }
}
