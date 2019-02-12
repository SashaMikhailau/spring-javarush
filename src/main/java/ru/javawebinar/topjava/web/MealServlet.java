package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.util.ArrayList;
import java.util.List;

public class MealServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(MealServlet.class);
    public static final String LIST = "/meals.jsp";
    public static final String EDIT = "/onemeal.jsp";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        log.debug("redirect to meals");
        List<Meal> meals = new ArrayList<>();
        List<MealTo> list = MealsUtil.getFilteredWithExcess(meals, LocalTime.MIN, LocalTime.MAX, 2000);
        req.setAttribute("list",list);
        req.getRequestDispatcher("/meals.jsp").forward(req, resp);

    }
}
