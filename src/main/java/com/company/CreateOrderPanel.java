package com.company;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
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
        newOrderButton.addClickListener(event -> {
            currentOrder = new Order(1, 1, 1, LocalDateTime.now());
            currentOrderLabel.setValue(currentOrder.toString());
        });
        addComponent(newOrderButton);

        // => Add buttons for each of the available meals.
        List<String> availableMeals = mealService.getAvailableMeals();
        for (String availableMeal : availableMeals) {
            Button button = new Button(availableMeal);
            button.addClickListener(event -> {
                if (currentOrder != null) {
                    currentOrder.addLine(event.getButton().getCaption(), 1);
                    currentOrderLabel.setValue(currentOrder.toString());
                }
            });
            addComponent(button);
        }

        // => Finally add a text that displays a crude version of our order.
        currentOrderLabel = new Label("No order placed yet!");
        addComponent(currentOrderLabel);
    }
}
