package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.to.MealTo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface MealService {
    List<MealTo> getAll(Integer userId);

    List<MealTo> getAllByDateTime(Integer userId, LocalDate startDate, LocalTime startTime,
                                  LocalDate endDate,
                                  LocalTime endTime);

    MealTo getById(Integer userId, Integer mealId);

    void delete(Integer userId, Integer mealId);

    void update(Integer userId, MealTo mealTo);

}