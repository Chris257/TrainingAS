package com.company;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Service class that contains all the business logic for adding and fetching orders.
 * @author Frank Karlstrøm - frank.karlstrom@gmail.com - fk@databaseforum.no
 */
class OrderService {

   public void addOrder(Order order){

   }

   public Order getOrder(BigDecimal orderId){
       LocalDateTime dateTime = LocalDateTime.of(2016, 5, 20, 19, 35, 0, 0);
       Order order = new Order(12,dateTime);
       if(order.getId().equals(orderId)){
           return order;
       }else{
           return null;
       }
   }
}
