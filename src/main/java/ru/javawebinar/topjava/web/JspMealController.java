package ru.javawebinar.topjava.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.javawebinar.topjava.service.MealService;

@Controller
@RequestMapping("/meals")
public class JspMealController {
    @Autowired
    MealService service;





}
