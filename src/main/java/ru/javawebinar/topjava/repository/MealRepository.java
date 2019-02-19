package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

public interface MealRepository {
    Meal save(Integer userId, Meal meal);

    void delete(Integer userId, int id);

    Meal get(Integer userId, int id);

    Collection<Meal> getAll(Integer userId);

    Collection<Meal> getAllByDate(Integer userId, LocalDate startDate, LocalDate endDate);
}
