package com.company;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Enables waitresses to place orders.
 * @author Frank Karlstrøm - frank.karlstrom@gmail.com - fk@databaseforum.no
 */
@SpringComponent
@UIScope
public class CreateOrderPanel extends VerticalLayout {
    @Autowired
    private MealService mealService;
    @Autowired
    private OrderService orderService;
    private Order currentOrder;
    private Label currentOrderLabel;

    /** Creates the entire UI for this CreateOrderPanel. */
    @PostConstruct
    void init() {
        // => Add a button that creates a new Order.
        Button newOrderButton = new Button("Create new order");
        Label dateLabel = new Label("");
        Label customerIdLabel = new Label("");
        Label waitressIdLabel = new Label("");
        Label restaurantIdLabel = new Label("");
        HorizontalLayout orderLayout = new HorizontalLayout();
        newOrderButton.addClickListener(event -> {
            currentOrder = new Order(1, 1, 1, LocalDateTime.now());
            currentOrderLabel.setValue(currentOrder.toString());
            dateLabel.setValue("Time: " + String.valueOf(currentOrder.getDatetime()));
            customerIdLabel.setValue(", Customer ID: " + String.valueOf(currentOrder.getCustomerId()));
            waitressIdLabel.setValue(", Waitress ID: " + String.valueOf(currentOrder.getWaitressId()));
            restaurantIdLabel.setValue(", Restaurant ID: " + String.valueOf(currentOrder.getCustomerId()));
        });
        addComponent(newOrderButton);
        orderLayout.addComponent(dateLabel);
        orderLayout.addComponent(customerIdLabel);
        orderLayout.addComponent(waitressIdLabel);
        orderLayout.addComponent(restaurantIdLabel);
        addComponent(orderLayout);

        // => Add buttons for each of the available meals.
        List<String> availableMeals = mealService.getAvailableMeals();
        for (String availableMeal : availableMeals) {
            HorizontalLayout menuLayout = new HorizontalLayout();
            Button button = new Button(availableMeal);
            TextField textField = new TextField();
            button.addClickListener(event -> {
                if (currentOrder != null) {
                    currentOrder.addLine(event.getButton().getCaption(), 1);
                    currentOrderLabel.setValue(currentOrder.toString());
                    textField.setValue(String.valueOf(currentOrder.getMenuItems().get(availableMeal)));
                }
            });
            menuLayout.addComponent(button);
            menuLayout.addComponent(textField);
            addComponent(menuLayout);
        }

        // => Finally add a text that displays a crude version of our order.
        currentOrderLabel = new Label("No order placed yet!");
        addComponent(currentOrderLabel);
    }
}
