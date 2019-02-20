package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.to.MealTo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface MealService {
    List<MealTo> getAll(Integer userId);

    List<MealTo> getAll(Integer userId, LocalDate startDate
            , LocalTime startTime, LocalDate endDate, LocalTime endTime);

    void delete(Integer userId, Integer mealId);

    MealTo get(Integer userId, Integer mealId);

    void update(Integer userId, MealTo mealId);

}