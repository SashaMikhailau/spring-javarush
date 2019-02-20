package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealTo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface MealService {
    List<MealTo> getAll(Integer userId,int calories);

    List<MealTo> getAllByDateTime(Integer userId, LocalDate startDate, LocalTime startTime,
                                  LocalDate endDate,
                                  LocalTime endTime, int calories);

    Meal getById(Integer userId, Integer mealId);

    void delete(Integer userId, Integer mealId);

    void update(Integer userId, Meal mealTo);

}