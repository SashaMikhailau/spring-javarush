package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.dao.MealMapDao;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class MealServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(MealServlet.class);
    public static final String LIST = "/meals.jsp";
    public static final String EDIT = "/onemeal.jsp";
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
    public MealDao mealDao;


    public MealServlet() {
        super();
        mealDao = new MealMapDao();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("Posted ");
        req.setCharacterEncoding("UTF-8");
        int mealId = Integer.parseInt(req.getParameter("mealId"));
        String description = req.getParameter("description");
        String dateTime= req.getParameter("datetime");
        int calories= Integer.parseInt(req.getParameter("calories"));
        log.debug(mealId+" "+description+" "+dateTime);
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime, dtf);
        Meal meal = new Meal(localDateTime,description,calories);
        meal.setId(mealId);
        mealDao.updateMeal(meal);
        resp.sendRedirect("meals");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        log.debug("redirect to meals");
        if ("delete".equals(action)) {
            int id = Integer.parseInt(req.getParameter("mealId"));
            mealDao.deleteMeal(id);
        } else if ("update".equals(action)) {
            int id = Integer.parseInt(req.getParameter("mealId"));
            Meal meal = mealDao.getMealById(id);
            req.setAttribute("meal",meal);
            req.getRequestDispatcher("/onemeal.jsp").forward(req,resp);
            return;
        }
        List<Meal> meals = mealDao.getAllMeals();
        List<MealTo> list = MealsUtil.getFilteredWithExcess(meals, LocalTime.MIN, LocalTime.MAX, 2000);
        req.setAttribute("list", list);
        req.getRequestDispatcher("/meals.jsp").forward(req, resp);

    }
}
