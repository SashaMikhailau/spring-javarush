package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MealServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Meal> meals = new ArrayList<>();
        meals.add(new Meal(LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 13)),"Завтрак",500));
        meals.add(new Meal(LocalDateTime.of(LocalDate.now(), LocalTime.of(16, 11)),"Завтрак",700));
        meals.add(new Meal(LocalDateTime.of(LocalDate.now(), LocalTime.of(19, 32)),"Завтрак",600));
        meals.add(new Meal(LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.of(10, 02)),"Завтрак",600));
        meals.add(new Meal(LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.of(14, 10)),"Обед",700));
        meals.add(new Meal(LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.of(20, 00)),"Ужин",700));
        List<MealTo> mealToList = MealsUtil.getFilteredWithExcess(meals, LocalTime.MIN, LocalTime.MAX, 2000);
        req.setAttribute("meallist",mealToList);
        req.getRequestDispatcher("/meals.jsp").forward(req,resp);

    }
}
