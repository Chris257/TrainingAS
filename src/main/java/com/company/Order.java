package com.company;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Represents an order of some kind in this system.
 * @author Frank Karlstrøm - frank.karlstrom@gmail.com - fk@databaseforum.no
 */
class Order {
    /** Immutable field, it should not be possible to change the customerId for an order. */
    private final long customerId;
    /** Immutable field, it should not be possible to change the timestamp for an order. */
    private final LocalDateTime datetime;

    private long waitressId;
    private long restaurantId;

    /**
     * Creates a new order with the specified id.
     * The id for an order must be unique, thus we use a combination of date and time, and a customer id
     * as a unique identifier.
     */
    Order(long customerId, long waitressId, long restaurantId, LocalDateTime datetime) {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime openingDate = LocalDateTime.of(2012, 5, 20, 19, 35, 0, 0);
        if (datetime.isAfter(today)) {
            throw new IllegalArgumentException("Date cannot be in the future");
        } else if (datetime.isBefore(openingDate)) {
            throw new IllegalArgumentException("Date cannot be before opening date");
        } else {
            this.customerId = customerId;
            this.waitressId = waitressId;
            this.restaurantId = restaurantId;
            this.datetime = datetime;

        }
    }

    public LocalDateTime getDatetime() { return datetime; }

    public long getWaitressId() {
        return waitressId;
    }

    public long getRestaurantId() {
        return restaurantId;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setWaitressId(long waitressId) {
        this.waitressId = waitressId;
    }

    public void setRestaurantId(long restaurantId) {
        this.restaurantId = restaurantId;
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
        BigDecimal id = new BigDecimal(dtf.format(this.datetime) + Long.toString(this.customerId));
        return id;
    }

    private Map<String, Integer> menuItems = new LinkedHashMap<>();

    public void addLine(String menuItem, int quantity) {
        if (menuItems.containsKey(menuItem)) {
            int existing = menuItems.get(menuItem);
            menuItems.put(menuItem, existing + quantity);
        } else {
            this.menuItems.put(menuItem, quantity);
        }
    }

    Map<String, Integer> getMenuItems() {
        return menuItems;
    }

    /** A simple to string implementation to let us quickly see our order just by doing toString(). */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("customerId=").append(customerId);
        sb.append(", datetime=").append(datetime);
        sb.append(", waitressId=").append(waitressId);
        sb.append(", restaurantId=").append(restaurantId);
        sb.append(", menuItems=").append(menuItems);
        sb.append('}');
        return sb.toString();
    }
}