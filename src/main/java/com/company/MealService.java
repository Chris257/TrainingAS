package com.company;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Frank Karlstrøm - frank.karlstrom@gmail.com - fk@databaseforum.no
 */
@Service
public class MealService {
    public List<String> getAvailableMeals() {
        List<String> meals = new ArrayList<>();
        meals.add("Pressed foie gras");
        meals.add("Ravioli");
        meals.add("Roast Pigeon");
        meals.add("Soup");
        meals.add("Sorbet");
        meals.add("Lemonade");
        meals.add("Beer");
        meals.add("Soda");
        return meals;
    }
}
