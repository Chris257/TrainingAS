package com.company;


import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Main UI root class for the application. Hopefully waitresses, chefs, owners, all can use web.
 * @author Frank Karlstrøm - frank.karlstrom@gmail.com - fk@databaseforum.no
 */
@SpringUI
public class VaadinUI extends UI {
    @Autowired
    private CreateOrderPanel createOrderPanel;
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setContent(createOrderPanel);
    }
}
