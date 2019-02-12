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
import java.time.LocalTime;
import java.util.List;

public class MealServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Meal> meals = MealsUtil.getMeals();
        List<MealTo> list = MealsUtil.getFilteredWithExcess(meals, LocalTime.MIN, LocalTime.MAX, 2000);
        req.setAttribute("list",list);
        req.getRequestDispatcher("/meals.jsp").forward(req, resp);

    }
}
