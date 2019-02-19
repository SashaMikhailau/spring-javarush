package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class MealServiceImpl implements MealService {

    private MealRepository repository;

    @Override
    public List<MealTo> getAll(Integer userId) {
        return MealsUtil.getFilteredWithExcess(repository.getAll(userId),
                SecurityUtil.authUserCaloriesPerDay(), LocalTime.MIN,
                LocalTime.MAX);
    }

    @Override
    public List<MealTo> getAllByDateTime(Integer userId, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        return MealsUtil.getFilteredWithExcess(repository.getAllByDate(userId, startDate, endDate),
                SecurityUtil.authUserCaloriesPerDay(), startTime, endTime);
    }

    @Override
    public MealTo getById(Integer userId, Integer mealId) {
        return MealsUtil.createWithExcess(repository.get(userId,mealId),false);
    }

    @Override
    public void delete(Integer userId, Integer mealId) {
        repository.delete(userId,mealId);
    }

    @Override
    public void update(Integer userId, MealTo mealTo) {
        Meal meal = new Meal(mealTo.getId(),mealTo.getDateTime(), mealTo.getDescription(),
                mealTo.getCalories());
        repository.save(userId, meal);

    }
}