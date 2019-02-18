package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MealMapDao implements MealDao {
    Map<Integer, Meal> map = new ConcurrentHashMap<Integer, Meal>();



    private final AtomicInteger counter = new AtomicInteger();

    public MealMapDao() {
        addMeal(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        addMeal(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
        addMeal(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
        addMeal(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        addMeal(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        addMeal(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
    }

    @Override
    public List<Meal> getAllMeals() {
        return new ArrayList<>(map.values());
    }

    @Override
    public Meal getMealById(int id) {
        return map.get(id);
    }

    @Override
    public void deleteMeal(int id) {
        map.remove(id);
    }

    @Override
    public void updateMeal(Meal meal) {
        map.replace(meal.getId(), meal);
    }

    @Override
    public void addMeal(Meal meal) {
        meal.setId(counter.incrementAndGet());
        map.put(meal.getId(), meal);
    }
}
