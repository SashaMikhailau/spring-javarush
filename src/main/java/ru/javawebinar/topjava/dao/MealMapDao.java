package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MealMapDao implements MealDao {
    private final List<Meal> meals = new ArrayList<>();
    private final AtomicInteger counter = new AtomicInteger();

    public MealMapDao() {
        List<Meal> meals = Arrays.asList(
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500,counter.incrementAndGet()),
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000,counter.incrementAndGet()),
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500,counter.incrementAndGet()),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000,counter.incrementAndGet()),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500,counter.incrementAndGet()),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510,counter.incrementAndGet())
        );
    }

    @Override
    public List<Meal> getAllMeals() {
        return new ArrayList<>(meals);
    }

    @Override
    public Meal getMealById(int id) {
        return null;
    }

    @Override
    public void deleteMeal(int id) {

    }

    @Override
    public void updateMeal(Meal meal) {

    }

    @Override
    public void addMeal(Meal meal) {

    }
}
