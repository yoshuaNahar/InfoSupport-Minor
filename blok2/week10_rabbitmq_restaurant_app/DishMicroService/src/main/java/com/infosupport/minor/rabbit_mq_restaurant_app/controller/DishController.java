package com.infosupport.minor.rabbit_mq_restaurant_app.controller;

import com.infosupport.minor.rabbit_mq_restaurant_app.domain.Dish;
import com.infosupport.minor.rabbit_mq_restaurant_app.services.DishService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DishController {

  @Autowired
  private DishService dishService;

  @GetMapping("/dishes")
  public List<Dish> getDishes() {
    return dishService.findDishes();
  }
}
